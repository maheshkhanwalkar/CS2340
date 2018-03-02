package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import edu.gatech.buzzshelter.R;

public class WelcomeActivity extends AppCompatActivity
{
    private boolean disableBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button login = findViewById(R.id.loginButton);
        Button register = findViewById(R.id.registerButton);

        login.setOnClickListener(v -> {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        });

        register.setOnClickListener(v -> {
            Intent registerIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerIntent);
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        
        /* Check if back button should be disabled */
        disableBack = getIntent().getBooleanExtra("DISABLE_BACK",
                false);
    }

    @Override
    public void onBackPressed()
    {
        /* Disable back, if logout was pressed  */
        if(!disableBack)
            super.onBackPressed();
    }
}
