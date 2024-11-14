package com.example.potatoguard.viewmodel;

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

    public LiveData<PlantHealthResponse> getPlantHealthDetails(File imageFile) {
        if (plantData == null) {
            plantData = repository.getPlantHealthInfo(imageFile);
        }
        return plantData;
    }
}
