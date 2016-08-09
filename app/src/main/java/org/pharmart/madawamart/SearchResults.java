package org.pharmart.madawamart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.google.firebase.database.Query;

import org.pharmart.madawamart.models.DrugModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResults extends AppCompatActivity {

    private RecyclerView drugList;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<DrugModel> data;
    private DrugModel current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");

        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);
        // Get a support ActionBar corresponding to this toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Search Results for "+query);
        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drugList = (RecyclerView) findViewById(R.id.search_list);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        drugList.setLayoutManager(mLayoutManager);

        data = new ArrayList<>();




        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference pharm = FirebaseDatabase.getInstance().getReference();

        Query firebaseQuery = pharm.child("drugs").orderByChild("generic_name").equalTo(query);
        firebaseQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Log.d("result", dataSnapshot.toString());
                    current = dataSnapshot.getValue(DrugModel.class);

                    data.add(current);
                    mAdapter = new DrugsAdapter(SearchResults.this, data);
                    drugList.setAdapter(mAdapter);


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
    }

    public class DrugsAdapter  extends RecyclerView.Adapter<DrugsAdapter.ViewHolder>{

        private LayoutInflater inflater;

        List<DrugModel> data = Collections.emptyList();

        public DrugsAdapter(Context context, List<DrugModel> data) {
            this.data = data;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public DrugsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.custom_drug_item, parent,false);
            ViewHolder holder = new ViewHolder(view);

            return holder;
        }

        public DrugModel getItem(int position) {
            return data.get(position);
        }

        @Override
        public void onBindViewHolder(DrugsAdapter.ViewHolder holder, int position) {
            final DrugModel current = data.get(position);
            holder.txtBrandName.setText(current.brand_name);
            holder.txtGenericName.setText(current.generic_name);

            holder.txtBrandName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SearchResults.this, Drug.class);
                    intent.putExtra("cautions",current.cautions);
//                    Log.d("cautions",current.cautions);
                    intent.putExtra("indications",current.indications);
//                    Log.d("indications",current.indications);
                    intent.putExtra("storage",current.storage);
                    intent.putExtra("comments",current.comments);
                    intent.putExtra("generic_name",current.generic_name);
                    intent.putExtra("brand_name",current.brand_name);
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
            private TextView txtBrandName;
            private TextView txtGenericName;

            public ViewHolder(View itemView) {
                super(itemView);
                txtBrandName = (TextView) itemView.findViewById(R.id.brand_name);
                txtGenericName = (TextView) itemView.findViewById(R.id.generic_name);
            }

        }


    }
}


