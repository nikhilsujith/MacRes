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

public class ModifyReservation extends AppCompatActivity {

    String reservationID, facilityName, date, time, deposit;

    TextView modifyTV, reservationIdTV, facilityNameTV, dateTV, timeTV, depositTV;
    EditText dateET, timeET;
    Button modifyBtn;

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_reservation);

        myDB = new DatabaseHelper(this);

        modifyTV = findViewById(R.id.modifyTV);
        reservationIdTV = findViewById(R.id.reservationIdTV);
        facilityNameTV = findViewById(R.id.facilityNameTV);
        dateTV = findViewById(R.id.dateTV);
        timeTV = findViewById(R.id.timeTV);
        depositTV = findViewById(R.id.depositTV);

        dateET = findViewById(R.id.dateET);
        timeET = findViewById(R.id.timeET);

        modifyBtn = findViewById(R.id.modifyBtn);

        reservationID = getIntent().getStringExtra("reservationID");
        facilityName = getIntent().getStringExtra("facilityName");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        deposit = getIntent().getStringExtra("deposit");

        reservationIdTV.append(reservationID);
        facilityNameTV.append(facilityName);
        dateET.setText(date);
        timeET.setText(time);
        depositTV.append(deposit);

        ButtonFunction();
    }

    public void ButtonFunction()
    {
        modifyBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String newDate = dateET.getText().toString();
                String newTime = timeET.getText().toString();

                boolean result = myDB.modifyReservation(reservationID, newDate, newTime);
                if(result) {
                    Toast.makeText(ModifyReservation.this, "Modification successful!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ModifyReservation.this, User_ViewSpecificReservation.class);
                    intent.putExtra("reservationID", reservationID);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(ModifyReservation.this, "Modification failed! Try again later!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
