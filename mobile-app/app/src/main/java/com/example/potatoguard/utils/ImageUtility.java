package com.example.potatoguard.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class ImageUtility {
    private final Context context;

    public ImageUtility(Context context) {
        this.context = context;
    }

    public byte[] getByteFromUri(Uri imageUri) {
        byte[] returnVal = null;

        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            Bitmap bitmap = getBitmapFromUri(imageUri);
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            }

            returnVal = stream.toByteArray();
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnVal;
    }

    public Bitmap getBitmapFromUri(Uri imageUri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public Uri getUriFromByte(byte[] imageByte) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        Bitmap imageBitmap = getBitmapFromByte(imageByte);
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), imageBitmap, "water_plant_" + Calendar.getInstance().getTime(), null);

        return path != null ? Uri.parse(path) : null;
    }

    private Bitmap getBitmapFromByte(byte[] imageByte) {
        Bitmap bitmap = null;

        if (imageByte != null) {
            bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
        }

        return bitmap;
    }

    public Uri getUriFromBitmap(Bitmap imageBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), imageBitmap, "water_plant_" + Calendar.getInstance().getTime(), null);
        return Uri.parse(path);
    }
}
