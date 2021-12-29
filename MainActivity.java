package com.example.generalaeronotics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Animation top_anim,bottom_anim;
    ImageView feedback;
    TextView heading,quote;
    private int time=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Window window=this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.splash));
        }
        setContentView(R.layout.activity_main);

        top_anim= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottom_anim= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        feedback=findViewById(R.id.imageView);
        heading=findViewById(R.id.textView);
        quote=findViewById(R.id.textView2);

        feedback.setAnimation(top_anim);
        heading.setAnimation(bottom_anim);
        quote.setAnimation(bottom_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), Login_activity.class);
                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View, String>(feedback, "splash_image");
                pairs[1] = new Pair<View, String>(heading, "splash_text");
                pairs[2] = new Pair<View, String>(quote, "desc");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                    startActivity(i, options.toBundle());
                    finish();
                }
            }
        },time);
    }


}