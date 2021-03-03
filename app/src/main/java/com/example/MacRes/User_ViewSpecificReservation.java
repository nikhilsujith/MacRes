package com.example.MacRes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import model.DatabaseHelper;

public class User_ViewSpecificReservation extends AppCompatActivity {

    TextView reservationDetailsTV, reservationIdTV, facilityNameTV, dateTV, timeTV, depositTV;
    Button cancelBtn, modifyBtn;

    DatabaseHelper myDB;

    String reservationID, username, facilityName, date, time, deposit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_specific_reservation);

        reservationDetailsTV = findViewById(R.id.reservationDetailsTV);
        reservationIdTV = findViewById(R.id.reservationIdTV);
        facilityNameTV = findViewById(R.id.facilityNameTV);
        dateTV = findViewById(R.id.dateTV);
        timeTV = findViewById(R.id.timeTV);
        depositTV = findViewById(R.id.depositTV);

        cancelBtn = findViewById(R.id.cancelBtn);
        modifyBtn = findViewById(R.id.modifyBtn);

        myDB = new DatabaseHelper(this);

        reservationID = getIntent().getStringExtra("reservationID");
        get_selected_reservation(reservationID);

        ButtonFunction();
    }

    public void get_selected_reservation(String reservationID)
    {
        String query = "SELECT * FROM reservations WHERE reservation_id LIKE '" + reservationID + "'";
        Cursor reservation = myDB.fetchSpecificReservation(query);

        while (reservation.moveToNext()) {
            reservationIdTV.append(reservation.getString(0));
            username = reservation.getString(1);
            facilityName = reservation.getString(2);
            facilityNameTV.append(facilityName);
            date = reservation.getString(3);
            dateTV.append(date);
            time = reservation.getString(4);
            timeTV.append(time);
            deposit = reservation.getString(5);
            depositTV.append(deposit);
        }
    }

    public void ButtonFunction()
    {
        cancelBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(User_ViewSpecificReservation.this);
                        builder.setTitle("Info");
                        builder.setMessage("Do you want to cancel this reservation ??");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                boolean result = myDB.cancelReservation(reservationID);
                                if(result) {
                                    Toast.makeText(User_ViewSpecificReservation.this, "Reservation successfully cancelled!", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(User_ViewSpecificReservation.this, ViewUserReservationsActivity.class);
                                    intent.putExtra("username", username);
                                    startActivity(intent);
                                }
                                else
                                    Toast.makeText(User_ViewSpecificReservation.this, "Cancellation failed! Try again later!", Toast.LENGTH_SHORT).show();

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

        modifyBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(User_ViewSpecificReservation.this, ModifyReservation.class);
                        intent.putExtra("reservationID", reservationID);
                        intent.putExtra("facilityName", facilityName);
                        intent.putExtra("date", date);
                        intent.putExtra("time", time);
                        intent.putExtra("deposit", deposit);
                        startActivity(intent);
                    }
                });
    }
}

