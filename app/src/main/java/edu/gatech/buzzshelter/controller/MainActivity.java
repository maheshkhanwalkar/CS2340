package edu.gatech.buzzshelter.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import edu.gatech.buzzshelter.R;
import edu.gatech.buzzshelter.model.data.Reservation;
import edu.gatech.buzzshelter.model.facade.DataFacade;

public class MainActivity extends AppCompatActivity
{
    private ProgressDialog progress;
    private DataFacade manager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button logout = findViewById(R.id.backButton);
        Button read = findViewById(R.id.readButton);
        Button reserve = findViewById(R.id.reserveButton);

        manager = DataFacade.getInstance();
        manager.setup();

        /* Do we need to wait on data? */
        if(manager.getShelters().isEmpty())
        {
            progress = new ProgressDialog(this);

            progress.setTitle("Loading shelter information");
            progress.setMessage("Please wait...");
            progress.setCancelable(false);
            progress.show();

            Thread thread = new Thread(this::waitOnLoad);
            thread.start();
        }
        else
        {
            doSetup();
        }

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

        /* Load reservation page */
        reserve.setOnClickListener(v -> {
            Intent landing = new Intent(this, ReserveListActivity.class);
            startActivity(landing);
        });
    }

    private void waitOnLoad()
    {
        while (manager.getShelters().isEmpty())
            ;

        runOnUiThread(this::doSetup);
    }

    private void doSetup()
    {
        if(progress != null)
            progress.dismiss();
    }
}
