package org.jcsvdao.config.classes.property;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by ericmckinley on 05/02/2016.
 */

@XmlType(propOrder = {"primaryKey", "converter"})
public class SimpleCSVColumnProperty extends CSVColumnProperty {

    private String converter;
    private Boolean primaryKey;

    public String getConverter() {
        return converter;
    }

    @XmlAttribute(name = "converter")
    public void setConverter(String converter) {
        this.converter = converter;
    }

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    @XmlAttribute(name = "primaryKey")
    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
}
