package com.example.potatoguard.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.potatoguard.R;
import com.example.potatoguard.utils.ImageUtility;
import com.example.potatoguard.viewmodel.MainViewModel;
import com.squareup.picasso.Picasso;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private TextView tvDescription, tvLabel, tvConfidence;
    private LinearLayout layoutPrediction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView plantImage = findViewById(R.id.imageview_plant);
        ImageView clearBtn = findViewById(R.id.button_clear);
        clearBtn.setOnClickListener(v -> {
            plantImage.setVisibility(View.GONE);
            layoutPrediction.setVisibility(View.GONE);

            tvDescription.setVisibility(View.VISIBLE);
        });

        tvDescription = findViewById(R.id.tv_description);

        tvLabel = findViewById(R.id.tv_label);
        tvConfidence = findViewById(R.id.tv_confidence);
        layoutPrediction = findViewById(R.id.layout_prediction);

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

    private void showProgressDialog(boolean isShow) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);

            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("We are getting plant disease details.");
            progressDialog.setCanceledOnTouchOutside(false);
        }

        if (isShow) progressDialog.show();
        else progressDialog.dismiss();
    }

    private void updatePlantImage(ImageView view, Uri plantUri) {
        view.setVisibility(View.VISIBLE);
        Picasso.get().load(plantUri).into(view);

        getPlantHealthDetails(this, getFileFromUri(plantUri));
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

    private void getPlantHealthDetails(Context context, File file) {
        showProgressDialog(true);
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Observe the weather data from the ViewModel
        viewModel.getPlantHealthDetails(context, file).observe(this, weatherResponse -> {
            if (weatherResponse != null) {
                String labelMessage = "Label - " + weatherResponse.getDiseaseLabel();
                tvLabel.setText(labelMessage);

                String confidenceMsg = "Confidence - " + weatherResponse.getConfidence() + "%";
                tvConfidence.setText(confidenceMsg);

                tvDescription.setVisibility(View.GONE);
                layoutPrediction.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }

            showProgressDialog(false);
        });
    }
}