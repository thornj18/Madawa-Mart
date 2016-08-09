package org.pharmart.madawamart;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Drug extends AppCompatActivity {

    private Toolbar mainBar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView txtGenericName, txtBrandName, drugPrice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);

        mainBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mainBar);


        txtBrandName = (TextView) findViewById(R.id.brand_name);
        txtGenericName = (TextView) findViewById(R.id.generic_name);
        drugPrice = (TextView) findViewById(R.id.price_range);

        // Get a support ActionBar corresponding to this toolbar
        getSupportActionBar().setTitle("");


        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        viewPager = (ViewPager) findViewById(R.id.pager);



        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);


        txtBrandName.setText(getIntent().getStringExtra("brand_name"));
        txtGenericName.setText(getIntent().getStringExtra("generic_name"));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Log.d("",data.getStringExtra("indications"));
    }

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    public String indicationsData(){
        return getIntent().getStringExtra("indications");
    }

    public String storageData(){
        return getIntent().getStringExtra("storage");
    }

    public String commentData(){
        return getIntent().getStringExtra("comments");
    }

    public String cautionData(){
        return getIntent().getStringExtra("cautions");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Indications(), "Indication");
        adapter.addFragment(new Cautions(), "Cautions");
        adapter.addFragment(new Storage(), "Storage");
        adapter.addFragment(new  Comments(), "Comment");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public static class Indications extends Fragment {
        public Drug drug_indications;

        public TextView txtIndications;

        public Indications() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            View view = inflater.inflate(R.layout.fragment_indications, container, false);
            txtIndications = (TextView) view.findViewById(R.id.txtIndications);
            Drug drug = (Drug) getActivity();
            setIndications(drug.indicationsData());
            return view;
        }

        public void setIndications(String indices) {
            txtIndications.setText(indices);

        }

    }
//
    public static class Cautions extends Fragment {

        public TextView txtCautions;

        public Cautions() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_cautions, container, false);
            txtCautions = (TextView) view.findViewById(R.id.txtCautions);
            Drug drug = (Drug) getActivity();
            setCautions(drug.cautionData());
            return view;
        }

        public void setCautions(String cautions) {
            if (!cautions.isEmpty())
                txtCautions.setText(cautions);
        }

    }

    public static class Comments extends Fragment {

        public TextView txtComments;

        public Comments() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_comments, container, false);
            txtComments = (TextView) view.findViewById(R.id.txtComments);
            Drug drug = (Drug) getActivity();
            setComments(drug.commentData());
            return view;
        }

        public void setComments(String comments) {
            if (!comments.isEmpty())
                txtComments.setText(comments);
        }

    }

    public static class Storage extends Fragment {

        public TextView txtStorage;

        public Storage() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            View view = inflater.inflate(R.layout.fragment_storage, container, false);
            txtStorage = (TextView) view.findViewById(R.id.txtStorage);
            Drug drug = (Drug) getActivity();
            setStorage(drug.storageData());
            return view;
        }

        public void setStorage(String storageInfo) {
            if (!storageInfo.isEmpty())
                txtStorage.setText(storageInfo);
        }

    }




}


