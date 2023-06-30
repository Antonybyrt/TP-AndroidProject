package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.page1);

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

        Button buttonVibrate = findViewById(R.id.button_vibrate);
        buttonVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                if (vibrator != null) {
                    vibrator.vibrate(500);
                }
            }
        });
    }
}