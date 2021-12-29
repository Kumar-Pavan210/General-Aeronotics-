package com.example.generalaeronotics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class dummy extends AppCompatActivity {
    TextView textView;
    String data_s="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //https://jsonplaceholder.typicode.com/photos/{Id}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        textView=findViewById(R.id.dummy_textview);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Image_Api api=retrofit.create(Image_Api.class);
        Call<List<Image_Model>> call=api.Get_Url();

        call.enqueue(new Callback<List<Image_Model>>() {
            @Override
            public void onResponse(Call<List<Image_Model>> call, Response<List<Image_Model>> response) {
                List<Image_Model> data=response.body();

                for(int i=0;i<10;i++){
                    data_s+=data.get(i).getThumbnailUrl();
                }
                textView.setText(data_s);

            }

            @Override
            public void onFailure(Call<List<Image_Model>> call, Throwable t) {

            }
        });

    }
}