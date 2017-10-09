package com.csc_331_jagwares.bluetoothattendee.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.csc_331_jagwares.bluetoothattendee.persistence.model.Class;
import com.csc_331_jagwares.bluetoothattendee.persistence.model.Student;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Wrapper for the SQLite database. Must be instantiated with
 * a context. open() must be called before any other method.
 *
 * TODO: Implement read methods.
 * TODO: Escape anything spliced into SQL.
 *       String foo = DatabaseUtils.sqlEscapeString(bar);
 * TODO: Add exists checks to all write methods. ???
 */
public class AttendeeDatasource {
    private SQLiteDatabase db;  // XXX Never initialized. Get this from helper.
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
        dbHelper.close();
    }


    // Begin write methods.
    // ====================

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
    // =================
    // End write methods

    // Begin read methods
    // ==================
    /**
     *
     * @return Vector<Class>
     */
    public ArrayList<Class> getAllClasses() {
        String[] columns = {dbHelper.COLUMN_CLASS_NAME};
        Cursor c = db.rawQuery("SELECT * FROM tblClass;", null);
        ArrayList<Class> results = new ArrayList<Class>();
        while (!c.isAfterLast()) {
            results.add(new Class(this, c.getString(0)));
        }
        return results;
    }

    private String UNREGISTERED_STUDENTS_QUERY =
            "SELECT * FROM ( \n"
                    + "SELECT tblStudent.jagNumber, tblStudent.firstName, \n"
                    + "tblStudent.lastName, tblEnrollment.className \n"
                    + "FROM tblStudent LEFT JOIN tblEnrollment \n"
                    + "ON tblStudent.jagNumber = tblEnrollment.jagNumber \n"
                    +") \n"
                    + "WHERE className IS NULL";

    public ArrayList<Student> getUnregisteredStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        Cursor c = db.rawQuery(UNREGISTERED_STUDENTS_QUERY, null);
        while (!c.isAfterLast()) {
            students.add(new Student(this, c.getString(0),
                    c.getString(1), c.getString(2)));
        }
        return students;
    }


    private boolean cursorIsEmpty(Cursor cursor) {
        return cursor.moveToFirst();
    }

    // Begin existence checks
    // ======================

    /**
     * Return true if a record of the student exists, else false.
     *
     * @param jagNumber
     * @return boolean
     */
    public boolean studentExists(String jagNumber) {
        Cursor c = db.rawQuery(
                "SELECT * FROM tblStudent WHERE jagNumber = ?",
                new String[]{jagNumber}
        );
        return !cursorIsEmpty(c);
    }

    /**
     * Return true if class exists, else false
     *
     * @param className
     * @return
     */
    public boolean classExists(String className) {
        Cursor c = db.rawQuery(
                "SELECT * FROM tblClass WHERE className = ?",
                new String[]{className}
        );
        return !cursorIsEmpty(c);
    }

    /**
     * Return true if student is enrolled in class.
     *
     * @param jagNumber
     * @param className
     * @return boolean
     */
    public boolean studentInClass(String jagNumber, String className) {
        Cursor c = db.rawQuery("SELECT * FROM tblEnrollment", null);
        return !cursorIsEmpty(c);
    }
}
