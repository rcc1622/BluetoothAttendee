package com.csc_331_jagwares.bluetoothattendee.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    private String firstName;
    private String lastName;
    private String fullName;
    private String id;
    private String emailAddress;
    private String macAddress;

    public Student(String id, String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = this.firstName + " " + this.lastName;
        this.id = id;
        this.emailAddress = emailAddress;

        this.macAddress = null;
    }

    public Student(String id, String firstName, String lastName, String emailAddress, String macAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = this.firstName + " " + this.lastName;
        this.id = id;
        this.emailAddress = emailAddress;

        this.macAddress = macAddress;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getId() {
        return this.id;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    protected Student(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.fullName = in.readString();
        this.id = in.readString();
        this.emailAddress = in.readString();
        this.macAddress = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.fullName);
        dest.writeString(this.id);
        dest.writeString(this.emailAddress);
        dest.writeString(this.macAddress);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

}
