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

public class MakePayment extends AppCompatActivity {

    String username, facilityName, date, time, deposit, creditCardNumber, cvv, expirationDate;
    TextView makePaymentTV, usernameTV, facilityNameTV, reservationDateTV, reservationTimeTV, depositTV, creditCardTV, cvvTV, expirationDateTV;
    EditText creditCardET, cvvET, expirationDateET;
    Button makePaymentBtn;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        username = getIntent().getStringExtra("username");
        facilityName = getIntent().getStringExtra("facilityName");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        deposit = getIntent().getStringExtra("deposit");

        makePaymentTV = findViewById(R.id.makePaymentTV);
        usernameTV = findViewById(R.id.usernameTV);
        facilityNameTV = findViewById(R.id.facilityNameTV);
        reservationDateTV = findViewById(R.id.reservationDateTV);
        reservationTimeTV = findViewById(R.id.reservationTimeTV);
        depositTV = findViewById(R.id.depositTV);
        creditCardTV = findViewById(R.id.creditCardTV);
        cvvTV = findViewById(R.id.cvvTV);
        expirationDateTV = findViewById(R.id.expirationDateTV);

        usernameTV.append(username);
        facilityNameTV.append(facilityName);
        reservationDateTV.append(date);
        reservationTimeTV.append(time);
        depositTV.append(deposit);

        creditCardET = findViewById(R.id.creditCardET);
        cvvET = findViewById(R.id.cvvET);
        expirationDateET = findViewById(R.id.expirationDateET);

        makePaymentBtn = findViewById(R.id.makePaymentBtn);

        myDB = new DatabaseHelper(this);

        ButtonFunction();
    }

    public void ButtonFunction()
    {
        makePaymentBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //write to database
                creditCardNumber = creditCardET.getText().toString();
                cvv = cvvET.getText().toString();
                expirationDate = expirationDateET.getText().toString();

                Boolean request;
                request = myDB.requestReservation(username, facilityName, date, time, deposit);

                if(request) {
                    Toast.makeText(MakePayment.this, "Request successfully made!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MakePayment.this, ViewUserReservationsActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MakePayment.this, "Request failed! Try again later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
