package org.jcsvdao.config.classes.converter;

import org.jcsvdao.CSVDaoException;

/**
 * Created by ericmckinley on 02/02/2016.
 */
public class LongCSVConverter extends AbstractCSVPropertyConverter<Long> {


    public Long convertFrom(String text) {
        try {
            return Long.valueOf(text);
        } catch (NumberFormatException e) {
            throw new CSVDaoException("Unble to convert long " + text, e);
        }
    }

    public String convertTo(Long object) {
        return String.valueOf(object);
    }
}
