package com.nadavalon.countriesnadavalon.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nadavalon.countriesnadavalon.R;
import com.nadavalon.countriesnadavalon.adapter.CountryAdapter;
import com.nadavalon.countriesnadavalon.model.Country;

import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class CountryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CountryAdapter adapter;
    List<String> borders;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle data = getIntent().getExtras();
        Country country = data.getParcelable("country");

        borders = country.getBorders();
        if(borders.size() == 0){
            borders.add("No neighbor countries");
        }
        adapter = new CountryAdapter(CountryActivity.this, borders);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CustomIntent.customType(this, "right-to-left");
    }
}