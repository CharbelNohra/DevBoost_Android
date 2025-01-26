package com.example.bottomnavigation.userList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import com.example.bottomnavigation.R;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<User> userList;

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
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_item, parent, false);
        }

        // Set user data to views
        User user = userList.get(position);

        ImageView avatar = convertView.findViewById(R.id.avatar);
        TextView username = convertView.findViewById(R.id.username);
        TextView email = convertView.findViewById(R.id.email);
        ImageView delete = convertView.findViewById(R.id.delete);

        // Set data for each user
        avatar.setImageResource(R.drawable.ic_user); // Replace with appropriate avatar resource
        username.setText(user.getUsername());
        email.setText(user.getEmail());

        // Set delete button click listener
        delete.setOnClickListener(v -> {
            // Remove the user from the list
            userList.remove(position);

            // Notify the adapter about the change
            notifyDataSetChanged();

            // Show a toast message (optional)
            Toast.makeText(context, "User deleted: " + user.getUsername(), Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
