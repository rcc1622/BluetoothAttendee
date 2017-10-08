package com.csc_331_jagwares.bluetoothattendee;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    // Holds all students from database.
    private ArrayList<StudentEntry> studentEntries;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Set title of the fragment to "Register Students".
        getActivity().setTitle("Register Students");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        // Populate list view with students.
        // This array would come from the database.
        studentEntries = new ArrayList<>();
        StudentEntry student1 = new StudentEntry("Alex Dudenhoeffer", "J00123456");
        StudentEntry student2 = new StudentEntry("Steven Mauseth", "J00111111");
        StudentEntry student3 = new StudentEntry("Robert Cox", "J00222222");
        StudentEntry student4 = new StudentEntry("Kaitlyn Gaiger", "J00333333");
        StudentEntry student5 = new StudentEntry("Fernando Lorenzo", "J00444444");

        student3.setDeviceRegisteredDate("09/17/17");
        student5.setDeviceRegisteredDate("10/03/17");

        studentEntries.add(student1);
        studentEntries.add(student2);
        studentEntries.add(student3);
        studentEntries.add(student4);
        studentEntries.add(student5);

        final Button registerDevicesBtn = (Button) view.findViewById(R.id.registerDevicesBtn);


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

        // Add the studentes from the ArrayList to the ListView.
        populateListView(view, studentEntries);

        // Listen for a ListView entry selection.
        registerClickCallback(view);

        return view;
    }

    private void populateListView(View view, ArrayList<StudentEntry> students) {
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
                StudentEntry entry = studentEntries.get(position);

/*                Fragment fragment = new ClassFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("Student Entry", entry);
                fragment.setArguments(bundle);

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainLayout, fragment);
                ft.addToBackStack(null);
                ft.commit();*/
            }
        });
    }
}
