package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.buzzshelter.R;
import edu.gatech.buzzshelter.model.facade.DataFacade;
import edu.gatech.buzzshelter.model.data.Shelter;

public class ReserveActivity extends AppCompatActivity
{
    public static final String SHELTER_NAME = "shelter_name";
    public static final String SHELTER_ID = "shelter_id";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataFacade facade = DataFacade.getInstance();

        String name = getIntent().getStringExtra(SHELTER_NAME);
        int id = getIntent().getIntExtra(SHELTER_ID, -1);

        Shelter current = facade.getShelter(name);

        /* Setup UI */
        setupPicker(current);
        addButton(current, id);
    }

    private void setupPicker(Shelter s)
    {
        Spinner typeSpinner = findViewById(R.id.typeSpinner);
        Spinner amtSpinner = findViewById(R.id.amtPicker);

        int max = s.getCapacity().get(0).getAvailable();

        setupType(typeSpinner, amtSpinner, s);
        setupAmt(amtSpinner, max);
    }

    private void setupType(Spinner spinner, Spinner amt, Shelter s)
    {
        List<Shelter.Capacity> caps = s.getCapacity();
        List<String> types = new ArrayList<>(caps.size());

        for(Shelter.Capacity c : caps)
        {
            types.add(c.getCategory());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, types);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Shelter.Capacity current = caps.get(position);
                setupAmt(amt, current.getAvailable());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
    }

    private void setupAmt(Spinner spinner, int max)
    {
        List<Integer> range = new ArrayList<>(max);

        for(int i = 1; i <= max; i++)
            range.add(i);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, range);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void addButton(Shelter s, int id)
    {
        Button reserve = findViewById(R.id.reserveButton);
        Button backBtn = findViewById(R.id.backButton);

        reserve.setOnClickListener(v -> {
            Spinner amtSpinner = findViewById(R.id.amtPicker);
            Spinner typeSpinner = findViewById(R.id.typeSpinner);

            String type = (String)typeSpinner.getSelectedItem();
            int amt = (int)amtSpinner.getSelectedItem();

            DataFacade facade = DataFacade.getInstance();
            boolean result = facade.reserve(s.getName(), type, amt);

            if(!result)
            {
                Snackbar.make(v, "Could not reserve!", Snackbar.LENGTH_SHORT)
                        .show();
            }
            else
            {
                Intent back = new Intent(this, ShelterActivity.class);
                back.putExtra(ShelterActivity.ARG_SHELTER_ID, id);

                startActivity(back);
            }
        });

        backBtn.setOnClickListener(v -> {
            Intent back = new Intent(this, ShelterActivity.class);
            back.putExtra(ShelterActivity.ARG_SHELTER_ID, id);

            startActivity(back);
        });
    }

}
