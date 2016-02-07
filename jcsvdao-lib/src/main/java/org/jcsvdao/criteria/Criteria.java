package org.jcsvdao.criteria;

/**
 * Created by ericmckinley on 27/01/2016.
 */
public interface Criteria {

    public String getProperty();

    public boolean isValid(Object o);
}
