package com.example.MacRes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import model.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    TextView loginTV, areYouNewTV;
    EditText usernameET, passwordET;
    Button loginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDb = new DatabaseHelper(this);

        loginTV = findViewById(R.id.loginTV);
        areYouNewTV = findViewById(R.id.areYouNewTV);
        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);

        LoginData();
    }

    public void LoginData(){
        loginBtn.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        String username = usernameET.getText().toString();
                        String password = passwordET.getText().toString();
                        String query = "SELECT * FROM users WHERE username like '"+username+ "' AND password like '"+password+"'";

                        Cursor loginResult = myDb.login_query(query);

                        if(loginResult.getCount() != 0){
                            while(loginResult.moveToNext()) {
                                String role = loginResult.getString(5);  //index 5 is the role of that user
                                String revoked = loginResult.getString(13);   //index 13 is the revoked of that user in the database
                                Intent i1;

                                switch (role) {
                                    case "USER":
                                        i1 = new Intent(LoginActivity.this, UserHomeActivity.class);
                                        break;
                                    case "FACILITY MANAGER":
                                        i1 = new Intent(LoginActivity.this, FMHomeActivity.class);
                                        break;
                                    case "ADMIN":
                                        i1 = new Intent(LoginActivity.this, AdminHomeActivity.class);
                                        break;
                                    default:
                                        i1 = new Intent(LoginActivity.this, LoginActivity.class);
                                }

                                Toast.makeText(LoginActivity.this,"Login Successful!",Toast.LENGTH_LONG).show();
                                i1.putExtra("username",username);
                                i1.putExtra("revoked", revoked);
                                startActivity(i1);

                                //Removing MainActivity[Login Screen] from the stack for preventing back button press.
                                finish();
                            }
                        }
                        else
                        {
                            //username or password does not exist
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setTitle("Alert");
                            builder.setMessage("Username or Password is wrong.");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    dialogInterface.dismiss();

                                }
                            });

                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                }
        );

        registerBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
