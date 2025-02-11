package com.example.bottomnavigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.bottomnavigation.botNavBar.AllUsers;
import com.example.bottomnavigation.botNavBar.Notifications;
import com.example.bottomnavigation.botNavBar.Settings;
import com.example.bottomnavigation.login.Login;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        // Check if user is logged in, otherwise redirect to Login
        if (!sharedPreferences.getBoolean("is_logged_in", false)) {
            navigateToLoginActivity();
            return;
        }

        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void setupUI() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_settings:
                    loadFragment(new Settings());
                    return true;
                case R.id.nav_all_users:
                    loadFragment(new AllUsers());
                    return true;
                case R.id.nav_notifications:
                    loadFragment(new Notifications());
                    return true;
                default:
                    return false;
            }
        });

        // Load default fragment
        bottomNavigationView.setSelectedItemId(R.id.nav_all_users);
        loadFragment(new AllUsers());
    }

    private void navigateToLoginActivity() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
