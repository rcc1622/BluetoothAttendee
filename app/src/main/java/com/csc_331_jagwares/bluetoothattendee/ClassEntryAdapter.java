package com.csc_331_jagwares.bluetoothattendee;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassEntryAdapter extends ArrayAdapter {

    private ArrayList<ClassEntry> classes;

    public ClassEntryAdapter(Context context, ArrayList<ClassEntry> classEntries) {

        super(context, 0, classEntries);
        classes = classEntries;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.class_entry, null);
        ClassEntry c = (ClassEntry) getItem(position);
        TextView tvClassName = v.findViewById(R.id.tvClassName);
        TextView tvClassLocation = v.findViewById(R.id.tvClassLocation);
        if (c != null) {
            tvClassName.setText(c.getName());
            tvClassLocation.setText(c.getLocation());
        }
        return v;

    }

}