package com.example.bottomnavigation.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.bottomnavigation.MainActivity;
import com.example.bottomnavigation.R;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private static final String ADMIN_USERNAME = "admin"; // Hardcoded username
    private static final String ADMIN_PASSWORD = "password"; // Hardcoded password

    private SharedPreferences sharedPreferences;
    private EditText usernameEditText, passwordEditText;
    private ImageView passwordToggle;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );

        sharedPreferences = getSharedPreferences("admin_prefs", MODE_PRIVATE);

        boolean isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);
        if (isLoggedIn) {
            navigateToMainActivity();
        }

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        passwordToggle = findViewById(R.id.passwordToggle);
        Button loginButton = findViewById(R.id.buttonLogin);

        passwordToggle.setOnClickListener(v -> {
            if (isPasswordVisible) {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                passwordToggle.setImageResource(R.drawable.ic_visibility_off);
            } else {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                passwordToggle.setImageResource(R.drawable.ic_visibility);
            }
            isPasswordVisible = !isPasswordVisible;
            passwordEditText.setSelection(passwordEditText.getText().length());
        });

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                // Save login status and password in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("is_logged_in", true);
                editor.putString("saved_password", password);
                editor.apply();

                Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                navigateToMainActivity();
            } else {
                Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
