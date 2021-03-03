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

public class FMSetNoShowsActivity extends AppCompatActivity {

    TextView noShowTV, usernameLabelTV, usernameTV, facilityNameTV, dateTV, timeTV;
    EditText facilityNameET, dateET, timeET;
    Button submitBtn;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm_set_no_shows);

        noShowTV = findViewById(R.id.requestTV);
        usernameLabelTV = findViewById(R.id.usernameLabelTV);
        usernameTV = findViewById(R.id.usernameTV);
        facilityNameTV = findViewById(R.id.facilityNameTV);
        dateTV = findViewById(R.id.dateTV);
        timeTV = findViewById(R.id.timeTV);

        facilityNameET = findViewById(R.id.facilityNameET);
        dateET = findViewById(R.id.dateET);
        timeET = findViewById(R.id.timeET);

        submitBtn = findViewById(R.id.submitBtn);

        myDB = new DatabaseHelper(this);
        usernameTV.setText(getIntent().getStringExtra("username"));

        ButtonFunction();
    }

    public void ButtonFunction()
    {
        submitBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //write to database
                String username = usernameTV.getText().toString();
                String facilityName = facilityNameET.getText().toString();
                String date = dateET.getText().toString();
                String time = timeET.getText().toString();

                Boolean result = myDB.setNoShow(username, facilityName, date, time);

                if(result)
                {
                    Toast.makeText(FMSetNoShowsActivity.this, "NoShow report made successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FMSetNoShowsActivity.this, FM_ViewSpecificUser.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(FMSetNoShowsActivity.this, "Failed! Try again later!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
