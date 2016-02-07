package org.jcsvdao.config;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by ericmckinley on 03/02/2016.
 */
public class CSVConfigProperty {

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    @XmlAttribute
    public void setValue(String value) {
        this.value = value;
    }
}
