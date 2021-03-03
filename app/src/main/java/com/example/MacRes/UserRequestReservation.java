package com.example.MacRes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import model.DatabaseHelper;

public class UserRequestReservation extends AppCompatActivity {

    String  username, facilityName, deposit;

    TextView usernameTV, facilityNameTV, timeTV, dateTV,depositTV;
    Button requestBtn;
    EditText timeET, dateET;

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request_reservation);

        myDB = new DatabaseHelper(this);

        username = getIntent().getStringExtra("username");
        facilityName = getIntent().getStringExtra("facilityName");
        deposit = getIntent().getStringExtra("deposit");

        usernameTV = findViewById(R.id.usernameLabelTV);
        facilityNameTV = findViewById(R.id.facilityNameTV);
        timeTV = findViewById(R.id.timeTV);
        dateTV = findViewById(R.id.dateTV);
        depositTV = findViewById(R.id.depositTV);

        timeET = findViewById(R.id.timeET);
        dateET = findViewById(R.id.dateET);

        requestBtn = findViewById(R.id.requestBtn);

        usernameTV.append(username);
        facilityNameTV.append(facilityName);
        depositTV.append(deposit);

        requestBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String date = dateET.getText().toString();
                        String time = timeET.getText().toString();

                        Intent intent = new Intent(UserRequestReservation.this, MakePayment.class);
                        intent.putExtra("username", username);
                        intent.putExtra("facilityName", facilityName);
                        intent.putExtra("date", date);
                        intent.putExtra("time", time);
                        intent.putExtra("deposit", deposit);
                        startActivity(intent);
                    }
                });
    }
}
