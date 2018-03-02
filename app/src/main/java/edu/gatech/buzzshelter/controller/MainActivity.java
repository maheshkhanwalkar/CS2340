package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import edu.gatech.buzzshelter.R;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button logout = findViewById(R.id.backButton);
        Button read = findViewById(R.id.readButton);

        /* Logout implementation */
        logout.setOnClickListener(v -> {
            Intent landing = new Intent(this, WelcomeActivity.class);
            landing.putExtra("DISABLE_BACK", true);

            startActivity(landing);
        });

        /* Load shelter page */
        read.setOnClickListener(v -> {
            Intent landing = new Intent(this, edu.gatech.buzzshelter.controller.ListActivity.class);
            startActivity(landing);
        });
    }
}
