package com.example.bottomnavigation.botNavBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.userList.User;
import com.example.bottomnavigation.userList.UserAdapter;
import com.example.bottomnavigation.api.ApiService;
import com.example.bottomnavigation.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllUsers extends Fragment {

    private ArrayList<User> studentList = new ArrayList<>();
    private ArrayList<User> teacherList = new ArrayList<>();
    private UserAdapter studentAdapter, teacherAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_users, container, false);

        ListView studentListView = view.findViewById(R.id.studentListView);
        ListView teacherListView = view.findViewById(R.id.teacherListView);

        // Initialize adapters
        studentAdapter = new UserAdapter(getActivity(), studentList);
        teacherAdapter = new UserAdapter(getActivity(), teacherList);

        studentListView.setAdapter(studentAdapter);
        teacherListView.setAdapter(teacherAdapter);

        TabHost tabHost = view.findViewById(R.id.tabHost);
        tabHost.setup();

        setupTab(tabHost, "Students", R.id.studentListView);
        setupTab(tabHost, "Teachers", R.id.teacherListView);

        // Fetch user data from APIs
        fetchUserData();

        return view;
    }

    private void setupTab(TabHost tabHost, String tabName, int contentId) {
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabName);
        tabSpec.setContent(contentId);

        View tabIndicator = getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView tabTitle = tabIndicator.findViewById(R.id.tabTitle);
        tabTitle.setText(tabName);

        tabSpec.setIndicator(tabIndicator);
        tabHost.addTab(tabSpec);
    }

    private void fetchUserData() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // Fetch student data
        fetchData(apiService.getAllStudents(), studentList, studentAdapter);

        // Fetch teacher data
        fetchData(apiService.getAllTeachers(), teacherList, teacherAdapter);
    }

    private void fetchData(Call<List<User>> call, final ArrayList<User> list, final UserAdapter adapter) {
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    list.clear();
                    list.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                // Handle failure
                t.printStackTrace();
            }
        });
    }
}
