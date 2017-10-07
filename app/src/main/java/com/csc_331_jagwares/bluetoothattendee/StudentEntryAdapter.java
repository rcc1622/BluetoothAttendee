package com.csc_331_jagwares.bluetoothattendee;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alex on 10/6/17.
 */

public class StudentEntryAdapter extends ArrayAdapter {

    // Holds the list of classes from the database to be added to the ListView.
    private ArrayList<StudentEntry> studentEntries;

    public StudentEntryAdapter(Context context, ArrayList<StudentEntry> studentEntries) {
        super(context, 0, studentEntries);
        this.studentEntries = studentEntries;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.student_entry, null);

        // Set object values to the ListView layout elements.
        StudentEntry studentEntry = (StudentEntry) getItem(position);
        TextView tvStudentName = view.findViewById(R.id.tvStudentName);
        TextView tvStudentId = view.findViewById(R.id.tvStudentId);

        if (studentEntry != null) {
            tvStudentName.setText(studentEntry.getName());
            tvStudentId.setText(studentEntry.getId());
        }

        return view;
    }

}
