package com.example.MacRes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import model.DatabaseHelper;

public class FMAddFacility extends AppCompatActivity {

    TextView addFacilityTV, facilityNameTV, facilityTypeTV, timeIntervalTV, durationTV, venueTV, depositTV;
    EditText facilityNameET;
    Spinner facilityTypeSpinner, timeIntervalSpinner, durationSpinner, venueSpinner, depositSpinner;
    Button addFacilityBtn;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm_add_facility);

        myDb = new DatabaseHelper(this);

        addFacilityTV = findViewById(R.id.addFacilityTV);
        facilityNameTV = findViewById(R.id.facilityNameTV);
        facilityTypeTV = findViewById(R.id.facilityTypeTV);
        timeIntervalTV = findViewById(R.id.timeIntervalTV);
        durationTV = findViewById(R.id.durationTV);
        venueTV = findViewById(R.id.venueTV);
        depositTV = findViewById(R.id.depositTV);

        facilityNameET = findViewById(R.id.facilityNameET);

        facilityTypeSpinner = findViewById(R.id.facilityTypeSpinner);
        timeIntervalSpinner = findViewById(R.id.timeIntervalSpinner);
        durationSpinner = findViewById(R.id.durationSpinner);
        venueSpinner = findViewById(R.id.venueSpinner);
        depositSpinner = findViewById(R.id.depositSpinner);

        addFacilityBtn = findViewById(R.id.addFacilityBtn); //button to send data in the activity to the db

        /* Calling the AddFacility() method which will insert the data into the database. */
        AddFacility();
    }

    public void AddFacility()
    {
        addFacilityBtn.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.addFacility(facilityNameET.getText().toString(),
                                facilityTypeSpinner.getSelectedItem().toString(),
                                timeIntervalSpinner.getSelectedItem().toString(),
                                durationSpinner.getSelectedItem().toString(),
                                venueSpinner.getSelectedItem().toString(),
                                depositSpinner.getSelectedItem().toString());

                        if(isInserted == true){
                            Toast.makeText(FMAddFacility.this,"Successfully added new facility!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(FMAddFacility.this, FMViewFacilitiesActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(FMAddFacility.this,"Cannot add new facility! Try again later!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}