package org.jcsvdao.examples.example01.converter;

import org.jcsvdao.config.classes.converter.EnumCSVPropertyConverter;
import org.jcsvdao.examples.example01.model.Gender;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ericmckinley on 07/02/2016.
 *
 */
public class GenderCustomerConverter extends EnumCSVPropertyConverter {
    @Override
    public Map<String, Enum> getEnumsMap() {
        Map<String, Enum> map = new HashMap<String, Enum>();
        map.put("M", Gender.MALE);
        map.put("F", Gender.FEMALE);
        return map;
    }
}
