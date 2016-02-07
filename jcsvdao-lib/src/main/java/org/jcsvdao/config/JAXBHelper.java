package org.jcsvdao.config;

import org.jcsvdao.CSVDaoException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by ericmckinley on 02/02/2016.
 */
public class JAXBHelper {




    public static Object loadResource(String resource, Class type) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Object data = jaxbUnmarshaller.unmarshal(type.getResourceAsStream(resource));
            return data;
        } catch (JAXBException e) {
            throw new CSVDaoException("Unable to read configuration resource " + resource, e);
        }
    }

    public static Object loadFile(String file, Class type) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Object data = jaxbUnmarshaller.unmarshal(new File(file));
            return data;
        } catch (JAXBException e) {
            throw new CSVDaoException("Unable to read configuration file " + file, e);
        }
    }
}
