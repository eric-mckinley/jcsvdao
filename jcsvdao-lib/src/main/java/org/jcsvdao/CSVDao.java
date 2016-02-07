package org.jcsvdao;


import org.jcsvdao.config.classes.CSVClassMapping;
import org.jcsvdao.config.classes.property.SimpleCSVColumnProperty;
import org.jcsvdao.criteria.Check;
import org.jcsvdao.criteria.Criteria;
import org.jcsvdao.criteria.NormalCriteria;
import org.jcsvdao.handler.CSVClassHandler;
import org.jcsvdao.handler.CSVClassRelationshipHandler;
import org.jcsvdao.reader.TextFileDataReader;
import org.jcsvdao.reflect.ReflectionHelper;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ericmckinley on 02/02/2016.
 */
public class CSVDao {

    private Map<Class, List> data = new HashMap<Class, List>();
    private List<Class> classes = new ArrayList<Class>();
    private CSVDaoFactory factory;


    public CSVDao(CSVDaoFactory factory) {
        try {
            this.factory = factory;
            initialise();
        } catch (Exception e) {
            throw new CSVDaoException("Unable to initialise dao",e);
        }
    }


    private void initialise() throws Exception {
        List<CSVClassMapping> mappings = factory.getConfig().getClassMappings();

        for (CSVClassMapping mapping : mappings) {
            classes.add(ReflectionHelper.createClass(mapping.getClassName()));
            CSVClassHandler handler = new CSVClassHandler(mapping);

            TextFileDataReader reader = new TextFileDataReader();
            reader.register(handler);
            reader.read(new FileInputStream(mapping.getCsvFile()));

            Class type = ReflectionHelper.createClass(mapping.getClassName());
            data.put(type, handler.getInstances());

        }


        for (int i = 0; i < classes.size(); i++) {
            Class classA = classes.get(i);

            for (Class classB : classes) {
                if (!classA.equals(classB)) {
                    if (classA.isAssignableFrom(classB)) {

                        List a = data.get(classA);
                        List b = data.get(classB);
                        a.addAll(b);
                    }
                }
            }

        }


        for (CSVClassMapping mapping : mappings) {

            CSVClassRelationshipHandler handler = new CSVClassRelationshipHandler(mapping, this);

            TextFileDataReader reader = new TextFileDataReader();
            reader.register(handler);
            reader.read(new FileInputStream(mapping.getCsvFile()));
        }


    }

    public <T>T get(Class<T> type, Serializable primaryKey)  {
        CSVClassMapping mapping = CSVDaoFactory.getMapping(type);
        SimpleCSVColumnProperty primaryKeyMapping = mapping.getPrimaryKey();

        NormalCriteria criteria = new NormalCriteria(primaryKeyMapping.getProperty(), Check.EQ, primaryKey);
        return findUnique(type, criteria);
    }

    public <T> List<T> find(Class<T> type, Criteria... criterias)  {
        List<T> found = new ArrayList<T>();

        List<T> allData = this.data.get(type);
        for (T data : allData) {
            boolean valid = true;

            for (Criteria criteria : criterias) {
                Object valueToTest = ReflectionHelper.getProperty(data, criteria.getProperty());
                valid &= criteria.isValid(valueToTest);
            }
            if (valid) {
                found.add(data);
            }
        }
        return found;
    }

    public <T>T findUnique(Class<T> type, Criteria... criterias)  {
        List<T> found = find(type, criterias);
        if (found.size() == 1) {
            return found.get(0);
        } else {
            StringBuilder buff = new StringBuilder();
            for (Criteria criteria : criterias) {
                buff.append(criteria).append("  ");
            }
            throw new CSVDaoException("Expected one " + type + " found:  " + found.size() + ", criteria " + buff);
        }
    }

}
