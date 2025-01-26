package com.example.bottomnavigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.bottomnavigation.botNavBar.AllUsers;
import com.example.bottomnavigation.botNavBar.Requests;
import com.example.bottomnavigation.botNavBar.Settings;
import com.example.bottomnavigation.login.Login;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private int requestCount = 5;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("admin_prefs", MODE_PRIVATE);

        // Check if user is logged out and redirect to Login screen if needed
        boolean isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);
        if (!isLoggedIn) {
            navigateToLoginActivity();
        }

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
                    break;
                case R.id.nav_all_users:
                    loadFragment(new AllUsers());
                    break;
                case R.id.nav_notifications:
                    loadFragment(new Requests());
                    break;
            }
            return true;
        });

        // Set badge on the notification icon (requests icon)
        BadgeDrawable badge = bottomNavigationView.getOrCreateBadge(R.id.nav_notifications);
        if (requestCount > 0) {
            badge.setVisible(true);
            badge.setNumber(requestCount);
        } else {
            badge.setVisible(false);
        }

        if (savedInstanceState == null) {
            loadFragment(new AllUsers());
        }
    }

    private void navigateToLoginActivity() {
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
