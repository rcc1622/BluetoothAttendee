package com.csc_331_jagwares.bluetoothattendee.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.csc_331_jagwares.bluetoothattendee.models.Student;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Class implements Parcelable {

    private String number;
    private String section;
    private String title;
    private String crn;
    private String days;
    private String time;
    private String semester;
    private String location;

    private ArrayList<Student> students;
  
    public Class(String number, String section, String title, String crn, String days, String time, String semester, String location) {
        this.number = number;
        this.section = section;
        this.title = title;
        this.crn = crn;
        this.days =days;
        this.time = time;
        this.semester = semester;
        this.location = location;
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

    public String getCrn() {return this.crn;}

    public String getDays() {return  this.days;}

    public String getTime () {return this.time;}

    public String getSemester () {return this.semester;}

    public String getLocation() {return this.location;}


    public void setNumber(){
        this.number = number;
    }

    public void setSection(){
        this.section = section;
    }

    public void setTitle(){
        this.title = title;
    }

    public void setCrn() {this.crn = crn;}

    public void setDays() {this.days = days;}

    public void setTime () {this.time = time;}

    public void setSemester () {this.semester = semester;}

    public void setLocation() {this.location = location;}

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public void addStudent(Student studentEntry) {
        this.students.add(studentEntry);
    }

    public Class(Parcel in) {
        this.number = in.readString();
        this.title = in.readString();
        this.section = in.readString();
        this.crn = in.readString();
        this.days = in.readString();
        this.time = in.readString();
        this.semester = in.readString();
        this.location = in.readString();
        students = new ArrayList<>(); //non-null reference is required
        in.readTypedList(students, Student.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.number);
        dest.writeString(this.title);
        dest.writeString(this.section);
        dest.writeString(this.crn);
        dest.writeString(this.days);
        dest.writeString(this.time);
        dest.writeString(this.semester);
        dest.writeString(this.location);
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
