package com.example.kafshapp.model;

import android.content.Context;
import android.widget.Toast;

import com.example.kafshapp.view.MainActivity;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//base Api Retrofit
public class ApiClient {
    public  static Retrofit retrofit=null;

    public static Retrofit getRetrofit(String url) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
