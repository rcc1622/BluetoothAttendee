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
        ClassEntry class1 = new ClassEntry("CSC 331", "SHEC 1000");
        ClassEntry class2 = new ClassEntry("CSC 322", "SHEC 1001");
        ClassEntry class3 = new ClassEntry("CSC 311", "SHEC 1002");
        ClassEntry class4 = new ClassEntry("CSC 320", "SHEC 1003");
        ClassEntry class5 = new ClassEntry("CSC 120", "SHEC 1004");

        classEntries.add(class1);
        classEntries.add(class2);
        classEntries.add(class3);
        classEntries.add(class4);
        classEntries.add(class5);

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
