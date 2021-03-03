package com.example.MacRes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminHomeActivity extends AppCompatActivity {

    TextView adminHomeTV;
    Button logoutBtn, viewMyProfileBtn, viewUsersBtn;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        adminHomeTV = findViewById(R.id.adminHomeTV);
        logoutBtn = findViewById(R.id.logoutBtn);
        viewMyProfileBtn = findViewById(R.id.viewMyProfileBtn);
        viewUsersBtn = findViewById(R.id.viewUsersBtn);

        username = getIntent().getStringExtra("username");

        adminHomeTV.setText("Welcome " + username + "(ADMIN)!");

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                final AlertDialog.Builder builder = new AlertDialog.Builder(AdminHomeActivity.this);
                builder.setTitle("Info");
                builder.setMessage("Do you want to logout ??");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(AdminHomeActivity.this,LoginActivity.class);
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
                Intent intent = new Intent(AdminHomeActivity.this, ViewProfileActivity.class);
                intent.putExtra("username",username);
                startActivityForResult(intent, 1);
            }
        });

        viewUsersBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(AdminHomeActivity.this,AdminViewUsersActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        final AlertDialog.Builder builder = new AlertDialog.Builder(AdminHomeActivity.this);
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
