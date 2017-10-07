package com.csc_331_jagwares.bluetoothattendee;


import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends Fragment implements View.OnClickListener {

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

        TextView tvClassName = view.findViewById(R.id.tvClassName);
        TextView tvClassLocation = view.findViewById(R.id.tvClassLocation);

        if (classEntry != null) {
            tvClassName.setText(classEntry.getName());
            tvClassLocation.setText(classEntry.getLocation());
        }

        // Buttons.
        Button rosterBtn = (Button) view.findViewById(R.id.rosterBtn);
        Button attendanceBtn = (Button) view.findViewById(R.id.attendanceBtn);
        Button registerBtn = (Button) view.findViewById(R.id.registerBtn);

        rosterBtn.setOnClickListener(this);
        attendanceBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        Fragment fragment = null;

        switch (v.getId()) {
            case R.id.rosterBtn:
                fragment = new ClassRosterFragment();
                break;
            case R.id.attendanceBtn:
                fragment = new TakeAttendanceFragment();
                break;
            case R.id.registerBtn:
                fragment = new RegistrationFragment();
                break;
        }

        Bundle bundle = new Bundle();
        bundle.putParcelable("Class", classEntry);

        if (fragment != null) {
            fragment.setArguments(bundle);
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainLayout, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

}
