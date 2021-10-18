package com.example.kafshapp.model;

public class Person {
    private int id;
    private String namePerson;
    private String passwordPerson;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public String getPasswordPerson() {
        return passwordPerson;
    }

    public void setPasswordPerson(String passwordPerson) {
        this.passwordPerson = passwordPerson;
    }

    public String getPhonePerson() {
        return phonePerson;
    }

    public void setPhonePerson(String phonePerson) {
        this.phonePerson = phonePerson;
    }

    private String phonePerson;
}