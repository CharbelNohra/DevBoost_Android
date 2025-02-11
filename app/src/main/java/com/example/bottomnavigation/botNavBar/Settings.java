package com.example.bottomnavigation.botNavBar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.bottomnavigation.Profile.Profile;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.databinding.FragmentSettingsBinding;
import com.example.bottomnavigation.login.Login;

public class Settings extends Fragment {

    private FragmentSettingsBinding binding;
    private static final String USER_PREFS = "user_prefs";
    private static final String PREFS_NAME = "admin_prefs";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnProfile.setOnClickListener(v -> openProfile());
        binding.btnLogout.setOnClickListener(v -> logout());

        return view;
    }

    private void openProfile() {
        startActivity(new Intent(getActivity(), Profile.class));
    }

    private void logout() {
        requireActivity().getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)
                .edit()
                .remove("is_logged_in")
                .apply();

        requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();

        Toast.makeText(getActivity(), "Logging out...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
