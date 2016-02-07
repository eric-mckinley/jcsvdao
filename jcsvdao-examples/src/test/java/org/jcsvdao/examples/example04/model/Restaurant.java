package org.jcsvdao.examples.example04.model;

/**
 * Created by ericmckinley on 07/02/2016.
 *
 * @RentBadger
 */
public class Restaurant {

    private String name;
    private String city;
    private Chef headChef;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Chef getHeadChef() {
        return headChef;
    }

    public void setHeadChef(Chef headChef) {
        this.headChef = headChef;
    }
}
