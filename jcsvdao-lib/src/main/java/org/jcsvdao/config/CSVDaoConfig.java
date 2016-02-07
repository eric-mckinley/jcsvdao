package org.jcsvdao.config;


import org.jcsvdao.config.classes.CSVClassMapping;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ericmckinley on 02/02/2016.
 */
@XmlType(propOrder = {"classMappings", "classMappingFiles", "settings"})
@XmlRootElement(name = "CSVConfig")
public class CSVDaoConfig implements Serializable {

    private CSVMappingFiles classMappingFiles;
    private List<CSVClassMapping> classMappings;
    private List<CSVConfigProperty> settings;

    private Map<String, String> allSettings;

    public CSVDaoConfig() {

        this.allSettings = new HashMap<String, String>();
        this.allSettings.put("entities.delete.strategy", "comment"); //other option remove
        this.allSettings.put("entities.flush.strategy", "");
    }


    public Map<String, String> getAllSettings() {
        return allSettings;
    }

    @XmlTransient
    public void setAllSettings(Map<String, String> allSettings) {
        this.allSettings = allSettings;
    }


    public List<CSVClassMapping> getClassMappings() {
        return classMappings;
    }

    @XmlElements({
            @XmlElement(name = "mapping", type = CSVClassMapping.class)

    })
    @XmlElementWrapper(name = "mappings")
    public void setClassMappings(List<CSVClassMapping> classMappings) {
        this.classMappings = classMappings;
    }

    public CSVMappingFiles getClassMappingFiles() {
        return classMappingFiles;
    }

    @XmlElement(name = "mappingFiles")
    public void setClassMappingFiles(CSVMappingFiles classMappingFiles) {
        this.classMappingFiles = classMappingFiles;
    }

    public List<CSVConfigProperty> getSettings() {
        return settings;
    }

    @XmlElements({
            @XmlElement(name = "setting", type = CSVConfigProperty.class)

    })
    @XmlElementWrapper(name = "settings")
    public void setSettings(List<CSVConfigProperty> settings) {
        this.settings = settings;
    }
}
