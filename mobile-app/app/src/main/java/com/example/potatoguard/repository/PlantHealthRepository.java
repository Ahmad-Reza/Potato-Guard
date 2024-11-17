package com.example.potatoguard.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.potatoguard.model.PlantHealthResponse;
import com.example.potatoguard.network.ApiService;
import com.example.potatoguard.network.RetrofitInstance;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlantHealthRepository {
    private final ApiService apiService;

    public PlantHealthRepository() {
        apiService = RetrofitInstance.getApiService();
    }

    public LiveData<PlantHealthResponse> getPlantHealthInfo(Context context, File file) {
        final MutableLiveData<PlantHealthResponse> data = new MutableLiveData<>();

        if (!file.exists()) {
            Toast.makeText(context, "File does not exist", Toast.LENGTH_SHORT).show();
            return null;
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file); // Adjust MIME type as needed
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        apiService.getPlantHealthInfo(multipartBody).enqueue(new Callback<PlantHealthResponse>() {
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
