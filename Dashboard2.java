package com.example.generalaeronotics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class Dashboard2 extends AppCompatActivity {
    TextView hitext;
    TextView wishes;
    TextView date;
    Button user_btn;
    Button exit_btn;
    Dbhelper ddhelper =new Dbhelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);
        Window window=this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.white));

        }
        hitext=findViewById(R.id.HiText);
        wishes=findViewById(R.id.wishes);
        date=findViewById(R.id.date);
        user_btn=findViewById(R.id.user_btn);
        exit_btn=findViewById(R.id.Exit_btn);
        date.setText(set_date());
        wishes.setText(set_wishes());
        String email=getIntent().getExtras().getString("email");
        hitext.setText("Hi  "+ddhelper.get_firstname(email)+" !");

        user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Dashboard.class));
            }
        });

        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
    private String set_wishes() {
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
    }
    private String  set_date(){
        Calendar calendar=Calendar.getInstance();
        String month=calendar.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.getDefault());
        int date=calendar.get(Calendar.DAY_OF_MONTH);
        int year=calendar.get(Calendar.YEAR);
        String full_date=String.valueOf(date)+" "+month+" "+String.valueOf(year);
        return full_date;
    }
}