package com.example.potatoguard.repository;

public class PlantHealthRepository {
   /* private final WeatherApiService apiService;

    public WeatherRepository() {
        apiService = RetrofitInstance.getApiService();
    }

    public LiveData<WeatherResponse> getWeather(String city, String apiKey) {
        final MutableLiveData<WeatherResponse> data = new MutableLiveData<>();

        apiService.getWeather(city, apiKey).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }*/
}
