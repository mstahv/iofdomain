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

			InputStream inputStream= new FileInputStream(f);
			Reader reader = new InputStreamReader(inputStream,"UTF-8");
			InputSource is = new InputSource(reader);
			is.setEncoding("UTF-8");

			Document d = db.parse(is);
			Element documentElement = d.getDocumentElement();
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
		if(status.isEmpty()) {
			status =  s.getAttribute("Value");
		}

		result.setCompetitorStatus(CompetitorStatus.valueOf(status.toUpperCase()));

		Element r = (Element) personResult.getElementsByTagName("Result").item(
				0);
		
		Element time;
		try {
			time = (Element)xPath.evaluate("./Time",
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
		for(int i = 0; i < elementsByTagName.getLength(); i++) {
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
