package com.example.generalaeronotics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    ImageView sign_up_logo;
    TextView sign_up_heading,sign_up_desc;
    TextInputLayout Phone;
    Button signup,goback;
    ProgressDialog dialog;
    Pattern pattern;
    Matcher matcher;
    String phone_regex="((\\+*)((0[ -]*)*|((91 )*))((\\d{12})+|(\\d{10})+))|\\d{5}([- ]*)\\d{6}";
    String phone_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Window window=this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }
        //hooks
        sign_up_logo=findViewById(R.id.sign_up_logo);
        sign_up_heading=findViewById(R.id.sig_up_heading);
        sign_up_desc=findViewById(R.id.sign_up_desc);
        Phone=findViewById(R.id.phone_number);
        signup=findViewById(R.id.sign_up_btn);
        Phone.setError(null);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone_string=Phone.getEditText().getText().toString().trim();
                pattern=Pattern.compile(phone_regex);
                matcher=pattern.matcher(phone_string);
                if(Validate_phone()){
                    Phone.setError(null);
                    Intent intent=new Intent(getApplicationContext(),Complete_your_profile.class);
                    intent.putExtra("phone",phone_string);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean Validate_phone() {
        if(phone_string.isEmpty()){
            Phone.setError("Phone field cannot be empty");
            return false;
        }else if(!matcher.matches()){
            Phone.setError("Invalid Phone number");
            return false;
        }else{
            Phone.setError(null);
            return true;
        }

    }
}