package com.example.potatoguard.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.potatoguard.model.PlantHealthResponse;
import com.example.potatoguard.repository.PlantHealthRepository;

import java.io.File;

public class MainViewModel extends ViewModel {
    private final PlantHealthRepository repository;
    private LiveData<PlantHealthResponse> plantData;

    public MainViewModel() {
        repository = new PlantHealthRepository();
    }

    public LiveData<PlantHealthResponse> getPlantHealthDetails(Context context, File imageFile) {
        return repository.getPlantHealthInfo(context, imageFile);
    }
}
