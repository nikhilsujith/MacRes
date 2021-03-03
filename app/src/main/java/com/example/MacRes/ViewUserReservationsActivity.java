package com.example.MacRes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adapters.ViewReservations_ListAdapter;
import data.Reservations;
import model.DatabaseHelper;

public class ViewUserReservationsActivity extends AppCompatActivity implements ViewReservations_ListAdapter.customButtonListener {
    ListView reservationsList;
    TextView viewReservationsTV;
    DatabaseHelper db;
    String username;
    ViewReservations_ListAdapter adapter;
    public static List<Reservations> RESERVATIONS_LIST = new ArrayList<Reservations>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_reservations);

        viewReservationsTV = findViewById(R.id.viewReservationsTV);
        reservationsList = findViewById(R.id.reservationsList);

        ViewGroup headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.user_header_view_reservations, reservationsList, false);
        reservationsList.addHeaderView(headerView);

        db = new DatabaseHelper(this);
        username = getIntent().getStringExtra("username");

        populate();
    }

    @Override
    public void onButtonClickListner(String reservationID) {
        Intent intent = new Intent(ViewUserReservationsActivity.this, User_ViewSpecificReservation.class);
        intent.putExtra("reservationID", reservationID);
        startActivity(intent);

    }

    public void populate()
    {
        Cursor cursor = db.user_view_reservations(username);

        //create user data
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                Reservations reservation = new Reservations(cursor.getString(0), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                RESERVATIONS_LIST.add(reservation);
            }

            adapter = new ViewReservations_ListAdapter(this, RESERVATIONS_LIST);
            adapter.setCustomButtonListner(ViewUserReservationsActivity.this);
            reservationsList.setAdapter(adapter);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter!=null)
        {
            RESERVATIONS_LIST.clear();
            populate();
        }
    }
}
