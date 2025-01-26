package com.example.bottomnavigation.Profile;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.bottomnavigation.R;

public class Profile extends AppCompatActivity {

    private EditText newPasswordEditText, confirmNewPasswordEditText;
    private Button savePasswordButton;
    private ImageView backButton, passwordToggle, confirmPasswordToggle;
    private SharedPreferences sharedPreferences;

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

        // Find Views
        newPasswordEditText = findViewById(R.id.newPass);
        confirmNewPasswordEditText = findViewById(R.id.confirmNewPass);
        savePasswordButton = findViewById(R.id.savePass);
        backButton = findViewById(R.id.backButton);
        passwordToggle = findViewById(R.id.password_toggle);
        confirmPasswordToggle = findViewById(R.id.passwordToggle);

        savePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBack();
            }
        });

        passwordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(newPasswordEditText, passwordToggle);
            }
        });

        confirmPasswordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(confirmNewPasswordEditText, confirmPasswordToggle);
            }
        });
    }

    private void changePassword() {
        String newPassword = newPasswordEditText.getText().toString().trim();
        String confirmPassword = confirmNewPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (newPassword.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password", newPassword);
        editor.apply();

        Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show();

        newPasswordEditText.setText("");
        confirmNewPasswordEditText.setText("");
    }

    private void navigateBack() {
        finish();
    }

    private void togglePasswordVisibility(EditText passwordEditText, ImageView toggleIcon) {
        if (passwordEditText.getInputType() == 129) { // Password hidden
            passwordEditText.setInputType(1); // Make password visible
            toggleIcon.setImageResource(R.drawable.ic_visibility); // Change icon to "visible"
        } else {
            passwordEditText.setInputType(129); // Hide password
            toggleIcon.setImageResource(R.drawable.ic_visibility_off); // Change icon to "hidden"
        }
        passwordEditText.setSelection(passwordEditText.getText().length()); // Keep cursor at the end
    }
}
