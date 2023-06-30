package com.example.myapplication2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.page2);

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

        final EditText editTextPhone = findViewById(R.id.editText_phone);
        final EditText editTextMessage = findViewById(R.id.editText_message);

        Button buttonMessage = findViewById(R.id.button_message);
        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString();
                String message = editTextMessage.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
                intent.putExtra("sms_body", message);
                startActivity(intent);
            }
        });
    }
}