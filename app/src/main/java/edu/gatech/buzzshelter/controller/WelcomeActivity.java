package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.firebase.ui.auth.AuthUI;
import java.util.Arrays;
import edu.gatech.buzzshelter.R;

public class WelcomeActivity extends AppCompatActivity
{
    private static final int RC_SIGN_IN = 123;
    private AuthUI.SignInIntentBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        builder = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false, true)
                .setAvailableProviders(Arrays.asList(
                        new AuthUI.IdpConfig.EmailBuilder().build(),
                        /* FIXME: Google OAuth has not yet been set up */
                        new AuthUI.IdpConfig.GoogleBuilder().build()
                ));

        startActivityForResult(builder.build(), RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN)
        {
            /* Go to Main page, on success */
            if(resultCode == RESULT_OK)
            {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
            else
            {
                /* Just replay the fragment on "failure" */
                startActivityForResult(builder.build(), RC_SIGN_IN);
            }
        }
    }
}
