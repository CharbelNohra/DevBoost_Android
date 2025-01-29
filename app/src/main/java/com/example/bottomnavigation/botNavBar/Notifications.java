package com.example.bottomnavigation.botNavBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.bottomnavigation.R;

public class Notifications extends Fragment {

    private EditText etTitle, etMessage;
    private Button btnSubmit;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        etTitle = view.findViewById(R.id.etNotificationTitle);
        etMessage = view.findViewById(R.id.etNotificationMessage);
        btnSubmit = view.findViewById(R.id.btnSubmitNotification);

        btnSubmit.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String message = etMessage.getText().toString().trim();

            if (title.isEmpty() || message.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                saveNotification(title, message);
            }
        });

        return view;
    }

    private void saveNotification(String title, String message) {
        Toast.makeText(getActivity(), "Notification Sent: " + title, Toast.LENGTH_SHORT).show();
    }
}
