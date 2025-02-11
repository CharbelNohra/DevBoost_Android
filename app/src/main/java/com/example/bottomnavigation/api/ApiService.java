package com.example.bottomnavigation.api;

import com.example.bottomnavigation.Profile.UpdatePasswordRequest;
import com.example.bottomnavigation.botNavBar.NotificationRequest;
import com.example.bottomnavigation.login.LoginRequest;
import com.example.bottomnavigation.login.LoginResponse;
import com.example.bottomnavigation.userList.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @POST("api/user/loginAdmin")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("api/user/students")
    Call<List<User>> getAllStudents();

    @GET("api/teacher/getByDomain")
    Call<List<User>> getAllTeachers();

    @DELETE("api/user/delete/{user_id}")
    Call<Void> deleteUser(@Path("user_id") int user_id);

    @POST("api/notification/create")
    Call<Void> sendNotification(@Body NotificationRequest request);

    @PUT("api/user/updatePassword")
    Call<Void> updatePassword(@Body UpdatePasswordRequest request);
}
