package com.example.MacRes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import model.DatabaseHelper;

public class Admin_ViewSpecificUser extends AppCompatActivity {

    TextView usernameTV, firstNameTV, lastNameTV, utaIdTV, roleTV, phoneTV, emailTV, addressTV, cityTV, stateTV, zipCodeTV, noshowsTV, revokedTV, violationsTV;
    Button changeRoleBtn, revokeBtn;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__view_specific_user);

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

        changeRoleBtn = findViewById(R.id.noshowsBtn);
        revokeBtn = findViewById(R.id.revokeBtn);

        String username = getIntent().getStringExtra("username");
        usernameTV.append(username);
        get_selected_user(username);

        ButtonFunctions();
    }

    public void get_selected_user(String username)
    {
        String query = "SELECT * FROM users WHERE username LIKE '" + username + "'";
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

            if(user.getString(13).equals("YES"))
                revokeBtn.setText("Un-revoke");
            else
                revokeBtn.setText("Revoke");
        }
    }

    public void ButtonFunctions()
    {
        changeRoleBtn.setOnClickListener(
            new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String[] s = {"USER", "FACILITY MANAGER", "ADMIN"};
                    final ArrayAdapter<String> adp = new ArrayAdapter<String>(Admin_ViewSpecificUser.this, android.R.layout.simple_spinner_item, s);

                    final Spinner sp = new Spinner(Admin_ViewSpecificUser.this);
                    sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    sp.setAdapter(adp);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(Admin_ViewSpecificUser.this);
                    builder.setTitle("Change User Role");
                    builder.setMessage("Select a role for this user...");

                    builder.setView(sp);

                    builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String role = sp.getSelectedItem().toString();
                            if(changeUserRole(role))
                            {
                                dialogInterface.dismiss();
                                Toast.makeText(Admin_ViewSpecificUser.this,"Changed Role Successfully!", Toast.LENGTH_SHORT).show();
                                roleTV.setText("Role: "+ role);
                            }
                            else
                            {
                                dialogInterface.dismiss();
                                Toast.makeText(Admin_ViewSpecificUser.this,"Cannot change role now! Try again later!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    builder.create().show();
                }
            });

        revokeBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String revoke = revokeBtn.getText().toString();
                        final AlertDialog.Builder builder = new AlertDialog.Builder(Admin_ViewSpecificUser.this);
                        builder.setTitle("Alert");

                        if(revoke.equals("Revoke"))
                            builder.setMessage("Are you sure you want to revoke this user: " + getIntent().getStringExtra("username") + "?");
                        else
                            builder.setMessage("Are you sure you want to un-revoke this user: " + getIntent().getStringExtra("username") + "?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();

                                if(revokeUser(revoke))
                                {
                                    if(revoke.equals("Revoke")) {
                                        Toast.makeText(Admin_ViewSpecificUser.this, "User is now revoked!", Toast.LENGTH_SHORT).show();
                                        revokeBtn.setText("Unrevoke");
                                        revokedTV.setText("Is Revoked: YES");
                                    }
                                    else
                                    {
                                        Toast.makeText(Admin_ViewSpecificUser.this, "User is now un-revoked!", Toast.LENGTH_SHORT).show();
                                        revokeBtn.setText("Revoke");
                                        revokedTV.setText("Is Revoked: NO");
                                    }
                                }
                                else
                                {
                                    Toast.makeText(Admin_ViewSpecificUser.this, "Revoke failed! Try again later!", Toast.LENGTH_SHORT).show();
                                }
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
    }

    public boolean revokeUser(String revoke)
    {
        String revokeValue = "";
        if(revoke.equals("Revoke"))
        {
            revokeValue = "YES";
        }
        else
        {
            revokeValue = "NO";
        }
        return myDB.admin_revokeUser(getIntent().getStringExtra("username"), revokeValue);
    }

    public boolean changeUserRole(String role)
    {
        return myDB.admin_changeRole(getIntent().getStringExtra("username"), role);
    }
}
