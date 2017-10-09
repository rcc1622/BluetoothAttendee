package com.csc_331_jagwares.bluetoothattendee.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.csc_331_jagwares.bluetoothattendee.R;
import com.csc_331_jagwares.bluetoothattendee.adapters.StudentEntryAdapter;
import com.csc_331_jagwares.bluetoothattendee.models.Class;
import com.csc_331_jagwares.bluetoothattendee.models.Student;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterDevicesFragment extends Fragment {

    private Class classEntry;
    private ArrayList<Student> students;

    public RegisterDevicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Set the main activity title.
        getActivity().setTitle("Register Devices");
        // Inflate the layout for this fragment.
        View view = inflater.inflate(R.layout.fragment_register_devices, container, false);

        // Get classEntry from ClassFragment.
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            classEntry = bundle.getParcelable("classEntry");
        }
        // Get ArrayList of students from the class.
        students = classEntry.getStudents();

        // Setup register devices button.
        final Button registerDevicesBtn = view.findViewById(R.id.registerDevicesBtn);
        registerDevicesBtn.setOnClickListener(new View.OnClickListener()
        {
            boolean clicked = false;

            @Override
            public void onClick(View v)
            {
                if (clicked) {
                    registerDevicesBtn.setText("Register Devices");
                    clicked = false;
                } else {
                    registerDevicesBtn.setText("Stop Registering Devices");
                    clicked = true;
                }
            }
        });

        // Add the students from the ArrayList to the ListView.
        if (students != null) {
            populateListView(view, students);
        }

        // Listen for a ListView entry selection.
        registerClickCallback(view);

        return view;
    }

    private void populateListView(View view, ArrayList<Student> students) {
        // Create the adapter to convert the array to views
        StudentEntryAdapter adapter = new StudentEntryAdapter(getContext(), students);

        // Attach the adapter to a ListView
        ListView lvStudentList = view.findViewById(R.id.lvStudentList);
        lvStudentList.setAdapter(adapter);
    }

    private void registerClickCallback(View view) {
        ListView list = view.findViewById(R.id.lvStudentList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Student studentEntry = students.get(position);

            }
        });
    }

}
