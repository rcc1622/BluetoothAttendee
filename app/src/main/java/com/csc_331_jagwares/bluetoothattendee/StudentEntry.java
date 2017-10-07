package com.csc_331_jagwares.bluetoothattendee;


import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by alex on 10/6/17.
 */

public class StudentEntry implements Parcelable {

    private String name;
    private String id;

    public StudentEntry(String name, String id){
        this.name = name;
        this.id = id;
    }

    void setName(String name) {
        this.name = name;
    }

    void setLocation(String id) {
        this.id = id;
    }

    String getName(){
        return this.name;
    }

    String getId(){
        return this.id;
    }

    protected StudentEntry(Parcel in) {
        name = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<StudentEntry> CREATOR = new Parcelable.Creator<StudentEntry>() {
        @Override
        public StudentEntry createFromParcel(Parcel in) {
            return new StudentEntry(in);
        }

        @Override
        public StudentEntry[] newArray(int size) {
            return new StudentEntry[size];
        }
    };

}
