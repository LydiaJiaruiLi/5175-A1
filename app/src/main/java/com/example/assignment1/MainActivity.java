package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import java.util.Arrays;
import java.util.Map;

/*
    This class is used to manage the activities for this app.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a service to play a background
        Intent intent = new Intent(MainActivity.this, BackgroundMusic.class);
        startService(intent);
    }
}