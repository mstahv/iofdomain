package org.peimari.helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.orienteering.datastandard._3.Iof3ClassResult;
import org.orienteering.datastandard._3.Iof3ResultList;
import org.orienteering.datastandard._3.Iof3PersonResult;
import org.orienteering.datastandard._3.Iof3SplitTime;
import org.orienteering.datastandard._3.PersonRaceResult;
import org.peimari.domain.ClassResult;
import org.peimari.domain.CompetitorStatus;
import org.peimari.domain.Person;
import org.peimari.domain.Result;
import org.peimari.domain.ResultList;
import org.peimari.domain.SplitTime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * A helper class to read iofxml file into apps internal format.
 */
public class ResultListService {

    static XPath xPath = XPathFactory.newInstance().newXPath();

    public static ResultList unmarshal(File f) {

        ResultList rl = new ResultList();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringComments(true);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setValidating(false);
        dbf.setCoalescing(true);
        try {
            dbf.setFeature(
                    "http://apache.org/xml/features/nonvalidating/load-external-dtd",
                    false);
            DocumentBuilder db = dbf.newDocumentBuilder();

            String defaultEncoding = "UTF-8";
            InputStream inputStream = new FileInputStream(f);
            BOMInputStream bOMInputStream = new BOMInputStream(inputStream);
            ByteOrderMark bom = bOMInputStream.getBOM();
            String charsetName = bom == null ? defaultEncoding : bom.getCharsetName();
            InputStreamReader reader = new InputStreamReader(new BufferedInputStream(bOMInputStream), charsetName);
            InputSource is = new InputSource(reader);
            is.setEncoding(defaultEncoding);

            Document d = db.parse(is);

            Element documentElement = d.getDocumentElement();

            if ("http://www.orienteering.org/datastandard/3.0".equals(documentElement.getAttribute("xmlns"))) {
                // IOF 3.0 handling
                return unmarshalIof3(f);
            }

            NodeList childNodes = documentElement.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (item instanceof Element) {
                    if (item.getNodeName().toLowerCase().equals("classresult")) {
                        rl.getClassResults().add(
                                parseClassResult((Element) item));
                    }

                }
            }
            return rl;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ResultList unmarshalIof3(File f) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Iof3ResultList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            String defaultEncoding = "UTF-8";
            InputStream inputStream = new FileInputStream(f);
            BOMInputStream bOMInputStream = new BOMInputStream(inputStream);
            ByteOrderMark bom = bOMInputStream.getBOM();
            String charsetName = bom == null ? defaultEncoding : bom.getCharsetName();
            InputStreamReader reader = new InputStreamReader(new BufferedInputStream(bOMInputStream), charsetName);
            InputSource is = new InputSource(reader);
            is.setEncoding(defaultEncoding);

            Iof3ResultList rl = (Iof3ResultList) jaxbUnmarshaller.unmarshal(is);
            ResultList resultList = new ResultList();
            List<ClassResult> classResults = new ArrayList<ClassResult>();
            List<Iof3ClassResult> crs = rl.getClassResult();
            for (Iof3ClassResult cr : crs) {
                ClassResult classResult = new ClassResult();
                classResult.setClassName(cr.getClazz().getName());
                List<Result> results = new ArrayList<Result>();
                for (Iof3PersonResult pr : cr.getPersonResult()) {
                    Person person = new Person();
                    person.setFirstName(pr.getPerson().getName().getGiven());
                    person.setLastName(pr.getPerson().getName().getFamily());
                    Result result = new Result();
                    result.setPerson(person);
                    PersonRaceResult prr = pr.getResult().get(0);
                    CompetitorStatus status;
                    if (prr.getStatus().value().equals("MissingPunch")) {
                        status = CompetitorStatus.DISQUALIFIED;
                    } else {
                        status = CompetitorStatus.valueOf(prr.getStatus().value().toUpperCase());
                    }

                    result.setCompetitorStatus(status);
                    if (result.getCompetitorStatus() == CompetitorStatus.OK) {
                        result.setTime(prr.getTime().longValue() * 1000);
                        List<Iof3SplitTime> sts = prr.getSplitTime();
                        List<SplitTime> splitTimes = new ArrayList<>();
                        for (Iof3SplitTime st : sts) {
                            SplitTime splitTime = new SplitTime();
                            splitTime.setControlCode(Integer.parseInt(st.getControlCode()));
                            splitTime.setTime(st.getTime().longValue() * 1000);
                            splitTimes.add(splitTime);
                        }
                        result.setSplitTimes(splitTimes);
                    }
                    results.add(result);
                }
                classResult.setResults(results);
                classResults.add(classResult);
            }
            resultList.setClassResults(classResults);
            return resultList;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private static ClassResult parseClassResult(Element item) {
        ClassResult classResult = new ClassResult();
        Node item2 = item.getElementsByTagName("ClassShortName").item(0);
        classResult.setClassName(item2.getTextContent());
        NodeList personresult = item.getElementsByTagName("PersonResult");
        for (int i = 0; i < personresult.getLength(); i++) {
            Element person = (Element) personresult.item(i);
            classResult.getResults().add(parseResult(person));
        }
        return classResult;
    }

    private static Result parseResult(Element personResult) {
        Element item = (Element) personResult.getElementsByTagName("Person")
                .item(0);
        String fn = item.getElementsByTagName("Family").item(0)
                .getTextContent();
        String gn = item.getElementsByTagName("Given").item(0).getTextContent();
        Person person = new Person();
        person.setFirstName(gn);
        person.setLastName(fn);
        Result result = new Result();
        result.setPerson(person);

        Element s = (Element) personResult.getElementsByTagName("CompetitorStatus").item(
                0);
        String status = s.getAttribute("value");
        if (status.isEmpty()) {
            status = s.getAttribute("Value");
        }

        result.setCompetitorStatus(CompetitorStatus.valueOf(status.toUpperCase()));

        Element r = (Element) personResult.getElementsByTagName("Result").item(
                0);

        Element time;
        try {
            time = (Element) xPath.evaluate("./Time",
                    r, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
        result.setTime(parseTime(time));
        result.setSplitTimes(parseSplits(r.getElementsByTagName("SplitTime")));
        return result;
    }

    private static List<SplitTime> parseSplits(NodeList elementsByTagName) {
        ArrayList<SplitTime> arrayList = new ArrayList<SplitTime>();
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            Element item = (Element) elementsByTagName.item(i);
            Element el = (Element) item.getElementsByTagName("ControlCode").item(0);
            Element time = (Element) item.getElementsByTagName("Time").item(0);
            SplitTime splitTime = new SplitTime();
            splitTime.setControlCode(Integer.parseInt(el.getTextContent()));
            splitTime.setTime(parseTime(time));
            arrayList.add(splitTime);
        }
        return arrayList;
    }

    private static long parseTime(Element time) {
        if (time == null) {
            return 0;
        }
        String timeString = time.getTextContent();
        String[] split = timeString.split(":");
        if (split.length == 3) {

            long t = Integer.parseInt(split[0]) * 60 * 60 * 1000
                    + Integer.parseInt(split[1]) * 60 * 1000
                    + (long) (Double.parseDouble(split[2]) * 1000);
            return t;
        } else {
            return 0;
        }
    }

}
