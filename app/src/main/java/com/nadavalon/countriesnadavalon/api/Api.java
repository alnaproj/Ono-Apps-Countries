package com.nadavalon.countriesnadavalon.api;

import com.nadavalon.countriesnadavalon.model.Country;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    static final String BASE_URL = "https://restcountries.eu/rest/v2/";

    @GET("all")
    Call<ArrayList<Country>> getCountries();
}
