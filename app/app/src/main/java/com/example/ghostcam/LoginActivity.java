package com.example.ghostcam;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox saveLoginCheckBox;
    private Button loginButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        saveLoginCheckBox = findViewById(R.id.save_login_checkbox);
        loginButton = findViewById(R.id.login_button);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);

        // Load saved credentials if exist
        loadSavedCredentials();

        // Set login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }

    private void loadSavedCredentials() {
        boolean saveLogin = sharedPreferences.getBoolean("save_login", false);
        if (saveLogin) {
            String savedUsername = sharedPreferences.getString("username", "");
            String savedPassword = sharedPreferences.getString("password", "");
            usernameEditText.setText(savedUsername);
            passwordEditText.setText(savedPassword);
            saveLoginCheckBox.setChecked(true);
        }
    }

    private void performLogin() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simple authentication check
        if (isValidCredentials(username, password)) {
            // Save credentials if checkbox is checked
            if (saveLoginCheckBox.isChecked()) {
                saveCredentials(username, password);
            } else {
                clearSavedCredentials();
            }

            // Navigate to MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Simple validation - you can customize this
        return username.equals("admin") && password.equals("ghost123") ||
               username.equals("ghost") && password.equals("cam123") ||
               username.length() > 3 && password.length() > 3;
    }

    private void saveCredentials(String username, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putBoolean("save_login", true);
        editor.apply();
    }

    private void clearSavedCredentials() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username");
        editor.remove("password");
        editor.putBoolean("save_login", false);
        editor.apply();
    }
}