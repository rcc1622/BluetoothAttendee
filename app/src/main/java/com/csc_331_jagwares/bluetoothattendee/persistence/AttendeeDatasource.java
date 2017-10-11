package com.csc_331_jagwares.bluetoothattendee.persistence;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.csc_331_jagwares.bluetoothattendee.persistence.model.Class;


import com.csc_331_jagwares.bluetoothattendee.persistence.model.Student;

import java.io.File;
import java.util.ArrayList;

public class AttendeeDatasource {
    private File dbPath;
    private SQLiteDatabase db;

    public AttendeeDatasource(String dbPath) {
        this.dbPath = new File(dbPath);
    }

    public void open() throws SQLException {
        this.db = SQLiteDatabase.openDatabase(
                dbPath.getPath(), null,
                SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    /**
     * Drop any existing tables and recreate them. Must be called when
     * opening the database for the first time.
     *
     * @throws SQLException
     */
    public void initializeDatabase() throws SQLException {
        //db.execSQL("DELETE FROM SQLITE_MASTER");
        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("CREATE TABLE tblClass (className TEXT PRIMARY KEY)");
        db.execSQL("CREATE TABLE tblStudent ( \n"
            + "jagNumber TEXT PRIMARY KEY, "
            + "firstName TEXT, \n"
            + "lastname TEXT)"
        );
        db.execSQL("CREATE TABLE tblEnrollment ( \n"
            + "jagNumber TEXT REFERENCES tblStudent(jagNumber), \n"
            + "className TEXT REFERENCES tblClass(className))"
        );
    }

    /**
     * Close the data source.
     */
    public void close() {
        db.close();
    }

    // Begin write methods.
    // ====================

    /**
     * Create a new class with the given name.
     *
     * @param className
     * @return void
     */
    public void addClass(String className) {
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO tblClass(className) VALUES (?)",
                    new String[]{className}
            );
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    /**
     * Create a new student with the given attributes.
     *
     * @param jagNumber
     * @param firstName
     * @param lastName
     * @return void
     */
    public void addStudent(String jagNumber, String firstName, String lastName) {
        db.beginTransaction();
        try {
            db.execSQL(
                    "INSERT INTO tblStudent (jagNumber, firstName, lastName) "
                            + "VALUES (?, ?, ?)",
                    new String[]{jagNumber, firstName, lastName}
            );
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    /**
     * Add student with a given Jag Number to the class.
     *
     * @param jagNumber
     * @param className
     * @return
     */
    public void enrollStudent(String jagNumber, String className) {
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO tblEnrollment(jagNumber, className) "
                            + "VALUES (?, ?)",
                    new String[]{jagNumber, className}
            );
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    // =================
    // End write methods

    // Begin read methods
    // ==================

    /**
     * Return class with the given name, else null;
     *
     * @param className
     * @return Class
     */
    public Class getClassByName(String className) {
        Cursor c = db.rawQuery("SELECT className FROM tblClass WHERE className = ?",
                new String[]{className}
        );
        if (c.moveToNext()) {
            return new Class(this, c.getString(0));
        } else {
            return null;
        }
    }

    public ArrayList<Class> getAllClasses() {
        Cursor c = db.rawQuery("SELECT * FROM tblClass;", null);
        ArrayList<Class> results = new ArrayList<Class>();
        while (c.moveToNext()) {
            results.add(new Class(this, c.getString(0)));
        }
        return results;
    }

    /**
     * Return student with given Jag number, else null.
     *
     * @param jagNumber
     * @return
     */
    public Student getStudentByJagNumber(String jagNumber) {
        Cursor c = db.rawQuery("SELECT * FROM tblStudent WHERE jagNumber = ?",
                new String[]{jagNumber});
        if (c.moveToNext()) {
            return new Student(this, c.getString(0), c.getString(1), c.getString(2));
        }
        return null;
    }

//    private String UNREGISTERED_STUDENTS_QUERY =
//            "SELECT * FROM ( \n"
//                    + "SELECT tblStudent.jagNumber, tblStudent.firstName, \n"
//                    + "tblStudent.lastName, tblEnrollment.className \n"
//                    + "FROM tblStudent LEFT JOIN tblEnrollment \n"
//                    + "ON tblStudent.jagNumber = tblEnrollment.jagNumber \n"
//                    +") \n"
//                    + "WHERE className IS NULL";
//
//    public ArrayList<Student> getUnregisteredStudents() {
//        ArrayList<Student> students = new ArrayList<Student>();
//        Cursor c = db.rawQuery(UNREGISTERED_STUDENTS_QUERY, null);
//        while (!c.isAfterLast()) {
//            students.add(new Student(this, c.getString(0),
//                    c.getString(1), c.getString(2)));
//        }
//        return students;
//    }

//    String STUDENTS_IN_CLASS_QUERY =
//            "SELECT tblStudent.jagNumber, tblStudent.firstName, tblStudent.lastName \n"
//            + "FROM tblStudent \n"
//            + "JOIN (tblEnrollment JOIN tblClass \n"
//                   + "ON tblEnrollment.className = tblClass.className) \n"
//            + "ON tblStudent.jagNumber = tblEnrollment.jagNumber \n"
//            + "WHERE tblClass.className = ? ";
//
//    public ArrayList<Student> getStudentsInClass(String className) {
//        Cursor c = db.rawQuery(STUDENTS_IN_CLASS_QUERY,
//                new String[]{DatabaseUtils.sqlEscapeString(className)});
//        ArrayList<Student> students = new ArrayList<>();
//        while (c.moveToNext()) {
//            students.add(new Student(
//                    this,
//                    DatabaseUtils.sqlEscapeString(c.getString(0)),
//                    DatabaseUtils.sqlEscapeString(c.getString(1)),
//                    DatabaseUtils.sqlEscapeString(c.getString(2)))
//            );
//        }
//        return students;
//    }

    /**
     * Return true if student is enrolled in class.
     *
     * @param jagNumber
     * @param className
     * @return boolean
     */
    public boolean studentInClass(String jagNumber, String className) {
        Cursor c = db.rawQuery("SELECT * FROM tblEnrollment WHERE "
                        + "jagNumber = ? and className = ?",
                new String[]{jagNumber, className}
        );
        return c.moveToFirst();
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
        return c.moveToFirst(); //Returns false if cursor empty.
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
        return c.moveToFirst();
    }
}
