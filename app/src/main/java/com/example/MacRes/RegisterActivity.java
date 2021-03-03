package com.example.MacRes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import model.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    TextView registerTV, usernameTV, passwordTV, confirmPasswordTV, firstNameTV, lastNameTV, utaIdTV, roleTV, phoneTV, emailTV, addressTV, cityTV, stateTV, zipCodeTV;
    EditText usernameET, passwordET, confirmPasswordET, firstNameET, lastNameET, utaIdET, phoneET, emailET,streetAddressET, cityET, zipCodeET;
    Spinner roleSpinner, stateSpinner;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myDb = new DatabaseHelper(this);

        registerTV = findViewById(R.id.registerTV);
        usernameTV = findViewById(R.id.usernameTV);
        passwordTV = findViewById(R.id.passwordTV);
        confirmPasswordTV = findViewById(R.id.confirmPasswordTV);
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

        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        confirmPasswordET = findViewById(R.id.confirmPasswordET);
        firstNameET = findViewById(R.id.firstNameET);
        lastNameET = findViewById(R.id.lastNameET);
        utaIdET = findViewById(R.id.utaIdET);
        phoneET = findViewById(R.id.phoneET);
        emailET = findViewById(R.id.emailET);
        streetAddressET = findViewById(R.id.addressET);
        cityET = findViewById(R.id.cityET);
        zipCodeET = findViewById(R.id.zipCodeET);
        roleSpinner = findViewById(R.id.roleSpinner);
        stateSpinner = findViewById(R.id.stateSpinner);
        registerBtn = findViewById(R.id.registerBtn); //button to send data in the activity to the db

        /* Calling the AddData method which will insert the data into the database. */
        AddData();
    }

    public void AddData(){
        registerBtn.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insert_register(usernameET.getText().toString(),
                                passwordET.getText().toString(),
                                firstNameET.getText().toString(),
                                lastNameET.getText().toString(),
                                utaIdET.getText().toString(),
                                roleSpinner.getSelectedItem().toString(),
                                phoneET.getText().toString(),
                                emailET.getText().toString(),
                                streetAddressET.getText().toString(),
                                cityET.getText().toString(),
                                stateSpinner.getSelectedItem().toString(),
                                zipCodeET.getText().toString());

                        if(isInserted == true){
                            Toast.makeText(RegisterActivity.this,"Successfully Registered",Toast.LENGTH_LONG).show();
                            Intent i1 = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i1);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this,"Registration Failed. Try again!",Toast.LENGTH_LONG).show();
                            Intent i2 =  new Intent(RegisterActivity.this, RegisterActivity.class);
                            startActivity(i2);
                        }
                    }
                }
        );
    }
}
