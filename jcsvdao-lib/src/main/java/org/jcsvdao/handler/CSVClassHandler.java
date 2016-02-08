package org.jcsvdao.handler;


import org.jcsvdao.CSVDaoException;
import org.jcsvdao.config.classes.CSVClassMapping;
import org.jcsvdao.config.classes.converter.AbstractCSVPropertyConverter;
import org.jcsvdao.config.classes.converter.CSVPropertyConverter;
import org.jcsvdao.config.classes.property.CSVColumnProperty;
import org.jcsvdao.config.classes.property.SimpleCSVColumnProperty;
import org.jcsvdao.config.matcher.RowMatcher;
import org.jcsvdao.reflect.ReflectionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericmckinley on 03/02/2016.
 */
public class CSVClassHandler extends AbstractTextHandler {

    private CSVClassMapping mapping;

    private List instances;

    public CSVClassHandler(CSVClassMapping mapping) {
        super(mapping.getDelimiter(), mapping.getLineComment(), mapping.getIgnoreFirstLine());
        this.instances = new ArrayList();
        this.mapping = mapping;
    }

    public List getInstances() {
        return instances;
    }

    @Override
    public void handleLineValues(String[] values) {
        RowMatcher rowMatcher = mapping.getRowMatcher();
        if (rowMatcher.useLine(values)) {

            Object instance = ReflectionHelper.createInstance(mapping.getClassName());
            List<CSVColumnProperty> properties = mapping.getProperties();

            for (int i = 0; i < properties.size(); i++) {
                CSVColumnProperty csvColumnProperty = properties.get(i);


                if (csvColumnProperty instanceof SimpleCSVColumnProperty) {
                    SimpleCSVColumnProperty columnProperty = (SimpleCSVColumnProperty) csvColumnProperty;

                    try {
                        CSVPropertyConverter converter;
                        if (columnProperty.getConverter() != null) {
                            converter = getCustomConverter(columnProperty.getConverter());
                        } else {
                            Class propertyType = ReflectionHelper.getType(instance.getClass(), csvColumnProperty.getProperty());
                            converter = getConverter(propertyType);
                        }
                        String value = values[csvColumnProperty.getColumn()];
                        Object actualValue = converter.convertFrom(value);
                        ReflectionHelper.setProperty(instance, csvColumnProperty.getProperty(), actualValue);
                    } catch (CSVDaoException e) {
                        throw new CSVDaoException("Error with " + mapping.getClassName() + " property " + columnProperty.getProperty(), e);
                    }
                }
            }
            if (!instances.contains(instance)) {
                instances.add(instance);
            }
        }
    }

    private CSVPropertyConverter getCustomConverter(String converterName) {
        List<AbstractCSVPropertyConverter> converters = mapping.getConverters();
        if (converters != null) {
            for (AbstractCSVPropertyConverter converter : converters) {
                if (converter.getName().equals(converterName)) {
                    return converter;
                }
            }
        }
        throw new CSVDaoException("No converter registered name " + converterName + " registered for " + mapping.getClassName());
    }
}
