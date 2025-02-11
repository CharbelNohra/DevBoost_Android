package com.example.bottomnavigation.Profile;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.api.RetrofitClient;
import com.example.bottomnavigation.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Profile extends AppCompatActivity {

    private EditText newPasswordEditText, confirmNewPasswordEditText;
    private Button savePasswordButton;
    private ImageView backButton, passwordToggle, confirmPasswordToggle;
    private SharedPreferences sharedPreferences;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("admin_prefs", MODE_PRIVATE);

        // Initialize Retrofit API service
        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(ApiService.class);

        // Find Views
        newPasswordEditText = findViewById(R.id.newPass);
        confirmNewPasswordEditText = findViewById(R.id.confirmNewPass);
        savePasswordButton = findViewById(R.id.savePass);
        backButton = findViewById(R.id.backButton);
        passwordToggle = findViewById(R.id.password_toggle);
        confirmPasswordToggle = findViewById(R.id.passwordToggle);

        savePasswordButton.setOnClickListener(v -> changePassword());
        backButton.setOnClickListener(v -> finish());

        passwordToggle.setOnClickListener(v -> togglePasswordVisibility(newPasswordEditText, passwordToggle));
        confirmPasswordToggle.setOnClickListener(v -> togglePasswordVisibility(confirmNewPasswordEditText, confirmPasswordToggle));
    }

    private void changePassword() {
        String newPassword = newPasswordEditText.getText().toString().trim();
        String confirmPassword = confirmNewPasswordEditText.getText().toString().trim();

        // Validate input fields
        if (TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
            Log.e("ProfileActivity", "Empty fields detected");
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Log.e("ProfileActivity", "Passwords do not match");
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (newPassword.length() < 8) {
            Log.e("ProfileActivity", "Password is too short: " + newPassword.length());
            Toast.makeText(this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create the request object with the new password
        UpdatePasswordRequest request = new UpdatePasswordRequest(newPassword);

        // Call the API to update the password
        apiService.updatePassword(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("ProfileActivity", "Password updated successfully");
                    Toast.makeText(Profile.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                    newPasswordEditText.setText("");
                    confirmNewPasswordEditText.setText("");
                } else {
                    Log.e("ProfileActivity", "Failed to update password, response code: " + response.code());
                    Toast.makeText(Profile.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("ProfileActivity", "Error updating password: " + t.getMessage(), t);
                Toast.makeText(Profile.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void togglePasswordVisibility(EditText passwordEditText, ImageView toggleIcon) {
        if (passwordEditText.getInputType() == 129) { // Password hidden
            passwordEditText.setInputType(1); // Make password visible
            toggleIcon.setImageResource(R.drawable.ic_visibility);
            Log.d("ProfileActivity", "Password visibility toggled ON");
        } else {
            passwordEditText.setInputType(129); // Hide password
            toggleIcon.setImageResource(R.drawable.ic_visibility_off);
            Log.d("ProfileActivity", "Password visibility toggled OFF");
        }
        passwordEditText.setSelection(passwordEditText.getText().length()); // Keep cursor at the end
    }
}
