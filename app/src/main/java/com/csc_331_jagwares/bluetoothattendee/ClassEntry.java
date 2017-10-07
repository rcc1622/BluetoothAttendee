package com.csc_331_jagwares.bluetoothattendee;


import android.os.Parcel;
import android.os.Parcelable;


public class ClassEntry implements Parcelable {

    private String name;
    private String title;
    private String location;
    private String days;
    private String time;

    public ClassEntry(String name, String title, String location, String days, String time){
        this.name = name;
        this.title = title;
        this.location = location;
        this.days = days;
        this.time = time;
    }

    void setName(String name) {
        this.name = name;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setLocation(String location) {
        this.location = location;
    }

    void setDays(String days) {
        this.days = days;
    }

    void setTime(String time) {
        this.time = time;
    }

    String getName(){
        return this.name;
    }

    String getTitle(){
        return this.title;
    }

    String getLocation(){
        return this.location;
    }

    String getDays(){
        return this.days;
    }

    String getTime(){
        return this.time;
    }

    protected ClassEntry(Parcel in) {
        name = in.readString();
        title = in.readString();
        location = in.readString();
        days = in.readString();
        time = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(title);
        dest.writeString(location);
        dest.writeString(days);
        dest.writeString(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ClassEntry> CREATOR = new Parcelable.Creator<ClassEntry>() {
        @Override
        public ClassEntry createFromParcel(Parcel in) {
            return new ClassEntry(in);
        }

        @Override
        public ClassEntry[] newArray(int size) {
            return new ClassEntry[size];
        }
    };

}