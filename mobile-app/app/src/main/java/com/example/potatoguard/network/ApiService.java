package com.example.potatoguard.network;

import com.example.potatoguard.model.PlantHealth;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/predict")
    Call<PlantHealth> getPlantHealth(@Query("file") String plantImage);
}
