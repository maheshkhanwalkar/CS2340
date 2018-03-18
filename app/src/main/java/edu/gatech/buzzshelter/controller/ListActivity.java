package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.gatech.buzzshelter.R;
import edu.gatech.buzzshelter.model.db.util.Toolkit;
import edu.gatech.buzzshelter.model.facade.DataFacade;
import edu.gatech.buzzshelter.model.user.Shelter;

public class ListActivity extends AppCompatActivity
{
    public static final String ARG_SHELTER_ID = "shelter_id";
    public final DataFacade manager = DataFacade.getInstance();
    private List<Shelter> shelterList = new ArrayList<>();

    private Set<Shelter> ageSet(Set<Shelter> def, Spinner ageSpinner)
    {
        /* Default set */
        String age = ageSpinner.getSelectedItem().toString();

        if(age.equals("Any"))
            return def;

        return manager.matchAge(age);
    }

    private Set<Shelter> genderSet(Set<Shelter> def, Spinner gSpinner)
    {
                /* Default set */
        String gender = gSpinner.getSelectedItem().toString();

        if(gender.equals("Any"))
            return def;

        return manager.matchGender(gender);
    }

    private Set<Shelter> nameSet(Set<Shelter> def, EditText nameBar)
    {
        String name = nameBar.getText().toString().toLowerCase();

                /* Default set */
        if(name.equals(""))
            return def;

        return manager.matchName(name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        InputStream stream = getResources().openRawResource(R.raw.shelter);
        manager.parseShelter(stream);
        shelterList = manager.getShelters();

        /* Search parameters */
        EditText nameBar = findViewById(R.id.searchBar);
        Spinner gSpinner = findViewById(R.id.gSpinner);
        Spinner ageSpinner = findViewById(R.id.ageSpinner);


        RecyclerView recyclerView = findViewById(R.id.shelterList);
        recyclerView.setAdapter(new SimpleRecyclerViewAdapter(shelterList));

        /* Isolated */
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.gender_array, android.R.layout.simple_spinner_dropdown_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            gSpinner.setAdapter(adapter);

            gSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    /* Process the current state */
                    Set<Shelter> copy = new HashSet<>(manager.getShelters());
                    shelterList.clear();

                    /* Intersect the results */
                    Set<Shelter> all = Toolkit.intersect(nameSet(copy, nameBar),
                            ageSet(copy, ageSpinner), genderSet(copy, gSpinner));

                    shelterList.addAll(all);
                    recyclerView.getAdapter().notifyDataSetChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent)
                {

                }
            });
        }

        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.age_array, android.R.layout.simple_spinner_dropdown_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ageSpinner.setAdapter(adapter);

            ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    /* Process the current state */
                    Set<Shelter> copy = new HashSet<>(manager.getShelters());
                    shelterList.clear();

                    /* Intersect the results */
                    Set<Shelter> all = Toolkit.intersect(nameSet(copy, nameBar),
                            ageSet(copy, ageSpinner), genderSet(copy, gSpinner));

                    shelterList.addAll(all);
                    recyclerView.getAdapter().notifyDataSetChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent)
                {

                }
            });
        }

        nameBar.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable)
            {
                /* Process the entered words */
                Set<Shelter> copy = new HashSet<>(manager.getShelters());
                shelterList.clear();

                /* Intersect the results */
                Set<Shelter> result = Toolkit.intersect(nameSet(copy, nameBar),
                        ageSet(copy, ageSpinner), genderSet(copy, gSpinner));

                shelterList.addAll(result);
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }

    public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder>
    {
        private final List<Shelter> mShelters;

        SimpleRecyclerViewAdapter(List<Shelter> shelters)
        {
            mShelters = shelters;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.shelter_list_content, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {
            holder.mShelter = mShelters.get(position);
            holder.mName.setText(holder.mShelter.getName());
            holder.mContentView.setText(holder.mShelter.getNotes());

            holder.mView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), ShelterActivity.class);
                intent.putExtra(ARG_SHELTER_ID, holder.mShelter.getKey());

                /* Display detailed view */
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount()
        {
            return mShelters.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            final View mView;
            final TextView mName;
            final TextView mContentView;
            Shelter mShelter;

            ViewHolder(View view)
            {
                super(view);

                mView = view;
                mName = view.findViewById(R.id.name);
                mContentView = view.findViewById(R.id.content);
            }

            @Override
            public String toString()
            {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

}
