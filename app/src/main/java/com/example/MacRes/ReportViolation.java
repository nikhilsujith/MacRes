package com.example.MacRes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import model.DatabaseHelper;

public class ReportViolation extends AppCompatActivity {

    TextView reportTV, usernameLabelTV, usernameTV, facilityNameTV, dateTV, timeTV, descriptionTV, depositTV;
    EditText facilityNameET, dateET, timeET, descriptionET;
    Button submitBtn;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_violation);

        reportTV = findViewById(R.id.reportTV);
        usernameLabelTV = findViewById(R.id.usernameLabelTV);
        usernameTV = findViewById(R.id.usernameTV);
        facilityNameTV = findViewById(R.id.facilityNameTV);
        dateTV = findViewById(R.id.dateTV);
        timeTV = findViewById(R.id.timeTV);
        descriptionTV = findViewById(R.id.descriptionTV);
        depositTV = findViewById(R.id.depositTV);

        facilityNameET = findViewById(R.id.facilityNameET);
        dateET = findViewById(R.id.dateET);
        timeET = findViewById(R.id.timeET);
        descriptionET = findViewById(R.id.descriptionET);

        submitBtn = findViewById(R.id.cancelBtn);

        myDB = new DatabaseHelper(this);
        usernameTV.setText(getIntent().getStringExtra("username"));

        ButtonFunction();
    }

    public void ButtonFunction()
    {
        submitBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //write to database
                String username = usernameTV.getText().toString();
                String facilityName = facilityNameET.getText().toString();
                String date = dateET.getText().toString();
                String time = timeET.getText().toString();
                String description = descriptionET.getText().toString();

                Intent intent = new Intent(ReportViolation.this, ChargeDeposit.class);
                intent.putExtra("username", username);
                intent.putExtra("facilityName", facilityName);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("description", description);
                startActivity(intent);
            }
        });
    }
}
