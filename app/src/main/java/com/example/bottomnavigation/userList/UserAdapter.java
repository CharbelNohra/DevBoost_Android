package com.example.bottomnavigation.userList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.api.ApiService;
import com.example.bottomnavigation.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<User> userList;

    // Constructor to initialize context and user list
    public UserAdapter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // Inflate the layout for the list item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_item, parent, false);

            // Initialize ViewHolder to avoid redundant findViewById calls
            holder = new ViewHolder();
            holder.avatar = convertView.findViewById(R.id.avatar);
            holder.fullName = convertView.findViewById(R.id.username);
            holder.email = convertView.findViewById(R.id.email);
            holder.delete = convertView.findViewById(R.id.delete);

            // Set the holder as the tag for the convertView
            convertView.setTag(holder);
        } else {
            // Retrieve the existing holder
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the current user from the list
        User user = userList.get(position);

        // Set the avatar, full name, and email
        holder.avatar.setImageResource(R.drawable.ic_user);
        holder.fullName.setText(user.getFull_name());
        holder.email.setText(user.getEmail());

        // Hide the delete icon for teachers
        if (user.getRole_id() == 2) { // If role_id is 2 (teacher)
            holder.delete.setVisibility(View.GONE); // Hide delete button for teachers
        } else { // If role_id is not 2 (student)
            holder.delete.setVisibility(View.VISIBLE); // Show delete button for students
            holder.delete.setOnClickListener(v -> {
                // Call the delete API for students
                deleteUser(user.getUser_id(), position);
            });
        }

        return convertView;
    }

    // ViewHolder class to store the views for better performance
    static class ViewHolder {
        ImageView avatar;
        TextView fullName;
        TextView email;
        ImageView delete;
    }

    // Method to delete a user via API call
    private void deleteUser(int userId, final int position) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Void> call = apiService.deleteUser(userId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Remove the user from the list and update the UI
                    userList.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Student deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failure to delete the user
                    Toast.makeText(context, "Failed to delete student", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle network error or failure
                Toast.makeText(context, "Error occurred while deleting the student", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
