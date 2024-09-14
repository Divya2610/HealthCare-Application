package com.example.healthcareminiproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        Intent intent = getIntent();
        String selectedTimeSlot = null;

        if (intent != null) {
            selectedTimeSlot = intent.getStringExtra("timeSlot");
            TextView textView = findViewById(R.id.textViewSelectedTimeSlot);
            textView.setText("Selected Time Slot: " + selectedTimeSlot);
        }

        Button buttonBookAppointment = findViewById(R.id.buttonBookAppointment);
        final String finalSelectedTimeSlot = selectedTimeSlot;


        buttonBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientName = ((EditText) findViewById(R.id.pname)).getText().toString();
                String patientEmail = ((EditText) findViewById(R.id.pemail)).getText().toString();
                String patientPhoneNumber = ((EditText) findViewById(R.id.pphn)).getText().toString();
                String patientIllness = ((EditText) findViewById(R.id.pdesc)).getText().toString();
                String selectedTimeSlot = finalSelectedTimeSlot;

                DBHelper dbHelper = new DBHelper(BookAppointment.this);
                boolean isInserted = dbHelper.insertAppointmentRequest(
                        patientName,
                        patientEmail,
                        patientPhoneNumber,
                        patientIllness,
                        selectedTimeSlot
                );

                if (isInserted) {
                    Toast.makeText(BookAppointment.this, "Appointment Request Sent", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BookAppointment.this, "Failed to send appointment request", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(BookAppointment.this, MedicalrecordsFragment.class);
                startActivity(intent);

            }
        });
    }
}