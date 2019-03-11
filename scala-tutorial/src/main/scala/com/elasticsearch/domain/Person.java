package com.elasticsearch.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Person {

    @JSONField(name = "personId", ordinal = 1)
    private String personId;

    @JSONField(name = "firstName")
    private String firstName;

    @JSONField(name = "lastName")
    private String lastName;

    @JSONField(name = "address")
    private String address;

    @JSONField(name = "dateOfBirth")
    private Date dateOfBirth;

    @JSONField(name = "age")
    private Integer age;

    public Person() {
    }

    public Person(String personId, String firstName, String lastName, String address, Date dateOfBirth, Integer age) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId='" + personId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
