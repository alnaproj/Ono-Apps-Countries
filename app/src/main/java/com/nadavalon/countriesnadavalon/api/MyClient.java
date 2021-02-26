package com.nadavalon.countriesnadavalon.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyClient {
    private static MyClient myClient;
    private Retrofit retrofit;

    private MyClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized MyClient getInstance() {
        if (myClient == null) {
            myClient = new MyClient();
        }
        return myClient;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }

}
