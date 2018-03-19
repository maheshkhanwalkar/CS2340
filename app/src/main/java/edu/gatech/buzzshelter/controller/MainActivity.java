package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
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
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(task -> {
                        Intent landing = new Intent(this, WelcomeActivity.class);
                        startActivity(landing);

                        finish();
                    });
        });

        /* Load shelter page */
        read.setOnClickListener(v -> {
            Intent landing = new Intent(this,
                    edu.gatech.buzzshelter.controller.ListActivity.class);

            startActivity(landing);
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(task -> {
                    Intent landing = new Intent(this, WelcomeActivity.class);
                    startActivity(landing);

                    finish();
                });
    }
}
