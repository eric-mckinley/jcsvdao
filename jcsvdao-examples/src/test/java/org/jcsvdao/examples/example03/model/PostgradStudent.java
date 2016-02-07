package org.jcsvdao.examples.example03.model;

/**
 * Created by ericmckinley on 07/02/2016.
 *
 */
public class PostgradStudent extends Student {

    private String thesisTitle;

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }
}
