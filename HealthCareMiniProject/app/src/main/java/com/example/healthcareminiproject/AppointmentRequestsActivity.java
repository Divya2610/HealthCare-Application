package com.example.healthcareminiproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AppointmentRequestsActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;

    private RecyclerView appointmentRequestsRecyclerView;
    private AppointmentRequestsAdapter adapter;
    private List<AppointmentRequest> appointmentRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointments);

        initializeViews();

        // Check if storage permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE_PERMISSION);
        } else {
            // Permission is already granted, fetch appointment requests
            fetchAppointmentRequests();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, fetch appointment requests
                fetchAppointmentRequests();
            } else {
                // Permission denied, show an error message or handle accordingly
                Log.e("AppointmentRequestsActivity", "Storage permission denied.");
            }
        }
    }

    private void initializeViews() {
        appointmentRequestsRecyclerView = findViewById(R.id.appointmentRequestsRecyclerView);
        appointmentRequests = new ArrayList<>();
        adapter = new AppointmentRequestsAdapter(appointmentRequests);
        appointmentRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointmentRequestsRecyclerView.setAdapter(adapter);
    }

    private void fetchAppointmentRequests() {
        DBHelper dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.getAllAppointmentRequests();

        int patientNameIndex = cursor.getColumnIndex("patient_name");
        int patientEmailIndex = cursor.getColumnIndex("patient_email");
        int patientPhoneIndex = cursor.getColumnIndex("patient_phone");
        int patientIllnessIndex = cursor.getColumnIndex("patient_illness");
        int timeSlotIndex = cursor.getColumnIndex("time_slot");

        appointmentRequests.clear(); // Clear existing requests

        if (cursor.moveToFirst()) {
            do {
                String patientName = cursor.getString(patientNameIndex);
                String patientEmail = cursor.getString(patientEmailIndex);
                String patientPhone = cursor.getString(patientPhoneIndex);
                String patientIllness = cursor.getString(patientIllnessIndex);
                String timeSlot = cursor.getString(timeSlotIndex);

                Log.d("AppointmentRequestsActivity", "Patient Name: " + patientName + ", Email: " + patientEmail + ", Phone: " + patientPhone + ", Illness: " + patientIllness + ", Time Slot: " + timeSlot);

                AppointmentRequest request = new AppointmentRequest(
                        patientName,
                        patientEmail,
                        patientPhone,
                        patientIllness,
                        timeSlot
                );

                appointmentRequests.add(request);
            } while (cursor.moveToNext());
        }

        cursor.close();
        adapter.notifyDataSetChanged();
    }

    // AppointmentRequest class to hold the appointment request data
    public static class AppointmentRequest {
        public String patientName;
        public String patientEmail;
        public String patientPhone;
        public String patientIllness;
        public String timeSlot;

        public AppointmentRequest(String patientName, String patientEmail, String patientPhone, String patientIllness, String timeSlot) {
            this.patientName = patientName;
            this.patientEmail = patientEmail;
            this.patientPhone = patientPhone;
            this.patientIllness = patientIllness;
            this.timeSlot = timeSlot;
        }
    }
}