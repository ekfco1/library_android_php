package com.example.libraryproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCom {
     final static String API_URL = "http://ec2-52-55-224-222.compute-1.amazonaws.com/";

    Gson gson = new GsonBuilder()
                .setLenient()
                .create();


    private OkHttpClient client;
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(interceptor) // This is used to add ApplicationInterceptor.
            .addNetworkInterceptor(interceptor) //This is used to add NetworkInterceptor.
            .build();


    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-52-55-224-222.compute-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build();


}