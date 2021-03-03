package com.example.MacRes;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import model.DatabaseHelper;

public class User_ViewSpecificViolation extends AppCompatActivity {

    TextView violationDetailsTV, violationIdTV, facilityNameTV, dateTV, timeTV, descriptionTV, depositTV;
    DatabaseHelper myDB;

    String violationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_specific_violation);

        myDB = new DatabaseHelper(this);

        violationDetailsTV = findViewById(R.id.violationDetailsTV);
        violationIdTV = findViewById(R.id.violationIdTV);
        facilityNameTV = findViewById((R.id.facilityNameTV));
        dateTV = findViewById(R.id.violationDateTV);
        timeTV = findViewById(R.id.violationTimeTV);
        descriptionTV = findViewById(R.id.descriptionTV);
        depositTV = findViewById(R.id.depositTV);

        violationID = getIntent().getStringExtra("violationID");

        get_selected_violation(violationID);
    }

    public void get_selected_violation(String violationID)
    {
        String query = "SELECT * FROM user_violations WHERE violation_id LIKE '" + violationID + "'";
        Cursor facility = myDB.fetchSpecificViolation(query);

        while (facility.moveToNext()) {
            violationIdTV.append(facility.getString(0));  //index 0 is the facilityName of that facility in the database
            facilityNameTV.append(facility.getString(2));  //index 1 is the type of that facility in the database
            dateTV.append(facility.getString(3));  //index 2 is the timeInterval of that facility in the database
            timeTV.append(facility.getString(4));  //index 3 is the duration of that facility in the database
            descriptionTV.append(facility.getString(5));  //index 4 is the venue of that facility in the database
            depositTV.append(facility.getString(6));  //index 5 is the deposit of that facility in the database
        }
    }
}

