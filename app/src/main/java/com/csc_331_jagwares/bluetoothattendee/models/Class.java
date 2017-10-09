package com.csc_331_jagwares.bluetoothattendee.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Class implements Parcelable {

    private String number;
    private String section;
    private String title;

    private ArrayList<Student> students;

    public Class(String number, String section, String title) {
        this.number = number;
        this.section = section;
        this.title = title;

        this.students = new ArrayList<>();
    }

    public String getNumber(){
        return this.number;
    }

    public String getSection(){
        return this.section;
    }

    public String getTitle(){
        return this.title;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public void addStudent(Student studentEntry) {
        this.students.add(studentEntry);
    }

    protected Class(Parcel in) {
        this.number = in.readString();
        this.title = in.readString();
        this.section = in.readString();
        this.students = in.createTypedArrayList(Student.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.number);
        dest.writeString(this.title);
        dest.writeString(this.section);
        dest.writeTypedList(this.students);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Class> CREATOR = new Parcelable.Creator<Class>() {
        @Override
        public Class createFromParcel(Parcel in) {
            return new Class(in);
        }

        @Override
        public Class[] newArray(int size) {
            return new Class[size];
        }
    };

}
