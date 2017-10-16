package com.csc_331_jagwares.bluetoothattendee.persistence.model;

import android.database.sqlite.SQLiteOpenHelper;

import com.csc_331_jagwares.bluetoothattendee.persistence
        .AttendeeDatasource;

/**
 * Created by steven on 10/3/2017.
 */

public class Student extends Model {

    private String jagNumber;
    private String firstName;
    private String lastName;

    /**
     * @param datasource
     * @param jagNumber
     * @param firstName
     * @param lastName
     */
    public Student (AttendeeDatasource datasource, String jagNumber, String firstName, String lastName) {
        super(datasource);
        this.jagNumber = jagNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getJagNumber() {
        return jagNumber;
    }

    public void setJagNumber(String jagNumber) {
        // TODO: dynamic
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        // TODO: dynamic
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void save() {
        /**
         * TODO
         */
    }
}
