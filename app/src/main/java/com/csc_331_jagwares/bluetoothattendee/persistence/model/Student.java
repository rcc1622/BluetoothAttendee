package com.csc_331_jagwares.bluetoothattendee.persistence.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import com.csc_331_jagwares.bluetoothattendee.persistence
        .AttendeeDatasource;

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * TODO: Models should inherit from ContentValues.
 * This will allow them to be passed to certain
 * database methods that use ContentValues to
 * repesent rows destined for insertion. Simplifies
 * packing and unpacking Student attributes between
 * reads/writes.
 *
 * Created by steven on 10/3/2017.
 */

public class Student extends Model {

    private String jagNumber;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String macAddress;

    /**
     *
     * @param datasource
     * @param jagNumber
     * @param firstName
     * @param lastName
     * @param emailAddress
     * @param macAddress
     */
    public Student (AttendeeDatasource datasource, String jagNumber, String firstName, String lastName,
                    String emailAddress, String macAddress) {
        super(datasource);
        this.jagNumber = jagNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.macAddress = macAddress;
    }

    public String getJagNumber() {
        return jagNumber;
    }

    public void setJagNumber(String jagNumber) {
        this.jagNumber = jagNumber;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }


    public void save() {
        datasource.insertStudent(
                jagNumber,
                firstName,
                lastName,
                emailAddress,
                macAddress);
    }
}
