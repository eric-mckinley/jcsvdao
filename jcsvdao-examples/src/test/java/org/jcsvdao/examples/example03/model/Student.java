package org.jcsvdao.examples.example03.model;

/**
 * Created by ericmckinley on 07/02/2016.
 *
 */
public class Student {

    private Long studentId;
    private String firstName;
    private String lastName;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
