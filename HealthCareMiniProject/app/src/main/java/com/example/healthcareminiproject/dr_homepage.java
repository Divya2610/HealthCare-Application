package com.example.healthcareminiproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class dr_homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dr_homepage); // Replace with your layout file name

        // Get reference to the button
        Button appointmentButton = findViewById(R.id.appointment_btn);
        Button logoutButton =findViewById(R.id.drlogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(dr_homepage.this, doc_sign_in.class);
                startActivity(intent);

            }
        });

        // Set click listener for the button
        appointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AppointmentsActivity
                Intent intent = new Intent(dr_homepage.this, AppointmentRequestsActivity.class);
                startActivity(intent);
            }
        });
    }
}
