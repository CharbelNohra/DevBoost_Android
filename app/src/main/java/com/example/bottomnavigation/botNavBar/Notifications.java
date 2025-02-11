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
import com.example.bottomnavigation.api.ApiService;
import com.example.bottomnavigation.api.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                sendNotification(title, message);
            }

            // Clear the input fields after sending
            etTitle.setText("");
            etMessage.setText("");
        });

        return view;
    }

    // Method to send notification via API
    private void sendNotification(String title, String message) {
        // Create the NotificationRequest object
        NotificationRequest request = new NotificationRequest(title, message);

        // Create the API service using Retrofit
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // Make the API call
        Call<Void> call = apiService.sendNotification(request);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Successfully sent the notification
                    Toast.makeText(getActivity(), "Notification sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failure response
                    Toast.makeText(getActivity(), "Failed to send notification", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle network error or failure
                Toast.makeText(getActivity(), "Error occurred while sending notification", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
