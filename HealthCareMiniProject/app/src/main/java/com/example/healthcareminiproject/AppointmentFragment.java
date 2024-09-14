package com.example.healthcareminiproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class AppointmentFragment extends Fragment {

    public AppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);

        // Find all buttons
        Button button1 = view.findViewById(R.id.bt1);
        Button button2 = view.findViewById(R.id.bt2);
        Button button3 = view.findViewById(R.id.bt3);
        Button button4 = view.findViewById(R.id.bt4);

        // Add onClickListeners to buttons
        button1.setOnClickListener(v -> navigateToActivity(appoint.class));
        button2.setOnClickListener(v -> navigateToActivity(appoint2.class));
        button3.setOnClickListener(v -> navigateToActivity(appoint3.class));
        button4.setOnClickListener(v -> navigateToActivity(appoint4.class));

        return view;
    }

    // Method to navigate to the specified activity
    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(requireActivity(), activityClass);
        startActivity(intent);
    }
}