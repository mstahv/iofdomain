package org.peimari.iofjavahelper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.Test;
import org.orienteering.datastandard._3.Iof3ResultList;

import java.io.File;

public class Iof3Domain {

    @Test
    public void testUnmarshal() {
        // unmarshal IOF 3.0 XML file to domain objects

        try {

            File file = new File("src/test/resources/iof3_from_navisport.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Iof3ResultList.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Iof3ResultList rl= (Iof3ResultList) jaxbUnmarshaller.unmarshal(file);

            String name = rl.getEvent().getName();
            System.out.println(name);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
