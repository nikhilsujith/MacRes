package com.example.MacRes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import model.DatabaseHelper;

public class User_ViewSpecificFacility extends AppCompatActivity {

    TextView facilityDetailsTV, facilityNameTV, facilityTypeTV, timeIntervalTV, durationTV, venueTV, depositTV, availableTV, revokedStatementTV;
    Button requestBtn;
    DatabaseHelper myDB;

    String username, facilityName, deposit, revoked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_specific_facility);

        myDB = new DatabaseHelper(this);

        facilityDetailsTV = findViewById(R.id.facilityDetailsTV);
        facilityNameTV = findViewById(R.id.facilityNameTV);
        facilityTypeTV = findViewById(R.id.facilityTypeTV);
        timeIntervalTV = findViewById((R.id.timeIntervalTV));
        durationTV = findViewById(R.id.durationTV);
        venueTV = findViewById(R.id.venueTV);
        depositTV = findViewById(R.id.depositTV);
        availableTV = findViewById(R.id.availableTV);
        revokedStatementTV = findViewById(R.id.revokedStatementTV);

        requestBtn = findViewById(R.id.requestBtn);

        username = getIntent().getStringExtra("username");
        facilityName = getIntent().getStringExtra("facilityName");
        revoked = getIntent().getStringExtra("revoked");

        get_selected_facility(facilityName);

        ButtonFunction();
    }

    public void get_selected_facility(String facilityName)
    {
        String query = "SELECT * FROM facilities WHERE facility_name LIKE '" + facilityName + "'";
        Cursor facility = myDB.fetchSpecificFacility(query);

        while (facility.moveToNext()) {
            facilityNameTV.append(facility.getString(0));  //index 0 is the facilityName of that facility in the database
            facilityTypeTV.append(facility.getString(1));  //index 1 is the type of that facility in the database
            timeIntervalTV.append(facility.getString(2));  //index 2 is the timeInterval of that facility in the database
            durationTV.append(facility.getString(3));  //index 3 is the duration of that facility in the database
            venueTV.append(facility.getString(4));  //index 4 is the venue of that facility in the database
            depositTV.append(facility.getString(5));  //index 5 is the deposit of that facility in the database
            availableTV.append(facility.getString(6));  //index 6 is the availability of that facility in the database

            deposit = facility.getString(5);

            if(revoked.equalsIgnoreCase("YES"))
            {
                requestBtn.setEnabled(false);
                requestBtn.setBackgroundResource(R.drawable.disabled_button_shape);
                requestBtn.setTextColor(getResources().getColor(R.color.gray));
                revokedStatementTV.setText("You cannot request a reservation, you are a revoked user. Sorry!");
            }
            else
                requestBtn.setEnabled(true);
        }
    }

    public void ButtonFunction()
    {
        requestBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(User_ViewSpecificFacility.this, UserRequestReservation.class);
                        intent.putExtra("facilityName", facilityName);
                        intent.putExtra("username", username);
                        intent.putExtra("deposit", deposit);
                        startActivity(intent);
                    }
                });
    }
}

