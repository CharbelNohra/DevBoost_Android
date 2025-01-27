package com.example.bottomnavigation.botNavBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.bottomnavigation.Profile.Profile;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.login.Login;

import java.util.Objects;

public class Settings extends Fragment {

    private static final String PREFS_NAME = "admin_prefs";
    private static final String SAVED_PASSWORD_KEY = "saved_password";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Button btnProfile = view.findViewById(R.id.btnProfile);
        Button btnLogout = view.findViewById(R.id.btnLogout);

        btnProfile.setOnClickListener(v -> showPasswordDialog());
        btnLogout.setOnClickListener(v -> logout());

        return view;
    }

    private void showPasswordDialog() {
        String savedPassword = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getString(SAVED_PASSWORD_KEY, "");

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_password, null);

        final EditText input = dialogView.findViewById(R.id.editPassword);
        Button btnConfirmPassword = dialogView.findViewById(R.id.btnConfirmPassword);

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(requireActivity());
        builder.setView(dialogView);
        builder.setCancelable(true);

        androidx.appcompat.app.AlertDialog dialog = builder.create();
        btnConfirmPassword.setOnClickListener(view -> {
            String enteredPassword = input.getText().toString().trim();
            if (enteredPassword.equals(savedPassword)) {
                Intent intent = new Intent(getActivity(), Profile.class);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "Incorrect password", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });

        dialog.show();
    }

    private void logout() {
        requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();

        Toast.makeText(getActivity(), "Logging out...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();
    }
}
