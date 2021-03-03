package com.example.MacRes;


//test slack

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import model.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    ImageView imageLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageLogo = findViewById(R.id.imageLogo);

        myDB = new DatabaseHelper(this);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                try
                {
                    sleep(3000);
                    Intent intent = (new Intent(MainActivity.this,LoginActivity.class));
                    startActivity(intent);
                    finish();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
