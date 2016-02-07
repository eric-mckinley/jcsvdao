package org.jcsvdao.config.classes.converter;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by ericmckinley on 02/02/2016.
 */

public abstract class AbstractCSVPropertyConverter<T> implements CSVPropertyConverter<T> {

    private String name;

    @XmlAttribute(name = "converterName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
