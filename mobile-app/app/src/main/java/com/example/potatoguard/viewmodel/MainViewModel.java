package com.example.potatoguard.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.potatoguard.model.PlantHealthResponse;
import com.example.potatoguard.repository.PlantHealthRepository;

import java.io.File;

public class MainViewModel extends ViewModel {
    private PlantHealthRepository repository;
    private LiveData<PlantHealthResponse> weatherData;

    public void WeatherViewModel() {
        repository = new PlantHealthRepository();
    }

    public LiveData<PlantHealthResponse> getPlantHealthDetails(File imageFile) {
        if (weatherData == null) {
            weatherData = repository.getPlantHealthInfo(imageFile);
        }
        return weatherData;
    }
}
