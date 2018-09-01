package com.watar.soft.watar.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by user on 07-09-2017.
 */

public class ApiUtils {

    public ApiUtils() {}

    public static final String BASE_URL = "http://159.65.152.70/api/";
    //public static final String BASE_URL = "http://192.168.1.101/water/";
    public static final String Accept = "application/json";
    public static final String Key = "base64:lGdz0IvAIiTz6tKRf0l5C7uL+Y0KioyqQUR35xx4+pg=";
    public static final String Content_Type = "application/json";

    public static APIService getAPIService()
    {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(1, TimeUnit.DAYS);
        okHttpClient.readTimeout(90, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(90, TimeUnit.SECONDS);

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);

/*
        return new retrofit2.Retrofit.Builder()                                   // Retrofit client.
                .baseUrl(BASE_URL)                                       // Base domain URL.
                .addConverterFactory(GsonConverterFactory.create())     // Added converter factory.
                .client(okHttpClient.build())
                .build()                                                // Build client.
                .create(APIService.class);
*/
    }


}