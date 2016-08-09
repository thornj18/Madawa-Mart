package org.pharmart.madawamart;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.pharmart.madawamart.models.PharmacyModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Pharmacies extends Fragment {

    private RecyclerView pharmacyList;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<PharmacyModel> data;
    private PharmacyModel current;
    private PharmacyAdapter pharmacyAdapter;


    public Pharmacies() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pharmacies, container, false);
        initializeViews(view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        pharmacyList.setLayoutManager(mLayoutManager);



        data = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference pharm = database.getReference("pharmacies");
        pharm.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                current = dataSnapshot.getValue(PharmacyModel.class);

                data.add(current);
                mAdapter = new PharmacyAdapter(getActivity(), data);
                pharmacyList.setAdapter(mAdapter);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }

    private void initializeViews(View view) {
        pharmacyList = (RecyclerView) view.findViewById(R.id.pharmacy_list);

    }

    public void setupRecyclerview(){

    }


    public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.ViewHolder> {

        private LayoutInflater inflater;

        List<PharmacyModel> data = Collections.emptyList();

        public PharmacyAdapter(Context context, List<PharmacyModel> data) {
            this.data = data;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public PharmacyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.custom_pharmacy_row, parent,false);
            ViewHolder holder = new ViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(PharmacyAdapter.ViewHolder holder, final int position) {
            final PharmacyModel current = data.get(position);
            holder.txtPharmacyName.setText(current.name);
            holder.txtDistanceAway.setText(current.location +" KM AWAY");
            holder.txtRating.setText(String.valueOf(current.ratings));

            holder.txtPharmacyName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),Pharmacy.class);
                    intent.putExtra("title",current.name);
                    intent.putExtra("distance",current.location);
                    intent.putExtra("rating",current.ratings);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView txtPharmacyName;
            public TextView txtDistanceAway;
            public TextView txtRating;

            public ViewHolder(View itemView) {
                super(itemView);
                txtPharmacyName = (TextView) itemView.findViewById(R.id.pharmacy_title);
                txtDistanceAway = (TextView) itemView.findViewById(R.id.txt_distance);
                txtRating = (TextView) itemView.findViewById(R.id.txt_rating);
            }

        }


    }


}


