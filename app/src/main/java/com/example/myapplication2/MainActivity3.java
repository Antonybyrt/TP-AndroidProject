package com.example.myapplication2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity3 extends AppCompatActivity {

    private ActivityResultLauncher<Intent> mActivityResultLauncher;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.page3);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.page1) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(0,0);
                return true;
            } else if (item.getItemId() == R.id.page2) {
                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                overridePendingTransition(0,0);
                return true;
            } else if (item.getItemId() == R.id.page3) {
                startActivity(new Intent(getApplicationContext(), MainActivity3.class));
                overridePendingTransition(0,0);
                return true;
            }
            return false;
        });

        Button buttonCaptureImage = findViewById(R.id.button_capture_image);
        imageView = findViewById(R.id.imageView);

        mActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                Uri imageUri = data.getData();
                                if (imageUri != null) {
                                    imageView.setImageURI(imageUri);
                                }
                            }
                        }
                    }
                });

        buttonCaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    mActivityResultLauncher.launch(takePictureIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity3.this, "Aucune application disponible pour prendre une photo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}