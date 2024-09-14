package com.example.healthcareminiproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class doc_sign_in extends AppCompatActivity {

    // Define allowed doctor usernames and passwords
    private static final String[] allowedUsernames = {"doctor1", "doctor2", "doctor3", "doctor4"};
    private static final String[] allowedPasswords = {"password1", "password2", "password3", "password4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_sign_in); // replace with your layout file

        // Get references to views
        EditText editTextEmail = findViewById(R.id.dr_email);
        EditText editTextPassword = findViewById(R.id.dr_password);
        Button buttonSignIn = findViewById(R.id.dr_button);

        // Set click listener for sign-in button
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get entered credentials
                String enteredUsername = editTextEmail.getText().toString();
                String enteredPassword = editTextPassword.getText().toString();

                // Check if entered credentials match allowed users
                boolean isValidCredentials = false;
                for (int i = 0; i < allowedUsernames.length; i++) {
                    if (enteredUsername.equals(allowedUsernames[i]) && enteredPassword.equals(allowedPasswords[i])) {
                        isValidCredentials = true;
                        break;
                    }
                }

                // Redirect if credentials are valid, otherwise show error message
                if (isValidCredentials) {
                    // Redirect to doctor's home page and pass the username
                    redirectToDoctorHomePage(enteredUsername);
                } else {
                    // Show error message
                    Toast.makeText(doc_sign_in.this, "Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to redirect to doctor's home page
    private void redirectToDoctorHomePage(String username) {
        Intent intent;
        switch (username) {
            case "doctor1":
                intent = new Intent(doc_sign_in.this, dr_homepage.class);
                break;
            case "doctor2":
                intent = new Intent(doc_sign_in.this, dr2_homepage.class);
                break;
            case "doctor3":
                intent = new Intent(doc_sign_in.this, dr3_homepage.class);
                break;
            case "doctor4":
                intent = new Intent(doc_sign_in.this, dr4_homepage.class);
                break;
            default:
                // Default redirection to a common doctor homepage if username not recognized
                intent = new Intent(doc_sign_in.this, dr_homepage.class);
                break;
        }
        startActivity(intent);
        // Finish the current activity to prevent the user from going back to the sign-in page
        finish();
    }
}
