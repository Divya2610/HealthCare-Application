package com.example.healthcareminiproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ReminderFragment extends Fragment {

    private mySQLiteDBHandler dbHandler;
    private EditText editText;
    private CalendarView calendarView;
    private String selectedDate;
    private SQLiteDatabase sqLiteDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reminder, container, false);

        editText = view.findViewById(R.id.editText);
        calendarView = view.findViewById(R.id.calendarView);
        Button buttonSave = view.findViewById(R.id.buttonSave);

        // Set OnClickListener for the Save button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call InsertDatabase() method here
                InsertDatabase(v);
                // Show a Toast message after data insertion
                showToast("Reminder set successfully");
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth);
                ReadDatabase(view);
            }
        });

        try {
            dbHandler = new mySQLiteDBHandler(getContext(), "CalendarDatabase", null, 1);
            sqLiteDatabase = dbHandler.getWritableDatabase();
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS EventCalendar(Date TEXT, Event TEXT)");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    public void InsertDatabase(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date", selectedDate);
        contentValues.put("Event", editText.getText().toString());
        sqLiteDatabase.insert("EventCalendar", null, contentValues);
    }

    public void ReadDatabase(View view) {
        String query = "SELECT Event FROM EventCalendar WHERE Date = '" + selectedDate + "'";
        try {
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                editText.setText(cursor.getString(0));
            } else {
                editText.setText("");
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            editText.setText("");
        }
    }

    private void showToast(String message) {
        if (getActivity() != null) {
            android.widget.Toast.makeText(getActivity(), message, android.widget.Toast.LENGTH_SHORT).show();
        }
    }
}