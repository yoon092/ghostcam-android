package com.example.ghostcam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Switch switch1, switch2, switch3;
    private Button selectVideoButton, uploadVideoButton, logoutButton;
    private Spinner appSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        initViews();
        setupSpinner();
        setupClickListeners();
    }

    private void initViews() {
        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        selectVideoButton = findViewById(R.id.select_video_button);
        uploadVideoButton = findViewById(R.id.upload_video_button);
        logoutButton = findViewById(R.id.logout_button);
        appSpinner = findViewById(R.id.app_spinner);
    }

    private void setupSpinner() {
        List<String> appList = new ArrayList<>();
        appList.add("Select App");
        appList.add("WhatsApp");
        appList.add("Zoom");
        appList.add("Skype");
        appList.add("Telegram");
        appList.add("Discord");
        appList.add("Google Meet");
        appList.add("Facebook Messenger");
        appList.add("Instagram");
        appList.add("TikTok");
        appList.add("Snapchat");
        appList.add("All Apps");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
            android.R.layout.simple_spinner_item, appList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appSpinner.setAdapter(adapter);
    }

    private void setupClickListeners() {
        selectVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Video selection feature - requires root access", Toast.LENGTH_LONG).show();
                // Here you would implement video selection functionality
                // This requires root access and Xposed framework to work properly
            }
        });

        uploadVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConfigurationValid()) {
                    Toast.makeText(MainActivity.this, "GhostCam activated! Ready to use with selected app.", Toast.LENGTH_LONG).show();
                    // Here you would implement the core GhostCam functionality
                    // This requires root access and Xposed framework
                } else {
                    Toast.makeText(MainActivity.this, "Please configure settings first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Switch listeners
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(MainActivity.this, "Feature 1: " + (isChecked ? "Enabled" : "Disabled"), Toast.LENGTH_SHORT).show();
        });

        switch2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(MainActivity.this, "Feature 2: " + (isChecked ? "Enabled" : "Disabled"), Toast.LENGTH_SHORT).show();
        });

        switch3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(MainActivity.this, "Feature 3: " + (isChecked ? "Enabled" : "Disabled"), Toast.LENGTH_SHORT).show();
        });
    }

    private boolean isConfigurationValid() {
        // Check if at least one switch is enabled and an app is selected
        boolean switchEnabled = switch1.isChecked() || switch2.isChecked() || switch3.isChecked();
        boolean appSelected = appSpinner.getSelectedItemPosition() > 0;
        return switchEnabled && appSelected;
    }

    @Override
    public void onBackPressed() {
        // Prevent going back to login without logging out
        Toast.makeText(this, "Use Exit button to logout", Toast.LENGTH_SHORT).show();
    }
}