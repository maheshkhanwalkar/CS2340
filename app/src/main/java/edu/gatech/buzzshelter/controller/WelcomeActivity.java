package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;

import edu.gatech.buzzshelter.R;

public class WelcomeActivity extends AppCompatActivity
{
    private boolean disableBack = false;
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* TODO: handle result of sign-up/login */
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false, true)
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                /* FIXME: Google OAuth has not yet been set up */
                                new AuthUI.IdpConfig.GoogleBuilder().build()
                        ))
                        .build()
                , RC_SIGN_IN);
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
