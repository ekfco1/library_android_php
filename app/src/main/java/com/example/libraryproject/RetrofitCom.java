package com.example.libraryproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCom {
//     final static String API_URL = "http:///";
    private static final String FCM_SEND_ENDPOINT = "" ;

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
             .baseUrl("http://52.4.115.234/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build();


}