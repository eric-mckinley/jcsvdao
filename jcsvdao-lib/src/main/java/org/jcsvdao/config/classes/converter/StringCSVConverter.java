package org.jcsvdao.config.classes.converter;

/**
 * Created by ericmckinley on 02/02/2016.
 */
public class StringCSVConverter extends AbstractCSVPropertyConverter<String> {

    public String convertFrom(String text) {
        return text;
    }

    public String convertTo(String object) {
        return object;
    }
}
