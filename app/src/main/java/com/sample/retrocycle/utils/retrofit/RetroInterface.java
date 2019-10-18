package com.sample.retrocycle.utils.retrofit;

import com.sample.retrocycle.models.mUser.GUsers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroInterface {
    @GET("users")
    Call<List<GUsers>> getUsers();
}
