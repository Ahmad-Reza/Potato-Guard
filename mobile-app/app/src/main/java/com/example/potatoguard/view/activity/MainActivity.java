package com.example.potatoguard.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.potatoguard.R;
import com.example.potatoguard.utils.ImageUtility;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView plantImage = findViewById(R.id.imageview_plant);
        ImageView clearBtn = findViewById(R.id.button_clear);
        clearBtn.setOnClickListener(v -> {
            plantImage.setVisibility(View.GONE);
        });

        TextView healthInfo = findViewById(R.id.tv_plant_health_info);
        healthInfo.setOnClickListener(v -> {

        });

        ActivityResultLauncher<Intent> cameraActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Bitmap imageBitmap = result.getData() != null ? (Bitmap) result.getData().getExtras().get("data") : null;

            ImageUtility imageUtility = new ImageUtility(getApplicationContext());
            Uri plantUri = imageBitmap != null ? imageUtility.getUriFromBitmap(imageBitmap) : null;

            updatePlantImage(plantImage, plantUri);
        });

        ImageView cameraBtn = findViewById(R.id.button_camera);
        cameraBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            cameraActivityLauncher.launch(intent);
        });

        ActivityResultLauncher<Intent> galleryActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Uri plantUri = result.getData() != null ? result.getData().getData() : null;

            updatePlantImage(plantImage, plantUri);
        });

        ImageView galleryBtn = findViewById(R.id.button_gallery);
        galleryBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/png");

            galleryActivityLauncher.launch(intent);
        });

        /*viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        // Observe the weather data from the ViewModel
        viewModel.getWeatherData("London", "your_api_key_here").observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {
                if (weatherResponse != null) {
                    temperatureTextView.setText("Temperature: " + weatherResponse.getMain().getTemp() + "°C");
                    descriptionTextView.setText("Description: " + weatherResponse.getWeather().get(0).getDescription());
                } else {
                    Toast.makeText(WeatherActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    private void updatePlantImage(ImageView view, Uri plantUri) {
        view.setVisibility(View.VISIBLE);
        Picasso.get().load(plantUri).into(view);
    }
}