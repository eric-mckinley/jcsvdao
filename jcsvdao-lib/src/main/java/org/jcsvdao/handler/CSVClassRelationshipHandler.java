package org.jcsvdao.handler;


import org.jcsvdao.CSVDao;
import org.jcsvdao.CSVDaoFactory;
import org.jcsvdao.config.classes.CSVClassMapping;
import org.jcsvdao.config.classes.converter.CSVPropertyConverter;
import org.jcsvdao.config.classes.property.*;
import org.jcsvdao.config.matcher.RowMatcher;
import org.jcsvdao.criteria.Check;
import org.jcsvdao.criteria.NormalCriteria;
import org.jcsvdao.reflect.ReflectionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericmckinley on 05/02/2016.
 */
public class CSVClassRelationshipHandler extends AbstractTextHandler {

    private CSVClassMapping mapping;
    private CSVDao dao;
    private CSVDaoFactory factory;

    public CSVClassRelationshipHandler(CSVDaoFactory factory, CSVClassMapping mapping, CSVDao dao) {
        super(mapping.getDelimiter(), mapping.getLineComment(), mapping.getIgnoreFirstLine());
        this.dao = dao;
        this.mapping = mapping;
        this.factory = factory;
    }


    @Override
    public void handleLineValues(String[] values) {
        RowMatcher rowMatcher = mapping.getRowMatcher();
        if (rowMatcher.useLine(values)) {

            SimpleCSVColumnProperty primaryProperty = mapping.getPrimaryKey();
            Class classType = ReflectionHelper.createClass(mapping.getClassName());

            Object primaryKeyValue = getPrimaryKeyValue(values);
            Object instance = dao.findUnique(classType, new NormalCriteria(primaryProperty.getProperty(), Check.EQ, primaryKeyValue));
            List<CSVColumnProperty> properties = mapping.getProperties();

            for (CSVColumnProperty property : properties) {


                if (property instanceof ManyToOneCSVColumnProperty) {
                    ManyToOneCSVColumnProperty manyToOne = (ManyToOneCSVColumnProperty) property;

                    CSVClassMapping foreignMapping = factory.getMapping(manyToOne.getClassType());

                    Class foreignType = ReflectionHelper.createClass(manyToOne.getClassType());
                    SimpleCSVColumnProperty foreignPrimaryKeyProp = foreignMapping.getPrimaryKey();
                    Class foreignKeyType = ReflectionHelper.getType(foreignType, foreignPrimaryKeyProp.getProperty());
                    CSVPropertyConverter converter = getConverter(foreignKeyType);
                    Object foreignKeyValue = converter.convertFrom(values[manyToOne.getColumn()]);


                    Object foreignData = dao.findUnique(foreignType, new NormalCriteria(foreignPrimaryKeyProp.getProperty(), Check.EQ, foreignKeyValue));
                    ReflectionHelper.setProperty(instance, manyToOne.getProperty(), foreignData);

                    if (manyToOne.getBidirectionalProperty() != null) {

                        List manyToOneList = (List) ReflectionHelper.getProperty(foreignData, manyToOne.getBidirectionalProperty());
                        if (manyToOneList == null) {
                            manyToOneList = new ArrayList();
                        }
                        manyToOneList.add(instance);
                        ReflectionHelper.setProperty(foreignData, manyToOne.getBidirectionalProperty(), manyToOneList);
                    }
                } else if (property instanceof OneToManyCSVColumnProperty) {

                    OneToManyCSVColumnProperty oneToMany = (OneToManyCSVColumnProperty) property;

                    CSVClassMapping foreignMapping = factory.getMapping(oneToMany.getClassType());
                    Class foreignType = ReflectionHelper.createClass(oneToMany.getClassType());
                    String[] foreignKeyValues = values[oneToMany.getColumn()].split(oneToMany.getForeignKeysDelimiter());
                    trimAll(foreignKeyValues);

                    List oneToManyList = new ArrayList();


                    SimpleCSVColumnProperty foreignPrimaryKeyProp = foreignMapping.getPrimaryKey();
                    Class foreignKeyType = ReflectionHelper.getType(foreignType, foreignPrimaryKeyProp.getProperty());
                    CSVPropertyConverter converter = getConverter(foreignKeyType);



                    for (String foreignKeyValue : foreignKeyValues) {
                        Object fk = converter.convertFrom(foreignKeyValue);
                        Object foreignData = dao.findUnique(foreignType, new NormalCriteria(foreignPrimaryKeyProp.getProperty(), Check.EQ, fk));
                        oneToManyList.add(foreignData);

                        if (oneToMany.getBidirectionalProperty() != null) {
                            ReflectionHelper.setProperty(foreignData, oneToMany.getBidirectionalProperty(), instance);
                        }
                    }
                    ReflectionHelper.setProperty(instance, oneToMany.getProperty(), oneToManyList);

                } else if (property instanceof OneToOneCSVColumnProperty) {
                    OneToOneCSVColumnProperty oneToOne = (OneToOneCSVColumnProperty) property;

                    CSVClassMapping foreignMapping = factory.getMapping(oneToOne.getClassType());
                    Class foreignType = ReflectionHelper.createClass(oneToOne.getClassType());

                    SimpleCSVColumnProperty foreignPrimaryKeyProp = foreignMapping.getPrimaryKey();

                    Class foreignKeyType = ReflectionHelper.getType(foreignType, foreignPrimaryKeyProp.getProperty());
                    CSVPropertyConverter converter = getConverter(foreignKeyType);
                    Object foreignKeyValue = converter.convertFrom(values[oneToOne.getColumn()]);

                    SimpleCSVColumnProperty foreignPKProperty = foreignMapping.getPrimaryKey();

                    Object foreignData = dao.findUnique(foreignType, new NormalCriteria(foreignPKProperty.getProperty(), Check.EQ, foreignKeyValue));
                    ReflectionHelper.setProperty(instance, oneToOne.getProperty(), foreignData);

                    if (oneToOne.getBidirectionalProperty() != null) {
                        ReflectionHelper.setProperty(foreignData, oneToOne.getBidirectionalProperty(), instance);
                    }
                }
            }
        }
    }

    private Object getPrimaryKeyValue(String[] values){
        SimpleCSVColumnProperty primaryProperty = mapping.getPrimaryKey();
        Class classType = ReflectionHelper.createClass(mapping.getClassName());
        Class propertyType = ReflectionHelper.getType(classType, primaryProperty.getProperty());

        CSVPropertyConverter converter = getConverter(propertyType);
        return converter.convertFrom(values[primaryProperty.getColumn()]);
    }
}
