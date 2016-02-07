package org.jcsvdao.config.classes.property;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by ericmckinley on 03/02/2016.
 */
//@XmlType( propOrder = { "classType", "bidirectionalProperty"} )
public class OneToManyCSVColumnProperty extends CSVColumnProperty {

    private String classType;
    private String bidirectionalProperty;
    private String foreignKeysDelimiter;

    public String getClassType() {
        return classType;
    }

    @XmlAttribute
    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getBidirectionalProperty() {
        return bidirectionalProperty;
    }

    @XmlAttribute
    public void setBidirectionalProperty(String bidirectionalProperty) {
        this.bidirectionalProperty = bidirectionalProperty;
    }

    public String getForeignKeysDelimiter() {
        return foreignKeysDelimiter;
    }

    @XmlAttribute
    public void setForeignKeysDelimiter(String foreignKeysDelimiter) {
        this.foreignKeysDelimiter = foreignKeysDelimiter;
    }
}
