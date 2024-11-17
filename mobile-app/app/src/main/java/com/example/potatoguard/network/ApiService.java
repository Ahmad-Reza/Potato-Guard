package com.example.potatoguard.network;

import com.example.potatoguard.model.PlantHealthResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @Multipart
    @POST("/predict")
    Call<PlantHealthResponse> getPlantHealthInfo(@Part MultipartBody.Part file);
}
