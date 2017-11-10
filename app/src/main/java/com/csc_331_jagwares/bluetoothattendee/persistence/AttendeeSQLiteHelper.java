package com.csc_331_jagwares.bluetoothattendee.persistence;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by steven on 10/3/2017.
 *
 * * TODO: Precompile SQL?

 * Based on tutorial code taken from:
 * https://github.com/thedeveloperworldisyours/SQLiteExample
 */
public class AttendeeSQLiteHelper extends SQLiteOpenHelper {
    // Database-level constants
    private static final String DATABASE_NAME = "attendee.db";
    private static final int DATABASE_VERSION = 1;

    // Constants for tblClass
    public static final String TABLE_CLASS = "tblClass";
    public static final String COLUMN_CLASS_NAME = "className";
    public static final String[] ALL_CLASS_COLUMNS = {
            COLUMN_CLASS_NAME
    };

    // Constants for tblStudent
    public static final String TABLE_STUDENT = "tblStudent";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_JAGNUMBER = "jagNumber";
    public static final String[] ALL_STUDENT_COLUMNS = {
            COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_JAGNUMBER
    };

    // Constants for tblEnrollment
    public static final String TABLE_ENROLLMENT = "tblEnrollment";
    // Shares column names with other tables.
    public static final String[] ALL_ENROLLMENT_COLUMNS = {
            COLUMN_CLASS_NAME, COLUMN_JAGNUMBER
    };

    // Table creation statements
    private static final String SQL_PROLOG = "PRAGMA foreign_keys = ON;\n";

    private static final String TABLE_CLASS_CREATE =
            "CREATE TABLE " + TABLE_CLASS + "( "
            + COLUMN_CLASS_NAME + " TEXT PRIMARY KEY"
            + "); \n"
            ;

    private static final String TABLE_STUDENT_CREATE =
            "CREATE TABLE " + TABLE_STUDENT + " ("
            + COLUMN_JAGNUMBER + " TEXT PRIMARY KEY, "
            + COLUMN_FIRST_NAME + " TEXT,"
            + COLUMN_LAST_NAME + " TEXT"
            + "); \n"
            ;

    private static final String TABLE_ENROLLMENT_CREATE =
            "CREATE TABLE " + TABLE_ENROLLMENT + "( "
            + COLUMN_CLASS_NAME + " TEXT REFERENCES "
                + TABLE_CLASS + "(" + COLUMN_CLASS_NAME + "), "
            + COLUMN_JAGNUMBER + " TEXT REFERENCES "
                + TABLE_STUDENT + "(" + COLUMN_JAGNUMBER + ")"
            + "); \n"
            ;

    private static final String DATABASE_CREATE =
              SQL_PROLOG + TABLE_CLASS_CREATE
            + TABLE_STUDENT_CREATE + TABLE_ENROLLMENT_CREATE;

    public AttendeeSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(AttendeeSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENROLLMENT);
        onCreate(db);
    }
}
