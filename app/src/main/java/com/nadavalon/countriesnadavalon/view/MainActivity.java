package com.nadavalon.countriesnadavalon.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nadavalon.countriesnadavalon.R;
import com.nadavalon.countriesnadavalon.adapter.CountriesAdapter;
import com.nadavalon.countriesnadavalon.model.Country;
import com.nadavalon.countriesnadavalon.viewmodel.CountriesViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import maes.tech.intentanim.CustomIntent;


public class MainActivity extends AppCompatActivity {
    CountriesViewModel countriesViewModel;
    RecyclerView recyclerView;
    CountriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        countriesViewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);
        countriesViewModel.getMutableLiveData().observe(this, new Observer<ArrayList<CountriesViewModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CountriesViewModel> countriesList) {
                recyclerView.setAdapter(adapter = new CountriesAdapter(MainActivity.this, countriesList, new CountriesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CountriesViewModel item) {
                        Intent intent = new Intent(MainActivity.this, CountryActivity.class);
                        intent.putExtra("country", new Country(item.nativeName, item.name, item.area, item.borders));
                        startActivity(intent);
                        CustomIntent.customType(MainActivity.this, "left-to-right");
                    }
                }
                ));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort, menu);
        return true;
    }

    public boolean onOptionsItemSelected(final MenuItem item) {
        countriesViewModel.getMutableLiveData().observe(this, new Observer<ArrayList<CountriesViewModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CountriesViewModel> countriesList) {
                switch (item.getItemId()) {
                    case R.id.name_asc:
                        Collections.sort(countriesList, new Comparator<CountriesViewModel>() {
                            @Override
                            public int compare(CountriesViewModel o1, CountriesViewModel o2) {
                                return o1.name.compareToIgnoreCase(o2.name);
                            }
                        });
                        break;
                    case R.id.name_dsc:
                        Collections.sort(countriesList, new Comparator<CountriesViewModel>() {
                            @Override
                            public int compare(CountriesViewModel o1, CountriesViewModel o2) {
                                return o2.name.compareToIgnoreCase(o1.name);
                            }
                        });
                        break;

                    case R.id.area_asc:
                        Collections.sort(countriesList, new Comparator<CountriesViewModel>() {
                            @Override
                            public int compare(CountriesViewModel o1, CountriesViewModel o2) {
                                if(o1.area == null){
                                    return 1;
                                }
                                if(o2.area == null){
                                    return -1;
                                }
                                return Double.compare(o1.area,o2.area);
                            }
                        });
                        break;
                    case R.id.area_dsc:
                        Collections.sort(countriesList, new Comparator<CountriesViewModel>() {
                            @Override
                            public int compare(CountriesViewModel o1, CountriesViewModel o2) {
                                if(o1.area == null){
                                    return 1;
                                }
                                if(o2.area == null){
                                    return -1;
                                }
                                return Double.compare(o2.area,o1.area);
                            }
                        });
                        break;
                }

                recyclerView.setAdapter(adapter = new CountriesAdapter(MainActivity.this, countriesList, new CountriesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CountriesViewModel item) {
                        Intent intent = new Intent(MainActivity.this, CountryActivity.class);
                        intent.putExtra("country", new Country(item.nativeName, item.name, item.area, item.borders));
                        startActivity(intent);
                        CustomIntent.customType(MainActivity.this, "left-to-right");
                    }
                }
                ));
            }
        });
        return super.onOptionsItemSelected(item);
    }
}