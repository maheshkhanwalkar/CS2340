package edu.gatech.buzzshelter.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import edu.gatech.buzzshelter.R;
import edu.gatech.buzzshelter.model.control.Manager;
import edu.gatech.buzzshelter.model.user.Shelter;

public class ListActivity extends AppCompatActivity {
    public static final String ARG_SHELTER_ID = "shelter_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        InputStream stream = getResources().openRawResource(R.raw.shelter);
        Manager manager = Manager.getInstance();
        manager.parseShelter(stream);

        RecyclerView recyclerView = findViewById(R.id.shelterList);
        recyclerView.setAdapter(new SimpleRecyclerViewAdapter(manager.getShelters()));
    }

    public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder> {
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
        public void onBindViewHolder(ViewHolder holder, int position) {
            Manager manager = Manager.getInstance();

            holder.mShelter = mShelters.get(position);
            holder.mName.setText(holder.mShelter.getName());
            holder.mContentView.setText(holder.mShelter.getNotes());

            // listener?
            holder.mView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), ShelterActivity.class);
                intent.putExtra(ARG_SHELTER_ID, holder.mShelter.getKey());

                //now just display the new window
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return mShelters.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mName;
            public final TextView mContentView;
            public Shelter mShelter;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mName = view.findViewById(R.id.name);
                mContentView = view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

}
