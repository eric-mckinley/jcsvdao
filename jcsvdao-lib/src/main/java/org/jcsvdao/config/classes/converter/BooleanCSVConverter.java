package org.jcsvdao.config.classes.converter;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by ericmckinley on 06/02/2016.
 */
public class BooleanCSVConverter extends AbstractCSVPropertyConverter {

    private String positive;
    private String negative;

    public BooleanCSVConverter() {
        this.positive = "true";
        this.negative = "false";
    }

    public String getPositive() {
        return positive;
    }

    @XmlAttribute(name = "positive")
    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getNegative() {
        return negative;
    }

    @XmlAttribute(name = "negative")
    public void setNegative(String negative) {
        this.negative = negative;
    }

    public Object convertFrom(String text) {

        if (this.positive.equals(text)) {
            return Boolean.TRUE;
        } else if (this.negative.equals(text)) {
            return Boolean.FALSE;
        } else {
            return null;
        }
    }

    public String convertTo(Object object) {

        if (Boolean.TRUE.equals(object)) {
            return this.positive;
        } else if (Boolean.FALSE.equals(object)) {
            return this.negative;
        } else {
            return "";
        }
    }
}
