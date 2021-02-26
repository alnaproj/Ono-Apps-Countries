package com.nadavalon.countriesnadavalon.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nadavalon.countriesnadavalon.api.Api;
import com.nadavalon.countriesnadavalon.api.MyClient;
import com.nadavalon.countriesnadavalon.model.Country;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CountriesViewModel extends ViewModel {
    public String nativeName;
    public String name;
    public Double area;
    public List<String> borders;
    public MutableLiveData<ArrayList<CountriesViewModel>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<CountriesViewModel> arrayList;
    private ArrayList<Country> countryList;

    public CountriesViewModel() {
    }

    public CountriesViewModel(Country country) {
        this.nativeName = country.getNativeName();
        this.name = country.getName();
        Double area = country.getArea();
        if (area == null) {
            area = 0.0;
        }
        this.area = area;
        this.borders = country.getBorders();
    }

    public MutableLiveData<ArrayList<CountriesViewModel>> getMutableLiveData() {
        arrayList = new ArrayList<>();
        Api api = MyClient.getInstance().getApi();
        Call<ArrayList<Country>> call = api.getCountries();
        call.enqueue(new Callback<ArrayList<Country>>() {

            @Override
            public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                countryList = new ArrayList<>();
                countryList = response.body();
                for (int i = 0; i < countryList.size(); i++) {
                    Country myl = countryList.get(i);
                    CountriesViewModel myListViewModel = new CountriesViewModel(myl);
                    arrayList.add(myListViewModel);
                    mutableLiveData.setValue(arrayList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
            }
        });

        return mutableLiveData;
    }
}