package com.example.MacRes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import model.DatabaseHelper;

public class FM_ViewSpecificFacility extends AppCompatActivity {

    TextView facilityDetailsTV, facilityNameTV, facilityTypeTV, timeIntervalTV, durationTV, venueTV, depositTV, availableTV;
    Button availableBtn;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm_view_specific_facility);

        myDB = new DatabaseHelper(this);

        facilityDetailsTV = findViewById(R.id.reservationDetailsTV);
        facilityNameTV = findViewById(R.id.reservationIdTV);
        facilityTypeTV = findViewById(R.id.facilityNameTV);
        timeIntervalTV = findViewById((R.id.dateTV));
        durationTV = findViewById(R.id.timeTV);
        venueTV = findViewById(R.id.venueTV);
        depositTV = findViewById(R.id.depositTV);
        availableTV = findViewById(R.id.availableTV);

        availableBtn = findViewById(R.id.cancelBtn);

        String facilityName = getIntent().getStringExtra("facilityName");
        get_selected_facility(facilityName);

        if(availableTV.getText().toString().equals("Is Available: NO"))
            availableBtn.setText("MAKE AVAILABLE");
        else
            availableBtn.setText("MAKE UNAVAILABLE");

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
        }
    }

    public void ButtonFunction()
    {
        availableBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String available = availableBtn.getText().toString();
                        final AlertDialog.Builder builder = new AlertDialog.Builder(FM_ViewSpecificFacility.this);
                        builder.setTitle("Alert");

                        if(available.equals("Make Unavailable"))
                            builder.setMessage("Are you sure you want to make this facility unavailable: " + getIntent().getStringExtra("facilityName") + "?");
                        else
                            builder.setMessage("Are you sure you want to make this facility available: " + getIntent().getStringExtra("facilityName") + "?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();

                                if(makeAvailable(available))
                                {
                                    if(available.equals("Make Unavailable")) {
                                        Toast.makeText(FM_ViewSpecificFacility.this, "Facility is now unavailable!", Toast.LENGTH_SHORT).show();
                                        availableBtn.setText("Make Available");
                                        availableTV.setText("Is Available: NO");
                                    }
                                    else
                                    {
                                        Toast.makeText(FM_ViewSpecificFacility.this, "Facility is now available!", Toast.LENGTH_SHORT).show();
                                        availableBtn.setText("Make Unavailable");
                                        availableTV.setText("Is Available: YES");
                                    }
                                }
                                else
                                {
                                    Toast.makeText(FM_ViewSpecificFacility.this, "Failed! Try again later!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
    }

    public boolean makeAvailable(String available)
    {
        String availableValue = "";
        if(available.equals("Make Unavailable"))
        {
            availableValue = "NO";
        }
        else {
            availableValue = "YES";
        }
        return myDB.fm_changeFacilityAvailability(getIntent().getStringExtra("facilityName"), availableValue);
    }
}
