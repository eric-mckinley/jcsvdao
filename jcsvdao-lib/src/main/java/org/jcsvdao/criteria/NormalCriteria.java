package org.jcsvdao.criteria;

import java.util.Date;
import java.util.List;

/**
 * Created by ericmckinley on 27/01/2016.
 */
public class NormalCriteria implements Criteria {

    private Check check;
    private Object value;
    private String property;

    public NormalCriteria(String property, Check check, Object value) {
        this.property = property;
        this.check = check;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public boolean isValid(Object o) {
        switch (check) {
            case LT:
                double da1 = Double.valueOf(o.toString());
                double da2 = Double.valueOf(value.toString());
                return da1 < da2;
            case LTE:
                double db1 = Double.valueOf(o.toString());
                double db2 = Double.valueOf(value.toString());
                return db1 <= db2;
            case GT:
                double dc1 = Double.valueOf(o.toString());
                double dc2 = Double.valueOf(value.toString());
                return dc1 > dc2;
            case GTE:
                double dd1 = Double.valueOf(o.toString());
                double dd2 = Double.valueOf(value.toString());
                return dd1 >= dd2;
            case EQ:
                return o.equals(value);
            case NEQ:
                return !o.equals(value);
            case ISNULL:
                return o == null;
            case NOTNULL:
                return o != null;
            case SIZE:
                double size = (o == null) ? 0 : ((List) o).size();
                double listSize = Double.valueOf(value.toString());
                return size == listSize;
            case BEFORE:
                Date date1 = (Date) o;
                Date date2 = (Date) value;
                return date1.before(date2);
            case AFTER:
                Date dateA1 = (Date) o;
                Date dateA2 = (Date) value;
                return dateA1.after(dateA2);
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return "NormalCriteria{" +
                "check=" + check +
                ", value=" + value +
                ", valueType=" + value.getClass().getName() +
                ", property='" + property + '\'' +
                '}';
    }
}
