package com.example.healthcareminiproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FeedbackFragment extends Fragment {

    private TextView feedback;
    private RatingBar rating;
    private Button btn;
    private DBHelper dbHelper;
    private EditText feedbackInput;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        feedback = view.findViewById(R.id.feedbacks);
        rating = view.findViewById(R.id.ratingBar);
        btn = view.findViewById(R.id.button);
        feedbackInput = view.findViewById(R.id.feed); // Initialize with the EditText id from the XML

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float ratingValue, boolean b) {
                if (ratingValue == 0) {
                    feedback.setText("Very Disatisfied");
                } else if (ratingValue == 1) {
                    feedback.setText("Disatisfied");
                } else if (ratingValue == 2 || ratingValue == 3) {
                    feedback.setText("OK");
                } else if (ratingValue == 4) {
                    feedback.setText("Satisfied");
                } else {
                    feedback.setText("Very satisfied");
                }

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String feedbackText = feedbackInput.getText().toString().trim();
                        boolean isInserted = dbHelper.insertFeedback(ratingValue, feedbackText);
                        if (isInserted) {
                            Toast.makeText(getActivity(), "Feedback is successfully stored", Toast.LENGTH_SHORT).show();
                            feedbackInput.setText(""); // Clear the feedback input field
                        } else {
                            Toast.makeText(getActivity(), "Failed to store feedback", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return view;
    }
}