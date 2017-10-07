package com.csc_331_jagwares.bluetoothattendee.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.csc_331_jagwares.bluetoothattendee.persistence.model.Class;

import java.util.Vector;

/**
 * Wrapper for the SQLite database. Must be instantiated with
 * a context. open() must be called befor any other method.
 *
 * TODO: Implement read methods.
 * TODO: Add exists checks to all write methods
 */
public class AttendeeDatasource {
    private SQLiteDatabase db;
    private AttendeeSQLiteHelper dbHelper;

    /**
     * Instantiate a data source in the given context.
     *
     * @param context
     */
    public AttendeeDatasource(Context context) {
        dbHelper = new AttendeeSQLiteHelper(context);
    }

    /**
     * Open the datasource. Must be called before any other methods.
     * @throws SQLException
     */
    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    /**
     * Close the data source.
     */
    public void close() {
        db.close();
    }

    /**
     * Create a new class with the given name.
     *
     * @param className
     * @return long
     */
    public long addClass(String className) {
        // TODO: Do an exists check first.
        ContentValues row = new ContentValues();
        row.put(dbHelper.COLUMN_CLASS_NAME, className);
        return db.insert(dbHelper.TABLE_CLASS, null, row);
    }

    /**
     *
     * @return Vector<Class>
     */
    public Vector<Class> getAllClasses() {
        String[] columns = {dbHelper.COLUMN_CLASS_NAME};
        Cursor c = db.query(
                dbHelper.TABLE_CLASS,
                columns,
                null,
                null,
                null,
                null,
                null);
        Vector<Class> results = new Vector<Class>();
        while (!c.isAfterLast()) {
            results.add(new Class(dbHelper, c.getString(0)));
        }
        return results;
    }


    /**
     * Create a new student with the given attributes.
     *
     * @param jagNumber
     * @param firstName
     * @param lastName
     * @return
     */
    public long addStudent(String jagNumber, String firstName, String lastName) {
        // TODO: Do an exists check first.
        ContentValues row = new ContentValues();
        row.put(dbHelper.COLUMN_JAGNUMBER, jagNumber);
        row.put(dbHelper.COLUMN_FIRST_NAME, firstName);
        row.put(dbHelper.COLUMN_LAST_NAME, lastName);
        return db.insert(dbHelper.TABLE_STUDENT, null, row);
    }

    /**
     * Add student with a given Jag Number to the class.
     *
     * @param jagNumber
     * @param className
     * @return
     */
    public long enrollStudent(String jagNumber, String className) {
        // TODO: Do an exists check first.
        ContentValues row = new ContentValues();
        row.put(dbHelper.COLUMN_JAGNUMBER, jagNumber);
        row.put(dbHelper.COLUMN_CLASS_NAME, className);
        return db.insert(dbHelper.TABLE_ENROLLMENT, null, row);
    }

    private boolean cursorIsEmpty(Cursor cursor) {
        return cursor.moveToFirst();
    }

    public boolean studentExists(String jagNumber) {
        Cursor c = db.rawQuery(
                "SELECT * FROM tblStudent WHERE jagNumber == ?",
                new String[]{jagNumber}
        );
        return !cursorIsEmpty(c);
    }
}
