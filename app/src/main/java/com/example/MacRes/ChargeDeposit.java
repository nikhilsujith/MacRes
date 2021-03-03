package com.example.MacRes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import model.DatabaseHelper;

public class ChargeDeposit extends AppCompatActivity {

    TextView chargeTV, usernameTV, facilityNameTV, dateTV, timeTV, descriptionTV, depositTV;
    EditText depositET;
    Button submitBtn;
    DatabaseHelper myDB;
    String username, facilityName, date, time, description, deposit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_deposit);

        chargeTV = findViewById(R.id.chargeTV);
        usernameTV = findViewById(R.id.usernameTV);
        facilityNameTV = findViewById(R.id.facilityNameTV);
        dateTV = findViewById(R.id.dateTV);
        timeTV = findViewById(R.id.timeTV);
        descriptionTV = findViewById(R.id.descriptionTV);
        depositTV = findViewById(R.id.depositTV);

        depositET = findViewById(R.id.depositET);

        submitBtn = findViewById(R.id.cancelBtn);

        myDB = new DatabaseHelper(this);

        username = getIntent().getStringExtra("username");
        facilityName = getIntent().getStringExtra("facilityName");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        description = getIntent().getStringExtra("description");

        usernameTV.append(username);
        facilityNameTV.append(facilityName);
        dateTV.append(date);
        timeTV.append(time);
        descriptionTV.append(description);

        ButtonFunction();
    }

    public void ButtonFunction()
    {
        submitBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //write to database
                Boolean result = myDB.makeReport(username, facilityName, date, time, description, "$"+depositTV.getText().toString());

                if(result)
                {
                    Toast.makeText(ChargeDeposit.this, "Violation report made successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChargeDeposit.this, FM_ViewSpecificUser.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(ChargeDeposit.this, "Failed! Try again later!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
