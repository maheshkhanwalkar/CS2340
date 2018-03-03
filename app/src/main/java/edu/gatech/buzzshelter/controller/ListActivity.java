package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import edu.gatech.buzzshelter.R;
import edu.gatech.buzzshelter.model.control.Manager;
import edu.gatech.buzzshelter.model.user.Shelter;

public class ListActivity extends AppCompatActivity
{
    public static final String ARG_SHELTER_ID = "shelter_id";
    public final Manager manager = Manager.getInstance();
    private List<Shelter> shelterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        InputStream stream = getResources().openRawResource(R.raw.shelter);
        manager.parseShelter(stream);
        shelterList = new ArrayList<>(manager.getShelters());

        EditText searchBar = findViewById(R.id.searchBar);
        RecyclerView recyclerView = findViewById(R.id.shelterList);
        recyclerView.setAdapter(new SimpleRecyclerViewAdapter(shelterList));

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {

                List<Shelter> total = manager.getShelters();

                shelterList.clear();
                shelterList.addAll(total);

                shelterList.removeIf(shelter -> !(
                    shelter.getRestrict().toUpperCase().contains(editable.toString().toUpperCase()) ||
                    shelter.getName().toUpperCase().contains(editable.toString().toUpperCase()))
                );
                
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });

        Button back = findViewById(R.id.backButton);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder>
    {
        private final List<Shelter> mShelters;

        public SimpleRecyclerViewAdapter(List<Shelter> shelters) {
            mShelters = shelters;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        public int getItemCount() {
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
