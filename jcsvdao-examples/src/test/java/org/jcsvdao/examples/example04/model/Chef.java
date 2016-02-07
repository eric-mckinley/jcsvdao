package org.jcsvdao.examples.example04.model;

/**
 * Created by ericmckinley on 07/02/2016.
 *
 * @RentBadger
 */
public class Chef {

    private Integer id;
    private String title;
    private String fullName;
    private Integer yearsExperience;
    private Restaurant employer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(Integer yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public Restaurant getEmployer() {
        return employer;
    }

    public void setEmployer(Restaurant employer) {
        this.employer = employer;
    }
}
