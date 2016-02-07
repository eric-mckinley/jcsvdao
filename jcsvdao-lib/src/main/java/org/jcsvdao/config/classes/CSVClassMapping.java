package org.jcsvdao.config.classes;


import org.jcsvdao.CSVDaoException;
import org.jcsvdao.config.classes.converter.*;
import org.jcsvdao.config.classes.property.*;
import org.jcsvdao.config.matcher.*;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ericmckinley on 02/02/2016.
 */

@XmlType(propOrder = {"className", "rowMatcher", "properties", "converters"})
@XmlRootElement(name = "CSVMapping")
public class CSVClassMapping implements Serializable {

    private String className;
    private String csvFile;
    private String delimiter;
    private Boolean ignoreFirstLine;
    private String lineComment;

    private RowMatcher rowMatcher;
    private List<CSVColumnProperty> properties;
    private List<AbstractCSVPropertyConverter> converters;

    public CSVClassMapping() {
        this.lineComment = "#";
        this.delimiter = ",";
    }

    public String getClassName() {
        return className;
    }

    @XmlAttribute(name = "className")
    public void setClassName(String className) {
        this.className = className;
    }


    public String getCsvFile() {
        return csvFile;
    }


    public String getLineComment() {
        return lineComment;
    }

    @XmlAttribute(name = "lineComment")
    public void setLineComment(String lineComment) {
        this.lineComment = lineComment;
    }

    @XmlAttribute(name = "csvFile")
    public void setCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }

    public String getDelimiter() {
        return delimiter;
    }

    @XmlAttribute(name = "delimiter")
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public Boolean getIgnoreFirstLine() {
        return ignoreFirstLine;
    }

    @XmlAttribute(name = "ignoreFirstLine")
    public void setIgnoreFirstLine(Boolean ignoreFirstLine) {
        this.ignoreFirstLine = ignoreFirstLine;
    }

    public RowMatcher getRowMatcher() {
        return rowMatcher;
    }


    @XmlElements({
            @XmlElement(name = "matchByColumnCount", type = CountRowMatcher.class),
            @XmlElement(name = "matchByColumn", type = ColumnPropertyRowMatcher.class),
            @XmlElement(name = "matchCustom", type = CustomRowMatcher.class),
            @XmlElement(name = "matchAll", type = AllRowMatcher.class)

    })
    public void setRowMatcher(RowMatcher rowMatcher) {
        this.rowMatcher = rowMatcher;
    }

    public List<CSVColumnProperty> getProperties() {
        return properties;
    }

    @XmlElements({
            @XmlElement(name = "manyToOne", type = ManyToOneCSVColumnProperty.class),
            @XmlElement(name = "oneToMany", type = OneToManyCSVColumnProperty.class),
            @XmlElement(name = "oneToOne", type = OneToOneCSVColumnProperty.class),
            @XmlElement(name = "property", type = SimpleCSVColumnProperty.class)

    })
    @XmlElementWrapper(name = "properties")
    public void setProperties(List<CSVColumnProperty> properties) {
        this.properties = properties;
    }

    public List<AbstractCSVPropertyConverter> getConverters() {
        return converters;
    }

    @XmlElements({
            @XmlElement(name = "customConverter", type = CustomCSVPropertyConverter.class),
            @XmlElement(name = "dateConverter", type = DateCSVPropertyConverter.class),
            @XmlElement(name = "decimalConverter", type = DecimalCSVConverter.class),
            @XmlElement(name = "booleanConverter", type = BooleanCSVConverter.class)
    })
    @XmlElementWrapper(name = "converters")
    public void setConverters(List<AbstractCSVPropertyConverter> converters) {
        this.converters = converters;
    }




    public SimpleCSVColumnProperty getPrimaryKey() {
        for (CSVColumnProperty property : properties) {
            if (property instanceof SimpleCSVColumnProperty) {
                SimpleCSVColumnProperty simpleProperty = (SimpleCSVColumnProperty) property;
                if (Boolean.TRUE.equals(simpleProperty.getPrimaryKey())) {
                    return (SimpleCSVColumnProperty) property;
                }
            }
        }
        throw new CSVDaoException("Unable to find primary key column for " + getClassName());
    }

    public CSVColumnProperty getProperty(String name) {
        for (CSVColumnProperty property : properties) {
            if (name.equals(property.getProperty())) {
                return property;
            }
        }
        throw new CSVDaoException("Unable to find primary key column for " + getClassName());
    }
}
