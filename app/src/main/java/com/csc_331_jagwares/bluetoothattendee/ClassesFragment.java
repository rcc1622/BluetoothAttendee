package com.csc_331_jagwares.bluetoothattendee;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassesFragment extends Fragment {

    // Holds all classes from database.
    private ArrayList<ClassEntry> classEntries;

    public ClassesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Set title of the fragment to "Classes"
        getActivity().setTitle("Classes");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classes, container, false);

        // Populate list view with classes.
        // This array would come from the database.
        classEntries = new ArrayList<>();
        ClassEntry class1 = new ClassEntry("CSC 331", "Software Engineering", "101", "SHEC 1000", "MWF", "3:00PM - 4:00PM");
        ClassEntry class2 = new ClassEntry("CSC 322", "Operating Systems", "102", "SHEC 1001", "TR", "1:00AM - 2:30AM");
        ClassEntry class3 = new ClassEntry("CSC 311", "Networking and Communications", "104", "SHEC 1002", "T", "5:00PM - 6:00PM");
        ClassEntry class4 = new ClassEntry("CSC 320", "Computer Organization and Architecture", "102", "SHEC 1003", "MWF", "12:00PM - 1:00PM");
        ClassEntry class5 = new ClassEntry("CSC 120", "Intro to Programming", "202", "SHEC 1004", "MW", "7:00PM - 8:15PM");
        ClassEntry class6 = new ClassEntry("CSC 108", "Intro to CS", "101", "SHEC 1004", "MWF", "3:00PM - 4:00PM");
        ClassEntry class7 = new ClassEntry("CSC 400", "Something Cool", "104", "SHEC 1004", "TR", "3:15PM - 4:30PM");

        classEntries.add(class1);
        classEntries.add(class2);
        classEntries.add(class3);
        classEntries.add(class4);
        classEntries.add(class5);
        classEntries.add(class6);
        classEntries.add(class7);

        // Add the classes from the ArrayList to the ListView.
        populateListView(view, classEntries);

        // Listen for a ListView entry selection.
        registerClickCallback(view);

        return view;
    }

    private void populateListView(View view, ArrayList<ClassEntry> classes) {
        // Create the adapter to convert the array to views
        ClassEntryAdapter adapter = new ClassEntryAdapter(getContext(), classes);

        // Attach the adapter to a ListView
        ListView lvClassList = view.findViewById(R.id.lvClassList);
        lvClassList.setAdapter(adapter);
    }

    private void registerClickCallback(View view) {
        ListView list = view.findViewById(R.id.lvClassList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassEntry entry = classEntries.get(position);

                Fragment fragment = new ClassFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("Class Entry", entry);
                fragment.setArguments(bundle);

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainLayout, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

}
