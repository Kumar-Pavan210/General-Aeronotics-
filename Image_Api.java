package com.example.generalaeronotics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Image_Api {
    @GET("photos")
    Call<List<Image_Model>> Get_Url();
}
