package com.example.potatoguard.view.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.potatoguard.R;
import com.example.potatoguard.model.PlantHealthResponse;
import com.example.potatoguard.utils.ImageUtility;
import com.example.potatoguard.viewmodel.MainViewModel;
import com.squareup.picasso.Picasso;

import java.io.File;

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
    }

    private void updatePlantImage(ImageView view, Uri plantUri) {
        view.setVisibility(View.VISIBLE);
        Picasso.get().load(plantUri).into(view);

        getPlantHealthDetails(getFileFromUri(plantUri));
    }

    public File getFileFromUri(Uri uri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            return new File(filePath);
        }
        return new File("");
    }

    private void getPlantHealthDetails(File imageFile) {
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Observe the weather data from the ViewModel
        viewModel.getPlantHealthDetails(imageFile).observe(this, new Observer<PlantHealthResponse>() {
            @Override
            public void onChanged(PlantHealthResponse weatherResponse) {
                if (weatherResponse != null) {

                } else {
                    Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}