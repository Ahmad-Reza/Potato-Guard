package com.example.potatoguard.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://us-central1-hazel-sky-441319-d2.cloudfunctions.net";

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
