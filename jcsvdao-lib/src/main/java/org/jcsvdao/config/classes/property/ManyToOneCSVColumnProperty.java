package org.jcsvdao.config.classes.property;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by ericmckinley on 03/02/2016.
 */
@XmlType(propOrder = {"classType", "bidirectionalProperty"})
public class ManyToOneCSVColumnProperty extends CSVColumnProperty {

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