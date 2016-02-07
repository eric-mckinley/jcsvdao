package org.jcsvdao.config.classes.converter;

import org.jcsvdao.CSVDaoException;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by ericmckinley on 02/02/2016.
 */
public class CustomCSVPropertyConverter extends AbstractCSVPropertyConverter {

    private String className;

    private AbstractCSVPropertyConverter customConverter;


    public String getClassName() {
        return className;
    }

    @XmlAttribute(name = "converterClass")
    public void setClassName(String className) {
        this.className = className;
    }

    public Object convertFrom(String text) {
        AbstractCSVPropertyConverter converter = getConverter();
        return converter.convertFrom(text);
    }

    public String convertTo(Object object) {
        AbstractCSVPropertyConverter converter = getConverter();
        return converter.convertTo(object);
    }

    private AbstractCSVPropertyConverter getConverter() {
        if (customConverter == null) {
            try {
                customConverter = (AbstractCSVPropertyConverter) Class.forName(className).newInstance();
            } catch (InstantiationException e) {
                throw new CSVDaoException("Cannot create custom converter : " + className, e);
            } catch (IllegalAccessException e) {
                throw new CSVDaoException("Cannot create custom converter : " + className, e);
            } catch (ClassNotFoundException e) {
                throw new CSVDaoException("Cannot create custom converter : " + className, e);
            }
        }
        return customConverter;
    }
}
