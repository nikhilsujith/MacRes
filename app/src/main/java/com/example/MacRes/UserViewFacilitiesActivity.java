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

import Adapters.ViewFacilities_ListAdapter;
import data.Facilities;
import model.DatabaseHelper;

public class UserViewFacilitiesActivity extends AppCompatActivity implements ViewFacilities_ListAdapter.customButtonListener {

    ListView facilitiesList;
    TextView viewFacilitiesTV;
    DatabaseHelper db;
    ViewFacilities_ListAdapter adapter;
    public static List<Facilities> FACILITY_LIST = new ArrayList<Facilities>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_facilities);

        viewFacilitiesTV = findViewById(R.id.viewFacilitiesTV);
        facilitiesList = findViewById(R.id.facilitiesList);

        ViewGroup headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.header_view_facilities, facilitiesList, false);
        facilitiesList.addHeaderView(headerView);

        db = new DatabaseHelper(this);

        populate();
    }

    @Override
    public void onButtonClickListner(String facilityName) {
        String username = getIntent().getStringExtra("username");
        String revoked = getIntent().getStringExtra("revoked");
        Intent intent = new Intent(UserViewFacilitiesActivity.this, User_ViewSpecificFacility.class);
        intent.putExtra("facilityName", facilityName);
        intent.putExtra("username", username);
        intent.putExtra("revoked", revoked);
        startActivity(intent);

    }

    public void populate()
    {
        String query = "SELECT * FROM facilities WHERE available='YES' ORDER BY facility_name ASC";
        Cursor cursor = db.view_facilities(query);

        //create user data
        while(cursor.moveToNext()) {
            Facilities facility = new Facilities(cursor.getString(0));
            FACILITY_LIST.add(facility);
        }

        adapter = new ViewFacilities_ListAdapter(this, FACILITY_LIST);
        adapter.setCustomButtonListner(UserViewFacilitiesActivity.this);
        facilitiesList.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter!=null)
        {
            FACILITY_LIST.clear();
            populate();
        }
    }
}
