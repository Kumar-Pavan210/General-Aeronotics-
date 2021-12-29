package com.example.generalaeronotics;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dashboard extends AppCompatActivity {
        TextView wishes;
        TextView date;
        ImageView back;
        RecyclerView recyclerView;
        LinearLayoutManager layoutManager;
        List<Model_class> userlist;
        Adapter adapter;
        String id="";
        String name="";
        List<Model_class> users;
        List<Image_Model> image_models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Drawable status_bar_gradient=this.getResources().getDrawable(R.drawable.status_bar_gradient);
        Window window=this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setBackgroundDrawable(status_bar_gradient);
        }



        users=new ArrayList<>();
        image_models=new ArrayList<>();
        wishes=findViewById(R.id.wishes);
        date=findViewById(R.id.date);
        back=findViewById(R.id.users_data_back);

        recyclerView=findViewById(R.id.Recyclerview);
        Intialize_Retrofit();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dashboard.super.onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void Intialize_Retrofit() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Model_api myapi=retrofit.create(Model_api.class);

        Call<List<Model_class>> call=myapi.call();

        call.enqueue(new Callback<List<Model_class>>() {
            @Override
            public void onResponse(Call<List<Model_class>> call, Response<List<Model_class>> response) {
                if(response.code()!=200){
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG);
                    return;

                }

                List<Model_class> data=response.body();
                for(Model_class model: data){

                    users.add(model);

                }

            }

            @Override
            public void onFailure(Call<List<Model_class>> call, Throwable t) {

            }
        });

        Retrofit retrofit1=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Image_Api api=retrofit1.create(Image_Api.class);
        Call<List<Image_Model>> call1=api.Get_Url();
        call1.enqueue(new Callback<List<Image_Model>>() {
            @Override
            public void onResponse(Call<List<Image_Model>> call, Response<List<Image_Model>> response) {
                if(response.code()!=200){
                    Toast.makeText(getApplicationContext(),"Error on accessing JSON Images",Toast.LENGTH_LONG);
                    return;
                }
                List<Image_Model> data2=response.body();
                int count=0;
                for(Image_Model im:data2){
                    if(count<10){
                    image_models.add(im);}
                    count++;
                }
                intialize_userlist(users,image_models);
            }

            @Override
            public void onFailure(Call<List<Image_Model>> call, Throwable t) {

            }
        });
    }

    /*private String set_wishes() {
    Calendar cal=Calendar.getInstance();
    int hour_of_day=cal.get(Calendar.HOUR_OF_DAY);
    if(hour_of_day>=0 && hour_of_day<12){
        return "Good morning";
    }else if(hour_of_day>=12&& hour_of_day<=16){
        return "Good Afternoon";
    }else if(hour_of_day>16 && hour_of_day<21){
        return "Good Evening";
    }else{
        return "Good Night";
    }
    }*/

    /*private String  set_date(){
        Calendar calendar=Calendar.getInstance();
        String month=calendar.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.getDefault());
        int date=calendar.get(Calendar.DAY_OF_MONTH);
        int year=calendar.get(Calendar.YEAR);
        String full_date=String.valueOf(date)+" "+month+" "+String.valueOf(year);
        return full_date;
    }*/

    private void intialize_userlist(List<Model_class> users,List<Image_Model> image_models) {
        adapter=new Adapter(users,image_models,this);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    }
