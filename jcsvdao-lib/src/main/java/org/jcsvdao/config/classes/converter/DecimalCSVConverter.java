package org.jcsvdao.config.classes.converter;

import org.jcsvdao.CSVDaoException;

import javax.swing.text.NumberFormatter;
import javax.xml.bind.annotation.XmlAttribute;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by ericmckinley on 06/02/2016.
 */
public class DecimalCSVConverter extends AbstractCSVPropertyConverter<Double> {

    private String format;

    public String getFormat() {
        return format;
    }

    @XmlAttribute
    public void setFormat(String format) {
        this.format = format;
    }

    public Double convertFrom(String text) {
        if (format == null) {
            try {
                return Double.valueOf(text);
            } catch (NumberFormatException e) {
                throw new CSVDaoException("Unable to convert int " + text, e);
            }
        } else {
            try {
                NumberFormatter formatter = new NumberFormatter(new DecimalFormat(format));
                formatter.setValueClass(Double.class);
                return (Double) formatter.stringToValue(text);
            } catch (ParseException e) {
                throw new CSVDaoException("Unable to convert double " + text, e);
            }
        }
    }

    public String convertTo(Double object) {
        if (format == null) {
            return String.valueOf(object);
        } else {
            try {
                NumberFormatter formatter = new NumberFormatter(new DecimalFormat(format));
                return formatter.valueToString(object);
            } catch (ParseException e) {
                throw new CSVDaoException("Unble to convert double " + object, e);
            }

        }
    }
}
