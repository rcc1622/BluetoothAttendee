package com.csc_331_jagwares.bluetoothattendee;


import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends Fragment {

    // This is the class entry that defines the fragment.
    private ClassEntry classEntry;

    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class, container, false);

        // Receive object data from ClassesFragment.
        classEntry = new ClassEntry(Parcel.obtain());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            classEntry = bundle.getParcelable("Class Entry");
        }

        // Set the title of the MainActivity to the class entry name.
        getActivity().setTitle(classEntry.getName());

        return view;
    }

}
