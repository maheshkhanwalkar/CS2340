package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import edu.gatech.buzzshelter.R;

public class LoginActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button login = findViewById(R.id.loginButton);
        Button cancel = findViewById(R.id.cancelButton);

        /* Implement cancel feature */
        cancel.setOnClickListener(v -> {
            Intent back = new Intent(this, WelcomeActivity.class);
            startActivity(back);
        });

        /* Implement login feature */
        login.setOnClickListener(v -> {
            /* TODO */
        });
    }

}
