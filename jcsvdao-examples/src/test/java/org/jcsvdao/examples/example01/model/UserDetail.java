package org.jcsvdao.examples.example01.model;

import java.util.Date;

/**
 * Created by ericmckinley on 07/02/2016.
 */
public class UserDetail {

    private String username;
    private String email;
    private Date registrationDate;
    private Integer age;
    private Boolean premiumUser;
    private Gender gender;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getPremiumUser() {
        return premiumUser;
    }

    public void setPremiumUser(Boolean premiumUser) {
        this.premiumUser = premiumUser;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
