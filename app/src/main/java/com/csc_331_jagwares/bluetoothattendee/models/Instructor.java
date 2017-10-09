package com.csc_331_jagwares.bluetoothattendee.models;


import java.util.ArrayList;

public class Instructor {

    private String prefix;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailAddress;

    private ArrayList<Class> classes;

    public Instructor(String prefix, String firstName, String lastName, String emailAddress) {
        this.prefix = prefix;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = prefix + " " + firstName + " " + lastName;
        this.emailAddress = emailAddress;

        this.classes = new ArrayList<>();
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public ArrayList<Class> getClasses() {
        return this.classes;
    }

    public void addClass(Class classEntry) {
        this.classes.add(classEntry);
    }

}
