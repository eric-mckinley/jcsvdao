package org.jcsvdao.config.classes.property;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by ericmckinley on 02/02/2016.
 */


@XmlType(propOrder = {"column", "property"})
public class CSVColumnProperty implements Serializable {

    private int column;
    private String property;

    public CSVColumnProperty() {
        column = -1; //default incase property not mapped
    }

    public int getColumn() {
        return column;
    }

    @XmlAttribute(name = "index")
    public void setColumn(int column) {
        this.column = column;
    }

    public String getProperty() {
        return property;
    }

    @XmlAttribute(name = "property")
    public void setProperty(String property) {
        this.property = property;
    }

}
