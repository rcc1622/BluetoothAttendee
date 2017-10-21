package com.csc_331_jagwares.bluetoothattendee.persistence.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;

import com.csc_331_jagwares.bluetoothattendee.persistence
        .AttendeeDatasource;

/**
 * Created by steven on 10/6/2017.
 */

abstract public class Model {

    AttendeeDatasource datasource;

    public Model(AttendeeDatasource datasource) {
        this.datasource = datasource;
    }

    abstract public void save();

    abstract public ContentValues toContentValues();
}
