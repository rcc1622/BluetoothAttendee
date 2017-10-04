package com.csc_331_jagwares.bluetoothattendee;


import android.os.Parcel;
import android.os.Parcelable;


public class ClassEntry implements Parcelable {

    private String name;
    private String location;

    public ClassEntry(String name, String location){
        this.name = name;
        this.location = location;
    }

    void setName(String name) {
        this.name = name;
    }

    void setLocation(String location) {
        this.location = location;
    }

    String getName(){
        return this.name;
    }

    String getLocation(){
        return this.location;
    }

    protected ClassEntry(Parcel in) {
        name = in.readString();
        location = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
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