package org.jcsvdao.criteria;

/**
 * Created by ericmckinley on 27/01/2016.
 */
public class CompoundCriteria implements Criteria {

    public enum CompoundCheckType {
        AND, OR
    }

    private NormalCriteria[] criterias;
    private CompoundCheckType checkType;
    private Class type;
    private String property;


    public CompoundCriteria(String property, CompoundCheckType checkType, NormalCriteria... criterias) {
        this.property = property;
        this.criterias = criterias;
        this.checkType = checkType;
    }

    public String getProperty() {
        return property;
    }

    public boolean isValid(Object o) {
        if (checkType.equals(CompoundCheckType.AND)) {

            for (int i = 0; i < criterias.length; i++) {
                NormalCriteria criteria = criterias[i];
                if (!criteria.isValid(o)) {
                    return false; //AND fails if one invalid
                }
            }
            return criterias.length > 0; //failing if none at all
        } else {

            for (int i = 0; i < criterias.length; i++) {
                NormalCriteria criteria = criterias[i];
                if (criteria.isValid(o)) {
                    return true; //OR passes if one valid
                }
            }
            return criterias.length > 0; //failing if none at all
        }
    }
}
