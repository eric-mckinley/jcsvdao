package org.jcsvdao.reflect;

import org.jcsvdao.CSVDaoException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ericmckinley on 31/01/2016.
 */
public class ReflectionHelper {

    public static Class createClass(String className) {
        try {
            return (Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new CSVDaoException("Unable to create class ", e);
        }
    }

    public static Object createInstance(String className) {
        return createInstance(createClass(className));
    }

    public static Object createInstance(Class type) {
        try {
            return type.newInstance();
        } catch (InstantiationException e) {
            throw new CSVDaoException("Unable to create new instance ", e);
        } catch (IllegalAccessException e) {
            throw new CSVDaoException("Unable to create new instance ", e);
        }
    }


    public static Object getProperty(Object o, String property) {
        try {
            Method setter = findGetter(property, o);
            return setter.invoke(o);
        } catch (IllegalAccessException e) {
            throw new CSVDaoException("Unable to get value for " + property, e);
        } catch (InvocationTargetException e) {
            throw new CSVDaoException("Unable to get value for " + property, e);
        }

    }

    public static void setProperty(Object o, String property, Object value) {
        try {
            Method setter = findSetter(property, o);
            setter.invoke(o, value);
        } catch (IllegalAccessException e) {
            throw new CSVDaoException("Unable to set value for " + property, e);
        } catch (InvocationTargetException e) {
            throw new CSVDaoException("Unable to set value for " + property, e);
        }
    }


    public static Class getType(Class clazz, String propertyName) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();

            for (int i = 0; i < descriptors.length; i++) {
                PropertyDescriptor descriptor = descriptors[i];
                if (descriptor.getName().equals(propertyName)) {
                    return descriptor.getPropertyType();
                }

            }
            throw new CSVDaoException("No property  " + propertyName + " found on " + clazz);

        } catch (IntrospectionException e) {
            throw new CSVDaoException("Unable to get class for property " + propertyName, e);
        }
    }


    private static Method findGetter(String name, Object object) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();

            for (int i = 0; i < descriptors.length; i++) {
                PropertyDescriptor descriptor = descriptors[i];
                if (descriptor.getName().equals(name)) {
                    return descriptor.getReadMethod();
                }

            }
            throw new CSVDaoException("No property  " + name + " found on " + object);

        } catch (IntrospectionException e) {
            throw new CSVDaoException("Unable to get read method for property " + name, e);
        }
    }

    private static Method findSetter(String name, Object object) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();

            for (int i = 0; i < descriptors.length; i++) {
                PropertyDescriptor descriptor = descriptors[i];
                if (descriptor.getName().equals(name)) {
                    return descriptor.getWriteMethod();
                }

            }
            throw new CSVDaoException("No property  " + name + " found on " + object);

        } catch (IntrospectionException e) {
            throw new CSVDaoException("Unable to get write method for property " + name, e);
        }
    }


}
