package org.jcsvdao.handler;

import org.jcsvdao.config.classes.converter.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ericmckinley on 02/02/2016.
 */
public abstract class AbstractTextHandler implements TextHandler {

    private int lineCount;
    private String delimiter;
    private boolean ignoreFirstLine;
    private String comment;

    private Map<Class, CSVPropertyConverter> converterMap;

    public AbstractTextHandler(String delimiter, String comment, Boolean ignoreHeader) {
        this.delimiter = delimiter;
        this.comment = comment;
        ignoreFirstLine = Boolean.TRUE.equals(ignoreHeader);

        this.converterMap = new HashMap<Class, CSVPropertyConverter>();

        this.converterMap.put(Boolean.class, new BooleanCSVConverter());
        this.converterMap.put(String.class, new StringCSVConverter());
        this.converterMap.put(Integer.class, new IntegerCSVConverter());
        this.converterMap.put(Long.class, new LongCSVConverter());
        this.converterMap.put(Double.class, new DecimalCSVConverter());
    }

    protected CSVPropertyConverter getConverter(Class type){
        return this.converterMap.get(type);
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public boolean isIgnoreFirstLine() {
        return ignoreFirstLine;
    }

    public void setIgnoreFirstLine(boolean ignoreFirstLine) {
        this.ignoreFirstLine = ignoreFirstLine;
    }

    final public void handleLine(String line) {

        if (lineCount == 0 && ignoreFirstLine && !line.startsWith(comment)) {
            lineCount++;
        } else if (!line.startsWith(comment)) {
            lineCount++;
            String[] values = line.split(delimiter);
            trimAll(values);
            if(values.length > 0) {
                handleLineValues(values);
            }
        }
    }

    protected void trimAll(String[] list) {
        for (int i = 0; i < list.length; i++) {
            list[i] = list[i].trim();
        }
    }

    public abstract void handleLineValues(String[] values);
}
