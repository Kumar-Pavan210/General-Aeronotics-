package com.example.generalaeronotics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Model_api {
    @GET("users")
    Call<List<Model_class>> call();
}
