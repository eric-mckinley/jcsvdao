package org.jcsvdao.config.matcher;

/**
 * Created by ericmckinley on 02/02/2016.
 */
public abstract class RowMatcher {

    public abstract boolean useLine(String[] cols);
}
