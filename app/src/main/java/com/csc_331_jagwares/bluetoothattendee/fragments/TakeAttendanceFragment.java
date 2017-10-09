package com.csc_331_jagwares.bluetoothattendee.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csc_331_jagwares.bluetoothattendee.R;
import com.csc_331_jagwares.bluetoothattendee.models.Class;
import com.csc_331_jagwares.bluetoothattendee.models.Student;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeAttendanceFragment extends Fragment {

    private Class classEntry;
    private ArrayList<Student> students;


    public TakeAttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Set the main activity title.
        getActivity().setTitle("Take Attendance");
        // Inflate the layout for this fragment.
        View view = inflater.inflate(R.layout.fragment_take_attendance, container, false);

        // Get classEntry from ClassFragment.
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            classEntry = bundle.getParcelable("classEntry");
        }
        // Get ArrayList of students from the class.
        students = classEntry.getStudents();

        return view;
    }

}
