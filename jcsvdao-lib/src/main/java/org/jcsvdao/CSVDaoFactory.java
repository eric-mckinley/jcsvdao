package org.jcsvdao;

import org.jcsvdao.config.CSVDaoConfig;
import org.jcsvdao.config.CSVMappingFiles;
import org.jcsvdao.config.JAXBHelper;
import org.jcsvdao.config.classes.CSVClassMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericmckinley on 02/02/2016.
 */
public class CSVDaoFactory {

    private static CSVDaoConfig config;

    public CSVDaoFactory() {
        this("/csv-config.xml");
    }


    public CSVDaoFactory(String configurationFile) {

        config = (CSVDaoConfig) JAXBHelper.loadResource(configurationFile, CSVDaoConfig.class);

        if (config.getClassMappings() == null) {
            CSVMappingFiles mappingFiles = config.getClassMappingFiles();
            List<String> files = mappingFiles.getFiles();
            List<CSVClassMapping> classMappings = new ArrayList<CSVClassMapping>();
            if (mappingFiles.getType().equals("resource")) {
                for (String s : files) {
                    classMappings.add((CSVClassMapping) JAXBHelper.loadResource(s, CSVClassMapping.class));
                }
            } else if (mappingFiles.getType().equals("file")) {
                for (String s : files) {
                    classMappings.add((CSVClassMapping) JAXBHelper.loadFile(s, CSVClassMapping.class));
                }
            }
            config.setClassMappings(classMappings);
        }
    }


    public CSVDaoConfig getConfig() {
        return config;
    }

    public void setConfig(CSVDaoConfig freshConfig) {
        config = freshConfig;
    }


    public CSVClassMapping getMapping(String className) {
        List<CSVClassMapping> mappings = config.getClassMappings();
        for (int i = 0; i < mappings.size(); i++) {
            CSVClassMapping mapping = mappings.get(i);
            if (mapping.getClassName().equals(className)) {
                return mapping;
            }
        }
        throw new CSVDaoException("Unable to find mapping for " + className);
    }

    public CSVClassMapping getMapping(Class clazz) {
        return getMapping(clazz.getName());
    }


}
