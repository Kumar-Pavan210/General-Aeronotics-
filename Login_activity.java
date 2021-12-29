package com.example.generalaeronotics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_activity extends AppCompatActivity {

    ImageView feedback_logo;
    TextView heading,desc;
    TextInputLayout email,password;
    Button login,sign_up;
    private ProgressDialog dialog;
    Pattern pattern;
    Matcher matcher;
    Matcher matcher1;
    Pattern pattern1;
    String regex_pattern_password="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    String email_regex="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    String email_text;
    String password_text;
    Dbhelper dbhelper=new Dbhelper(this);

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login_activity);

        Window window=this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        //progress dialog intialization
        dialog=new ProgressDialog(this);
        dialog.setTitle("Processing");
        dialog.setMessage("Loading....");
        dialog.setCancelable(false);
        dialog.setIndeterminate(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        //hooks
        feedback_logo=findViewById(R.id.login_image);
        heading=findViewById(R.id.login_welcome_note);
        desc=findViewById(R.id.desc);
        email=findViewById(R.id.usn1);
        password=findViewById(R.id.login_password1);
        login=findViewById(R.id.login_btn);
        sign_up=findViewById(R.id.singup_btn);
        email.setError(null);
        email.setError(null);

        //animation
        Pair[] pairs=new Pair[6];
        pairs[0]=new Pair<View,String>(feedback_logo,"splash_image");
        // pairs[1]=new Pair<View,String>(heading,"splash_text");
        pairs[1]=new Pair<View,String>(desc,"desc");
        pairs[2]=new Pair<View,String>(email,"edittext2");
        pairs[3]=new Pair<View,String>(password,"edittext2");
        pairs[4]=new Pair<View,String>(login,"btn2");
        pairs[5]=new Pair<View,String>(sign_up,"btn3");
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Signup.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Login_activity.this,pairs);
                    startActivity(i,options.toBundle());
                }
            }
        });





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_text=email.getEditText().getText().toString().trim();
                password_text=password.getEditText().getText().toString().trim();
                if(validate_email()&&validate_password()){
                    email.setError(null);
                    password.setError(null);
                    Boolean result=dbhelper.check_user_existance(email_text,password_text);
                    if(result){
                        Toast.makeText(Login_activity.this, "Logging In", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),Dashboard2.class);
                    intent.putExtra("email",email_text);
                    startActivity(intent);
                    }else{
                        email.setError("No such user Exists !");
                    }

                }
            }

            private boolean validate_password() {
                //regex pattern for atleast eight characters ,atleast one letter,one number and one special charcter
                regex_pattern_password="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
                pattern=Pattern.compile(regex_pattern_password);;
                matcher=pattern.matcher(password_text);;
                if(password_text.isEmpty()){
                    password.setError("Password field cannot be empty.");
                    return false;
                }
                else if(!matcher.matches()){
                    password.setError("Password should contain at least  1 letter,1 number & 1 special character.");
                    return false;
                }else{
                    password.setError(null);
                    return true;
                }
            }
            private boolean validate_email(){
                //validate email
                email_regex="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
                pattern1=Pattern.compile(email_regex);
                matcher1=pattern1.matcher(email_text);
                if(email_text.isEmpty()){
                    email.setError("Email field cannot be empty.");
                    return false;
                }else if(!matcher1.matches()){
                    email.setError("Invalid email.");
                    return false;
                }else{
                    email.setError(null);
                    return true;
                }
            }
        });


    }


}