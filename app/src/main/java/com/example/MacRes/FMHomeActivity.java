package com.example.MacRes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FMHomeActivity extends AppCompatActivity {

    TextView fmHomeTV;
    Button logoutBtn, addFacilityBtn, viewMyProfileBtn, viewFacilitiesBtn, viewUsersBtn, viewReservationsBtn;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmhome);

        fmHomeTV = findViewById(R.id.fmHomeTV);
        logoutBtn = findViewById(R.id.logoutBtn);
        addFacilityBtn = findViewById((R.id.addFacilityBtn));
        viewMyProfileBtn = findViewById(R.id.viewMyProfileBtn);
        viewFacilitiesBtn = findViewById(R.id.viewFacilitiesBtn);
        viewUsersBtn = findViewById(R.id.viewUsersBtn);
        viewReservationsBtn = findViewById(R.id.viewReservationsBtn);

        username = getIntent().getStringExtra("username");

        fmHomeTV.setText("Welcome " + username + "(FM)!");

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                final AlertDialog.Builder builder = new AlertDialog.Builder(FMHomeActivity.this);
                builder.setTitle("Info");
                builder.setMessage("Do you want to logout ??");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(FMHomeActivity.this,LoginActivity.class);
                        startActivity(intent);

                        finish();

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

        viewMyProfileBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(FMHomeActivity.this, ViewProfileActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        viewUsersBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(FMHomeActivity.this, FMViewUsersActivity.class);
                startActivity(intent);
            }
        });

        viewFacilitiesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(FMHomeActivity.this, FMViewFacilitiesActivity.class);
                startActivity(intent);
            }
        });

        addFacilityBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(FMHomeActivity.this, FMAddFacility.class);
                startActivity(intent);
            }
        });

        viewReservationsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(FMHomeActivity.this, FMViewReservationsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        final AlertDialog.Builder builder = new AlertDialog.Builder(FMHomeActivity.this);
        builder.setTitle("Info");
        builder.setMessage("Do you want to exit? Once you do, you will be logged out immediately.");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
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
}
