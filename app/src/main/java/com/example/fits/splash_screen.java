package com.example.fits;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent i=new Intent(splash_screen.this,MainActivity.class);
                startActivity(i);

            }
        },4000);


    }
}