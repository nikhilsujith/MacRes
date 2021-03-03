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

public class FM_ViewSpecificReservation extends AppCompatActivity {

    TextView reservationDetailsTV, reservationIdTV, usernameTV, facilityNameTV, dateTV, timeTV, depositTV;
    Button deleteBtn;

    DatabaseHelper myDB;

    String reservationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm_view_specific_reservation);

        reservationDetailsTV = findViewById(R.id.reservationDetailsTV);
        reservationIdTV = findViewById(R.id.reservationIdTV);
        usernameTV = findViewById(R.id.usernameTV);
        facilityNameTV = findViewById(R.id.facilityNameTV);
        dateTV = findViewById(R.id.dateTV);
        timeTV = findViewById(R.id.timeTV);
        depositTV = findViewById(R.id.depositTV);

        deleteBtn = findViewById(R.id.deleteBtn);

        myDB = new DatabaseHelper(this);

        reservationID = getIntent().getStringExtra("reservationID");
        reservationIdTV.append(reservationID);
        get_selected_reservation(reservationID);

        ButtonFunction();
    }

    public void get_selected_reservation(String reservationID)
    {
        String query = "SELECT * FROM reservations WHERE reservation_id LIKE '" + reservationID + "'";
        Cursor reservation = myDB.fetchSpecificReservation(query);

        while (reservation.moveToNext()) {
            reservationIdTV.append(reservation.getString(0));
            usernameTV.append(reservation.getString(1));
            facilityNameTV.append(reservation.getString(2));
            dateTV.append(reservation.getString(3));
            timeTV.append(reservation.getString(4));
            depositTV.append(reservation.getString(5));
        }
    }

    public void ButtonFunction()
    {
        deleteBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(FM_ViewSpecificReservation.this);
                        builder.setTitle("Info");
                        builder.setMessage("Do you want to delete this reservation ??");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                boolean result = myDB.cancelReservation(reservationID);
                                if(result) {
                                    Toast.makeText(FM_ViewSpecificReservation.this, "Reservation successfully deleted!", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(FM_ViewSpecificReservation.this, FMViewReservationsActivity.class);
                                    startActivity(intent);
                                }
                                else
                                    Toast.makeText(FM_ViewSpecificReservation.this, "Cancellation failed! Try again later!", Toast.LENGTH_SHORT).show();

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
}

