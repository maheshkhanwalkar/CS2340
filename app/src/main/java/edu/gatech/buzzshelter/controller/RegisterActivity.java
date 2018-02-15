package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import edu.gatech.buzzshelter.R;

public class RegisterActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button register = findViewById(R.id.registerButton);
        Button cancel = findViewById(R.id.cancelButton);

        /* Implement register */
        /* TODO actually register */
        register.setOnClickListener(v -> {
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
        });

        /* Implement cancel */
        cancel.setOnClickListener(v -> {
            Intent back = new Intent(this, WelcomeActivity.class);
            startActivity(back);
        });
    }

}
