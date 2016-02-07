package org.jcsvdao.config.classes.converter;

import org.jcsvdao.CSVDaoException;

/**
 * Created by ericmckinley on 02/02/2016.
 */
public class IntegerCSVConverter extends AbstractCSVPropertyConverter<Integer> {


    public Integer convertFrom(String text) {
        try {
            return Integer.valueOf(text);
        } catch (NumberFormatException e) {
            throw new CSVDaoException("Unble to convert int " + text, e);
        }
    }

    public String convertTo(Integer object) {
        return String.valueOf(object);
    }
}
