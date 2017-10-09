package com.csc_331_jagwares.bluetoothattendee.persistence.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import com.csc_331_jagwares.bluetoothattendee.persistence
        .AttendeeDatasource;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by steven on 10/3/2017.
 */
public class Class extends Model {

    private String className;

    /**
     * @param datasource
     * @param className
     */
    public Class(AttendeeDatasource datasource, String className) {
        super(datasource);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

//    public void setClassName(String className) {
//        // TODO: This must be dynamic.
//    }

}
