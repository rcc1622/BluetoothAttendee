package com.csc_331_jagwares.bluetoothattendee;

/**
 * Created by fzlor on 10/2/2017.
 */

public class ClassEntry {

    private String name;
    private String location;

    public ClassEntry(String name, String location){
        this.name = name;
        this.location = location;
    }

    String getName(){
        return this.name;
    }

    String getLocation(){
        return this.location;
    }
}
