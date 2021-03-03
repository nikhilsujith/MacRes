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

import Adapters.ViewUsers_ListAdapter;
import data.Users;
import model.DatabaseHelper;

public class FMViewUsersActivity extends AppCompatActivity implements ViewUsers_ListAdapter.customButtonListener {

    ListView userList;
    TextView viewUsersTV;
    DatabaseHelper db;
    ViewUsers_ListAdapter adapter;
    public static List<Users> USER_LIST = new ArrayList<Users>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm_view_users);

        viewUsersTV = findViewById(R.id.viewUsersTV);

        userList = findViewById(R.id.userList);

        ViewGroup headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.header_view_users, userList, false);
        userList.addHeaderView(headerView);

        db = new DatabaseHelper(this);

        populate();
    }

    @Override
    public void onButtonClickListner(String username) {
        Intent intent = new Intent(FMViewUsersActivity.this, FM_ViewSpecificUser.class);
        intent.putExtra("username", username);
        startActivity(intent);

    }

    public void populate()
    {
        Cursor cursor = db.admin_view_users();

        //create user data
        while(cursor.moveToNext()) {
            Users user = new Users(cursor.getString(0), cursor.getString(2), cursor.getString(3), cursor.getString(5));
            USER_LIST.add(user);
        }

        adapter = new ViewUsers_ListAdapter(this, USER_LIST);
        adapter.setCustomButtonListner(FMViewUsersActivity.this);
        userList.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter!=null)
        {
            USER_LIST.clear();
            populate();
        }
    }
}

