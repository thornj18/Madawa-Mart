package org.pharmart.madawamart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

public class Pharmacy extends AppCompatActivity {

    private TextView pharmacyName;
    private TextView openHours;
    private TextView ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmarcy);

        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);
        // Get a support ActionBar corresponding to this toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pharmacyName = (TextView) findViewById(R.id.pharmacy_name);
        openHours = (TextView) findViewById(R.id.open_times);
        ratings = (TextView) findViewById(R.id.ratings);


        Intent intent = getIntent();
        String name = intent.getStringExtra("title");
//        Log.d("name",name);
//        String hours = intent.getStringExtra("hours");
        String rating = intent.getStringExtra("rating");

        pharmacyName.setText(name);
//        openHours.setText(hours);
        if (rating==null) {
            ratings.setText("NO RATINGS YET");
        }else{
            ratings.setText(String.valueOf(rating));

        }
    }
}
