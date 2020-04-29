package com.example.ens_tryouts_project.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMySUClientInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://mysu.sabanciuniv.edu/apps/test/";

    public static Retrofit getRetrofitInstance() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
