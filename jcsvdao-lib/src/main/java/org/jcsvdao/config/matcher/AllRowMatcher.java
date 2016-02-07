package org.jcsvdao.config.matcher;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ericmckinley on 02/02/2016.
 */
@XmlRootElement(name = "allRowMatcher")
public class AllRowMatcher extends RowMatcher {

    @Override
    public boolean useLine(String[] cols) {
        return true;
    }
}
