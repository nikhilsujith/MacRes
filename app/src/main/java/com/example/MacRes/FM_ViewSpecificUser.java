package com.example.MacRes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import model.DatabaseHelper;

public class FM_ViewSpecificUser extends AppCompatActivity {

    TextView usernameTV, firstNameTV, lastNameTV, utaIdTV, roleTV, phoneTV, emailTV, addressTV, cityTV, stateTV, zipCodeTV, noshowsTV, revokedTV, violationsTV;
    Button noshowsBtn, reportBtn;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm_view_specific_user);

        myDB = new DatabaseHelper(this);

        usernameTV = findViewById(R.id.usernameLabelTV);
        firstNameTV = findViewById(R.id.firstNameTV);
        lastNameTV = findViewById(R.id.lastNameTV);
        utaIdTV = findViewById(R.id.utaIdTV);
        roleTV = findViewById(R.id.roleTV);
        phoneTV = findViewById(R.id.phoneTV);
        emailTV = findViewById(R.id.emailTV);
        addressTV = findViewById(R.id.addressTV);
        cityTV = findViewById(R.id.cityTV);
        stateTV = findViewById(R.id.stateTV);
        zipCodeTV = findViewById(R.id.zipCodeTV);
        noshowsTV = findViewById(R.id.noShowsTV);
        revokedTV = findViewById(R.id.revokedTV);
        violationsTV = findViewById(R.id.violationsTV);

        noshowsBtn = findViewById(R.id.noshowsBtn);
        reportBtn = findViewById(R.id.reportBtn);

        String username = getIntent().getStringExtra("username");
        usernameTV.append(username);
        get_selected_user(username);

        ButtonFunctions();
    }

    public void get_selected_user(String username)
    {
        String query = "SELECT * FROM users WHERE username LIKE '" + username + "';";
        Cursor user = myDB.fetchSpecificUser(query);

        while (user.moveToNext()) {
            firstNameTV.append(user.getString(2));  //index 2 is the first_name of that user in the database
            lastNameTV.append(user.getString(3));  //index 3 is the last_name of that user in the database
            utaIdTV.append(user.getString(4));  //index 4 is the uta_id of that user in the database
            roleTV.append(user.getString(5));  //index 5 is the role of that user in the database
            phoneTV.append(user.getString(6));  //index 6 is the phone of that user in the database
            emailTV.append(user.getString(7));  //index 7 is the email of that user in the database
            addressTV.append(user.getString(8));  //index 8 is the street_address of that user in the database
            cityTV.append(user.getString(9));  //index 9 is the city of that user in the database
            stateTV.append(user.getString(10));  //index 10 is the state of that user in the database
            zipCodeTV.append(user.getString(11));  //index 11 is the zip code of that user in the database
            noshowsTV.append(user.getString(12));  //index 12 is the no shows of that user in the database
            revokedTV.append(user.getString(13));  //index 13 is the revoked of that user in the database
            violationsTV.append(user.getString(14));  //index 14 is the violations of that user in the database
        }
    }

    public void ButtonFunctions()
    {
        noshowsBtn.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //set a no show or go to a new activity
                        Intent intent = new Intent(FM_ViewSpecificUser.this, FMSetNoShowsActivity.class);
                        intent.putExtra("username", getIntent().getStringExtra("username"));
                        startActivity(intent);
                    }
                });

        reportBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //go to new activity to report a user
                        Intent intent = new Intent(FM_ViewSpecificUser.this, ReportViolation.class);
                        intent.putExtra("username", getIntent().getStringExtra("username"));
                        startActivity(intent);
                    }
                });
    }
}