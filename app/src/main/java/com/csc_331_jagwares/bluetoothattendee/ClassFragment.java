package com.csc_331_jagwares.bluetoothattendee;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends Fragment {

    ArrayList<String> classes;

    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Classes");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class, container, false);

        // Populate list view with classes.\
        // This array would come from the db.
        classes = new ArrayList<>();
        classes.add("CSC 311");
        classes.add("CSC 322");
        classes.add("CSC 331");
        classes.add("EH 215");
        populateListView(view, classes);

        registerClickCallback(view);

        return view;

    }

    private void populateListView(View view, ArrayList<String> classes) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.class_entry,
                classes
        );

        ListView lvClassList = view.findViewById(R.id.lvClassList);
        lvClassList.setAdapter(adapter);
    }

    private void registerClickCallback(View view) {

        ListView list = view.findViewById(R.id.lvClassList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedData = classes.get(position);
                Toast.makeText(getActivity().getBaseContext(), selectedData, Toast.LENGTH_LONG).show();
            }
        });
    }

}
