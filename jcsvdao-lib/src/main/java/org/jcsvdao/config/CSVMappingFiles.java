package org.jcsvdao.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by ericmckinley on 03/02/2016.
 */
public class CSVMappingFiles {

    private String type;
    private List<String> files;

    public String getType() {
        return type;
    }

    @XmlAttribute(name = "fileType")
    public void setType(String type) {
        this.type = type;
    }

    public List<String> getFiles() {
        return files;
    }

    @XmlElement(name = "mappingFile")
    public void setFiles(List<String> files) {
        this.files = files;
    }
}
