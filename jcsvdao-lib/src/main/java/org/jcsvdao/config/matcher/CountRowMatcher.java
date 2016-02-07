package org.jcsvdao.config.matcher;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by ericmckinley on 02/02/2016.
 */
@XmlType(propOrder = {"expectedColumns"})
public class CountRowMatcher extends RowMatcher {

    private int expectedColumns;

    public int getExpectedColumns() {
        return expectedColumns;
    }

    @XmlAttribute
    public void setExpectedColumns(int expectedColumns) {
        this.expectedColumns = expectedColumns;
    }

    @Override
    public boolean useLine(String[] cols) {
        return cols.length == expectedColumns;
    }
}
