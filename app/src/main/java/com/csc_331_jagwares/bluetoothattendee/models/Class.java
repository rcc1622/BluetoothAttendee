package com.csc_331_jagwares.bluetoothattendee.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.csc_331_jagwares.bluetoothattendee.models.Student;

import java.util.ArrayList;

public class Class implements Parcelable {

    private String number;
    private String section;
    private String title;

    private ArrayList<Student> students;

    public Class() {
        this.number = null;
        this.section = null;
        this.title = null;
        this.students = new ArrayList<>();
    }

    public Class(String number, String section, String title) {
        this.number = number;
        this.section = section;
        this.title = title;

        this.students = new ArrayList<>();
    }

    public Class(String number, String section, String title, ArrayList<Student> students) {
        this.number = number;
        this.section = section;
        this.title = title;

        this.students = students;
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

    public Class(Parcel in) {
        number = in.readString();
        section = in.readString();
        title = in.readString();
        students = new ArrayList<>(); //non-null reference is required
        in.readTypedList(students, Student.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number);
        dest.writeString(section);
        dest.writeString(title);
        dest.writeTypedList(students);
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
