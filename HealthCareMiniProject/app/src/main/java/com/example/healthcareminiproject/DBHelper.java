package com.example.healthcareminiproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;

    private static final String DATABASE_NAME = "Login.db";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_APPOINTMENT_REQUESTS = "appointment_requests";
    private static final String TABLE_FEEDBACK = "feedback";

    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    "username TEXT PRIMARY KEY, " +
                    "password TEXT)";

    private static final String CREATE_APPOINTMENT_REQUESTS_TABLE =
            "CREATE TABLE " + TABLE_APPOINTMENT_REQUESTS + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "patient_name TEXT, " +
                    "patient_email TEXT, " +
                    "patient_phone TEXT, " +
                    "patient_illness TEXT, " +
                    "time_slot TEXT)";

    private static final String CREATE_FEEDBACK_TABLE =
            "CREATE TABLE " + TABLE_FEEDBACK + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "rating REAL, " +
                    "comments TEXT)";



    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_APPOINTMENT_REQUESTS_TABLE);
        db.execSQL(CREATE_FEEDBACK_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No upgrade logic needed for this example
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username =?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        return result != -1;
    }

    public boolean insertAppointmentRequest(String patientName, String patientEmail, String patientPhone, String patientIllness, String timeSlot) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("patient_name", patientName);
        values.put("patient_email", patientEmail);
        values.put("patient_phone", patientPhone);
        values.put("patient_illness", patientIllness);
        values.put("time_slot", timeSlot);
        long result = db.insert("appointment_requests", null, values);
        return result != -1;
    }

    public Cursor getAllAppointmentRequests() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM appointment_requests", null);
        return cursor;
    }

    public boolean insertFeedback(float rating, String comments) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("rating", rating);
        values.put("comments", comments);
        long result = db.insert("feedback", null, values);
        return result != -1;
    }

    public boolean insertEventReminder(String date, String event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("event", event);
        long result = db.insert("event_reminders", null, values);
        return result != -1;
    }

    public Cursor getEventReminderByDate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT event FROM event_reminders WHERE date = ?", new String[]{date});
        return cursor;
    }
}

