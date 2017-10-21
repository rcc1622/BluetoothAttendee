package com.csc_331_jagwares.bluetoothattendee.persistence.suite;

//import android.support.test.filters.SmallTest;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.csc_331_jagwares.bluetoothattendee.persistence
        .AttendeeDatasource;
import com.csc_331_jagwares.bluetoothattendee.persistence.model.Class;
import com.csc_331_jagwares.bluetoothattendee.persistence.model.Student;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by steven on 10/7/2017.
 */
@RunWith(AndroidJUnit4.class)
public class AttendeeDatasourceTest {
    static AttendeeDatasource datasource;
    static Context context = InstrumentationRegistry.getTargetContext();
    static File dbFile = new File(context.getFilesDir(), "foo.db");

    @BeforeClass
    public static void setUp() throws Exception {
        if (dbFile.exists()) {
            dbFile.delete();
        }
        dbFile.createNewFile();
        datasource = new AttendeeDatasource(dbFile.getPath());
        datasource.open();
        datasource.initializeDatabase();
        datasource.insertClass("Underwater Basket Weaving");
        datasource.insertClass("Calculus");
        datasource.insertClass("Pigonometry");
        datasource.insertStudent(
                "J99999999", "Jimmy", "James",
                "jimmyjames@foo.bar", "00-14-22-01-23-45"
                );
        datasource.insertStudent(
                "J88888888", "Willy", "Wonka",
                "willywonka@foo.bar", "00-14-22-01-23-46");
        datasource.enrollStudent("J99999999", "Underwater Basket Weaving");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        datasource.close();
    }

    /*
    Begin Class model tests.
     */
    @Test
    public void testClassExists() throws Exception {
        assertTrue(datasource.classExists("Underwater Basket Weaving"));
    }

    @Test
    public void testGetClassByName() throws Exception {
        Class cls = datasource.getClassByName("Underwater Basket Weaving");
        assertTrue(cls.getClassName().equals("Underwater Basket Weaving"));
    }
    @Test
    public void testGetAllClasses() throws Exception {
        assertTrue(datasource.getAllClasses().size() == 3);
    }

    /*
    Begin Student model tests.
     */
    @Test
    public void testStudentExists() throws Exception {
        assertTrue(datasource.studentExists("J99999999"));
    }

    @Test
    public void testGetStudentByJagNumber() throws Exception {
        Student student = datasource.getStudentByJagNumber("J88888888");
        assertTrue(student.getFirstName().equals("Willy"));
    }

    @Test
    public void testStudentInClass() throws Exception {
        assertTrue(datasource.studentInClass("J99999999", "Underwater Basket Weaving"));
        assertFalse(datasource.studentInClass("J88888888", "Underwater Basket Weaving"));
    }

    @Test
    public void testGetStudentsInClass() {
        Class cls = datasource.getClassByName("Underwater Basket Weaving");
        ArrayList<Student> students = cls.getStudents();
        assertTrue(students.size() == 1);
        assertTrue(students.get(0).getFirstName().equals("Jimmy"));
    }

    @Test
    public void testSaveStudent() {
        Student jimmy = datasource.getStudentByJagNumber("J99999999");
        assertNotNull(jimmy);
        jimmy.setLastName("Jack");
        jimmy.save();
        jimmy = datasource.getStudentByJagNumber("J99999999");
        assertTrue(jimmy.getLastName().equals("Jack"));
    }
}