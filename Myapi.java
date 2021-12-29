package com.example.generalaeronotics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Myapi {
    @GET("users")
     Call<List<Retrofit_model>> getmodels();
}
