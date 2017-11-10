package com.csc_331_jagwares.bluetoothattendee.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csc_331_jagwares.bluetoothattendee.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    public SettingsFragment() {
        // Required empty public constructor.
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Set the main activity title.
        getActivity().setTitle(R.string.nav_settings);
        // Inflate the layout for this fragment.
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        return view;
    }

}
