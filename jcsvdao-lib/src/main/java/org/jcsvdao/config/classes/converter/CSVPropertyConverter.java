package org.jcsvdao.config.classes.converter;

import java.io.Serializable;

/**
 * Created by ericmckinley on 02/02/2016.
 */
public interface CSVPropertyConverter<T> extends Serializable {

    public String getName();

    public T convertFrom(String text);

    public String convertTo(T object);
}
