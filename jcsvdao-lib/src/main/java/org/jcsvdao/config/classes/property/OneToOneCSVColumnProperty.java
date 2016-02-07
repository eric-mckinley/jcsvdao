package org.jcsvdao.config.classes.property;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by ericmckinley on 05/02/2016.
 */
public class OneToOneCSVColumnProperty extends CSVColumnProperty {

    private String classType;
    private String bidirectionalProperty;

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
}