package com.csc_331_jagwares.bluetoothattendee.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.csc_331_jagwares.bluetoothattendee.R;
import com.csc_331_jagwares.bluetoothattendee.fragments.ClassesFragment;
import com.csc_331_jagwares.bluetoothattendee.fragments.HelpFragment;
import com.csc_331_jagwares.bluetoothattendee.fragments.ReportsFragment;
import com.csc_331_jagwares.bluetoothattendee.fragments.SettingsFragment;
import com.csc_331_jagwares.bluetoothattendee.models.Class;
import com.csc_331_jagwares.bluetoothattendee.models.Instructor;
import com.csc_331_jagwares.bluetoothattendee.models.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Instructor instructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setup the layout of the activity.
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Initialize instructor object.
        // This would be retrieved from the database.
        instructor = new Instructor("Dr.", "Ryan", "Benton", "rbenton@southalabama.edu");
        // Add sample classes.
        Class class1 = new Class("CSC 331", "102", "Software Engineering Principles", "36561", "MWF" , "3:00PM-4:45PM", "Fall 2016", "SHEC 2222");
        Class class2 = new Class("CSC 320", "101", "Computer Organization and Architecture", "5422", "MWF" , "4:00PM-4:50PM", "Fall 2016", "SHEC 2122");
        Class class3 = new Class("CSC 311", "103", "Networks and Communication", "32654", "MWF" , "12:00PM-12:50PM", "Fall 2016", "SHEC 2232");
        Class class4 = new Class("CSC 120", "101", "Intro to Programming", "84522", "TR" , "3:00PM-4:45PM", "Fall 2016", "SHEC 3222");
        Class class5 = new Class("CSC 108", "102", "Intro to Computer Science", "3236", "TR" , "3:00PM-4:45PM", "Fall 2016", "SHEC 2222");
        Student student1 = new Student("J00589451", "Alex", "Dudenhoeffer", "aad1621@jagmail.southalabama.edu");
        Student student2 = new Student("J00345451", "Steven", "Mauseth", "swm1621@jagmail.southalabama.edu");
        Student student3 = new Student("J00589424", "Kaitlyn", "Gaiger", "kdg1621@jagmail.southalabama.edu");
        Student student4 = new Student("J00543451", "Fernando", "Lorenzo", "fl1621@jagmail.southalabama.edu");
        Student student5 = new Student("J00436788", "Robert", "Cox", "aad1621@jagmail.southalabama.edu");
        class1.addStudent(student1);
        class1.addStudent(student3);
        class1.addStudent(student5);
        class2.addStudent(student1);
        class2.addStudent(student2);
        class2.addStudent(student3);
        class2.addStudent(student4);
        class2.addStudent(student5);
        class3.addStudent(student2);
        class3.addStudent(student3);
        class4.addStudent(student4);
        class5.addStudent(student5);
        class5.addStudent(student1);
        instructor.addClass(class1);
        instructor.addClass(class2);
        instructor.addClass(class3);
        instructor.addClass(class4);
        instructor.addClass(class5);

        // Update navigation drawer profile TextViews.
        View header = navigationView.getHeaderView(0);
        TextView tvHeaderName = header.findViewById(R.id.tvInstructorName);
        TextView tvHeaderEmail = header.findViewById(R.id.tvInstructorEmail);
        tvHeaderName.setText(instructor.getFullName());
        tvHeaderEmail.setText(instructor.getEmailAddress());

        // Checks first item in the navigation drawer initially.
        navigationView.setCheckedItem(R.id.nav_classes);

        // Open ClassesFragment initially.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainLayout, new ClassesFragment());
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the action menu options.
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_classes) {
            fragment = new ClassesFragment();
        } else if (id == R.id.nav_reports){
            fragment = new ReportsFragment();
        } else if (id == R.id.nav_settings){
            fragment = new SettingsFragment();
        } else if (id == R.id.nav_help){
            fragment = new HelpFragment();
        }

        // Switch to selected fragment.
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainLayout, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public ArrayList<Class> getClasses() {
        // This method is used to send an ArrayList of classes to the fragments.
        return instructor.getClasses();
    }

}
