package com.csc_331_jagwares.bluetoothattendee.activities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.csc_331_jagwares.bluetoothattendee.R;
import com.csc_331_jagwares.bluetoothattendee.fragments.ClassFragment;
import com.csc_331_jagwares.bluetoothattendee.models.Class;

public class ClassActivity extends AppCompatActivity {

    private Class classEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        // Receive a class object.
        try {
            Intent intent = getIntent();
            classEntry = intent.getExtras().getParcelable("classEntry");
        } catch(Exception e) {
            e.printStackTrace();
        }

        // Open ClassFragment initially.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainLayout, new ClassFragment());
        ft.commit();
    }

    public Class getClassEntry() {
        // This method is used to send a class to a fragment.
        return classEntry;
    }

}
