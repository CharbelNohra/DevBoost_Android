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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllUsers extends Fragment {

    private ArrayList<User> studentList = new ArrayList<>();
    private ArrayList<User> teacherList = new ArrayList<>();
    private UserAdapter studentAdapter, teacherAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_users, container, false);

        ListView studentListView = view.findViewById(R.id.studentListView);
        ListView teacherListView = view.findViewById(R.id.teacherListView);

        loadDataFromJson();

        studentAdapter = new UserAdapter(getActivity(), studentList);
        teacherAdapter = new UserAdapter(getActivity(), teacherList);

        studentListView.setAdapter(studentAdapter);
        teacherListView.setAdapter(teacherAdapter);

        TabHost tabHost = view.findViewById(R.id.tabHost);
        tabHost.setup();

        setupTab(tabHost, "Students", R.id.studentListView);
        setupTab(tabHost, "Teachers", R.id.teacherListView);

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

    private void loadDataFromJson() {
        String jsonString = "[\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"John Doe\",\n" +
                "    \"email\": \"john.doe@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Jane Smith\",\n" +
                "    \"email\": \"jane.smith@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Michael Brown\",\n" +
                "    \"email\": \"michael.brown@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Emily Johnson\",\n" +
                "    \"email\": \"emily.johnson@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"David Lee\",\n" +
                "    \"email\": \"david.lee@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Sophia Wang\",\n" +
                "    \"email\": \"sophia.wang@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"William Clark\",\n" +
                "    \"email\": \"william.clark@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Olivia Evans\",\n" +
                "    \"email\": \"olivia.evans@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Daniel Lewis\",\n" +
                "    \"email\": \"daniel.lewis@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Ava Harris\",\n" +
                "    \"email\": \"ava.harris@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Lucas Young\",\n" +
                "    \"email\": \"lucas.young@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Mia King\",\n" +
                "    \"email\": \"mia.king@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"James Scott\",\n" +
                "    \"email\": \"james.scott@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Charlotte Adams\",\n" +
                "    \"email\": \"charlotte.adams@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Benjamin Nelson\",\n" +
                "    \"email\": \"benjamin.nelson@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Amelia Carter\",\n" +
                "    \"email\": \"amelia.carter@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Ethan Perez\",\n" +
                "    \"email\": \"ethan.perez@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Ella Mitchell\",\n" +
                "    \"email\": \"ella.mitchell@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Jackson Roberts\",\n" +
                "    \"email\": \"jackson.roberts@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Lily Walker\",\n" +
                "    \"email\": \"lily.walker@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Mason Green\",\n" +
                "    \"email\": \"mason.green@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Chloe Hall\",\n" +
                "    \"email\": \"chloe.hall@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Alexander Allen\",\n" +
                "    \"email\": \"alexander.allen@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Grace Harris\",\n" +
                "    \"email\": \"grace.harris@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Logan Carter\",\n" +
                "    \"email\": \"logan.carter@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Zoe Walker\",\n" +
                "    \"email\": \"zoe.walker@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Oliver Lewis\",\n" +
                "    \"email\": \"oliver.lewis@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Harper Robinson\",\n" +
                "    \"email\": \"harper.robinson@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Sebastian Thomas\",\n" +
                "    \"email\": \"sebastian.thomas@example.com\",\n" +
                "    \"role\": \"teacher\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"avatar\": \"ic_user\",\n" +
                "    \"username\": \"Scarlett White\",\n" +
                "    \"email\": \"scarlett.white@example.com\",\n" +
                "    \"role\": \"student\"\n" +
                "  }\n" +
                "]";


        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                User user = new User(
                        jsonObject.getString("avatar"),
                        jsonObject.getString("username"),
                        jsonObject.getString("email"),
                        jsonObject.getString("role")
                );

                if (user.getRole().equals("student")) {
                    studentList.add(user);
                } else {
                    teacherList.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
