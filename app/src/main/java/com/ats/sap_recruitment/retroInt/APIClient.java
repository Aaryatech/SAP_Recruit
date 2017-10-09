package com.ats.sap_recruitment.retroInt;


import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {


    private static Retrofit retrofit;

    public static Retrofit getClient() {
        Gson gson = new GsonBuilder().setLenient().create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();


        retrofit = new Retrofit.Builder().baseUrl(Constants.LOCAL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient).build();


        return retrofit;
    }
}
