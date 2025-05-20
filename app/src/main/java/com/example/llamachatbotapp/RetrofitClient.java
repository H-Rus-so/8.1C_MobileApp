package com.example.llamachatbotapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class RetrofitClient {
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(180, TimeUnit.SECONDS) // 3 minutes
                    .connectTimeout(180, TimeUnit.SECONDS)
                    .writeTimeout(180, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    // TODO: Replace with your backend server's IP address and port
                    .baseUrl("http://YOUR_SERVER_IP:5000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
