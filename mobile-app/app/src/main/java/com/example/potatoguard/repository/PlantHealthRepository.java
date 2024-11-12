package com.example.potatoguard.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.potatoguard.model.PlantHealthResponse;
import com.example.potatoguard.network.ApiService;
import com.example.potatoguard.network.RetrofitInstance;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantHealthRepository {
    private final ApiService apiService;

    public PlantHealthRepository() {
        apiService = RetrofitInstance.getApiService();
    }

    public LiveData<PlantHealthResponse> getPlantHealthInfo(File imageFile) {
        final MutableLiveData<PlantHealthResponse> data = new MutableLiveData<>();

        apiService.getPlantHealthInfo(imageFile).enqueue(new Callback<PlantHealthResponse>() {
            @Override
            public void onResponse(@NonNull Call<PlantHealthResponse> call, @NonNull Response<PlantHealthResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlantHealthResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
