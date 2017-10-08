package com.csc_331_jagwares.bluetoothattendee;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ClassEntryAdapter extends ArrayAdapter {

    // Holds the list of classes from the database to be added to the ListView.
    private ArrayList<ClassEntry> classEntries;

    public ClassEntryAdapter(Context context, ArrayList<ClassEntry> studentEntries) {
        super(context, 0, studentEntries);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.class_entry, null);

        // Set object values to the ListView layout elements.
        ClassEntry classEntry = (ClassEntry) getItem(position);
        TextView tvClassName = view.findViewById(R.id.tvClassName);
        TextView tvClassTitle = view.findViewById(R.id.tvClassTitle);
        TextView tvClassLocation = view.findViewById(R.id.tvClassLocation);
        TextView tvClassDays = view.findViewById(R.id.tvClassDays);
        TextView tvClassTime = view.findViewById(R.id.tvClassTime);

        if (classEntry != null) {
            tvClassName.setText(classEntry.getName() + "-" + classEntry.getSection());
            tvClassTitle.setText(classEntry.getTitle());
            tvClassLocation.setText(classEntry.getLocation());
            tvClassDays.setText("Days: " + classEntry.getDays());
            tvClassTime.setText(classEntry.getTime());
        }

        return view;
    }

}