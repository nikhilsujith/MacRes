package com.example.MacRes;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import model.DatabaseHelper;

public class ViewProfileActivity extends AppCompatActivity {

    TextView profileTV, usernameTV, firstNameTV, lastNameTV, utaIdTV, roleTV, phoneTV, emailTV, addressTV, cityTV, stateTV, zipCodeTV, emptyTV;
    EditText firstNameET, lastNameET, utaIdET,phoneET, emailET, addressET, cityET, zipCodeET;
    Spinner stateSpinner;
    Button updateProfileBtn;
    String username;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        profileTV = findViewById(R.id.profileTV);
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
        emptyTV = findViewById(R.id.emptyTV);

        updateProfileBtn = findViewById(R.id.updateProfileBtn);

        firstNameET = findViewById(R.id.firstNameET);
        lastNameET = findViewById(R.id.lastNameET);
        utaIdET = findViewById(R.id.utaIdET);
        phoneET = findViewById(R.id.phoneET);
        emailET = findViewById(R.id.emailET);
        addressET = findViewById(R.id.addressET);
        cityET = findViewById(R.id.cityET);
        zipCodeET = findViewById(R.id.zipCodeET);

        stateSpinner = findViewById(R.id.stateSpinner);

        myDB = new DatabaseHelper(this);

        username = getIntent().getStringExtra("username");
        get_user_profile(username);

        updateUserProfile();
    }

    public void get_user_profile(String username)
    {
        String query = "SELECT * FROM users WHERE username LIKE '" + username + "';";
        Cursor profileResult = myDB.fetchSpecificUser(query);

        while(profileResult.moveToNext()) {
            usernameTV.setText("Username:\t" + profileResult.getString(0));  //index 0 is the username of that user in the database
            firstNameET.setText(profileResult.getString(2));  //index 2 is the first name of that user in the database
            lastNameET.setText(profileResult.getString(3));  //index 3 is the last name of that user in the database
            utaIdET.setText(profileResult.getString(4));  //index 4 is the uta id of that user in the database
            roleTV.setText("Role:\t" + profileResult.getString(5));  //index 5 is the role of that user in the database
            phoneET.setText(profileResult.getString(6));  //index 6 is the phone of that user in the database
            emailET.setText(profileResult.getString(7));  //index 7 is the email of that user in the database
            addressET.setText(profileResult.getString(8)); //index 8 is the address of that user in the database
            cityET.setText(profileResult.getString(9));  //index 9 is the city of that user in the database
            stateSpinner.setSelection(((ArrayAdapter)stateSpinner.getAdapter()).getPosition(profileResult.getString(10)));  //index 10 is the state of that user in the database
            zipCodeET.setText(profileResult.getString(11));  //index 11 is the zip code of that user in the database
        }
    }

    public void updateUserProfile() {
        updateProfileBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean result = myDB.updateAdminProfile(username, firstNameET.getText().toString(), lastNameET.getText().toString(), utaIdET.getText().toString(),
                        phoneET.getText().toString(), emailET.getText().toString(), addressET.getText().toString(), cityET.getText().toString(),
                        stateSpinner.getSelectedItem().toString(), zipCodeET.getText().toString());

                if(result)
                    Toast.makeText(ViewProfileActivity.this, "Update Successful!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ViewProfileActivity.this, "Update failed! Try again later!", Toast.LENGTH_SHORT).show();

                get_user_profile(username);
            }
        });
    }
}
