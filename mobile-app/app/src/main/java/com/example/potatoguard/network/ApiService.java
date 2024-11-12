package com.example.potatoguard.network;

import com.example.potatoguard.model.PlantHealthResponse;

import java.io.File;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/predict")
    Call<PlantHealthResponse> getPlantHealthInfo(@Query("file") File plantImage);
}
