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

import Adapters.ViewViolations_ListAdapter;
import data.Violations;
import model.DatabaseHelper;

public class UserViewViolationsActivity extends AppCompatActivity implements ViewViolations_ListAdapter.customButtonListener{

    ListView violationsList;
    TextView viewViolationsTV;
    DatabaseHelper db;
    ViewViolations_ListAdapter adapter;
    public static List<Violations> VIOLATION_LIST = new ArrayList<Violations>();
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_violations);

        viewViolationsTV = findViewById(R.id.viewViolationsTV);
        violationsList = findViewById(R.id.violationsList);

        ViewGroup headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.header_for_violations, violationsList, false);
        violationsList.addHeaderView(headerView);

        db = new DatabaseHelper(this);

        username = getIntent().getStringExtra("username");

        populate();
    }

    @Override
    public void onButtonClickListner(String violationID) {
        Intent intent = new Intent(UserViewViolationsActivity.this, User_ViewSpecificViolation.class);
        intent.putExtra("violationID", violationID);
        intent.putExtra("username", username);
        startActivity(intent);

    }

    public void populate()
    {
        Cursor cursor = db.view_violations(username);

        //create user data
        while(cursor.moveToNext()) {
            Violations violation = new Violations(cursor.getString(0));
            VIOLATION_LIST.add(violation);
        }

        adapter = new ViewViolations_ListAdapter(this, VIOLATION_LIST);
        adapter.setCustomButtonListner(UserViewViolationsActivity.this);
        violationsList.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter!=null)
        {
            VIOLATION_LIST.clear();
            populate();
        }
    }
}
