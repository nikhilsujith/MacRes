package com.example.MacRes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserHomeActivity extends AppCompatActivity {

    TextView welcomeUserTV;
    Button logoutBtn, viewMyProfileBtn, viewMyReservationsBtn, viewFacilitiesBtn, viewMyViolationsBtn;
    String username, revoked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        welcomeUserTV = findViewById(R.id.welcomeUserTV);
        logoutBtn = findViewById(R.id.logoutBtn);
        viewMyProfileBtn = findViewById(R.id.viewMyProfileBtn);
        viewMyReservationsBtn = findViewById(R.id.viewMyReservationsBtn);
        viewFacilitiesBtn = findViewById(R.id.viewFacilitiesBtn);
        viewMyViolationsBtn = findViewById(R.id.viewMyViolationsBtn);

        username = getIntent().getStringExtra("username");
        revoked = getIntent().getStringExtra("revoked");

        welcomeUserTV.setText("Welcome " + username + "(USER)!");

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                final AlertDialog.Builder builder = new AlertDialog.Builder(UserHomeActivity.this);
                builder.setTitle("Info");
                builder.setMessage("Do you want to logout ??");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(UserHomeActivity.this, LoginActivity.class);
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
                Intent intent = new Intent(UserHomeActivity.this, ViewProfileActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        viewMyReservationsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this, ViewUserReservationsActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        viewFacilitiesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this, UserViewFacilitiesActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("revoked", revoked);
                startActivity(intent);
            }
        });

        viewMyViolationsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this, UserViewViolationsActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        final AlertDialog.Builder builder = new AlertDialog.Builder(UserHomeActivity.this);
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
