package com.example.generalaeronotics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Complete_your_profile extends AppCompatActivity {
        TextInputLayout firstname;
        TextInputLayout lastname;
        TextInputLayout phonenumber;
        TextInputLayout email;
        TextInputLayout password1;
        TextInputLayout password2;
        Button finish;
        Pattern pattern;
        Matcher matcher;
        String phone_number;
        String regex_pattern_password="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        String email_regex="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        String FIRSTNAME,LASTNAME,EMAIL,PASSWORD1,PASSWORD2;
    Dbhelper dbhelper=new Dbhelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_your_profile);

        //Hooks
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        phonenumber=findViewById(R.id.Phone_no);
        email=findViewById(R.id.Email);
        password1=findViewById(R.id.password1);
        password2=findViewById(R.id.password2);
        finish=findViewById(R.id.finish);

        //Status bar color
        Window window=this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.yellow));
        }

        phone_number=getIntent().getExtras().getString("phone");
        phonenumber.getEditText().setText(phone_number);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FIRSTNAME=firstname.getEditText().getText().toString();
                LASTNAME=lastname.getEditText().getText().toString();
                EMAIL=email.getEditText().getText().toString();
                PASSWORD1=password1.getEditText().getText().toString();
                PASSWORD2=password2.getEditText().getText().toString();
                //Toast.makeText(getApplicationContext(),,Toast.LENGTH_LONG).show();
                if(validate_firstname(FIRSTNAME)&&validate_lastname(LASTNAME)&&validate_email(EMAIL)&&validate_password(PASSWORD1,PASSWORD2)){
                    firstname.setError(null);
                    lastname.setError(null);
                    email.setError(null);
                    password1.setError(null);
                    password2.setError(null);

                    Boolean result=dbhelper.insert_data_into_users(EMAIL,PASSWORD2,FIRSTNAME,LASTNAME,phone_number);
                    if(result){
                    Toast.makeText(getApplicationContext(),"Signed up successfully",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),Login_activity.class));}
                    else{
                        Toast.makeText(getApplicationContext(),"Something went wrong Try again !.",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private boolean validate_firstname(String firstname_string) {
        if(firstname_string.isEmpty()){
            firstname.setError("Firstname field cannot be empty");
            return false;
        }else{
            firstname.setError(null);
            return true;
        }
    }

    private boolean validate_lastname(String lastname_string){
        if(lastname_string.isEmpty()){
            lastname.setError("Lastname field field cannot be empty");
            return false;
        }else{
            lastname.setError(null);
            return true;
        }
    }
    private boolean validate_email(String email_string){
        pattern=Pattern.compile(email_regex);
        matcher=pattern.matcher(email_string);
        if(email_string.isEmpty()){
            email.setError("Email field cannot be empty");
            return false;
        }else if(!matcher.matches()){
            email.setError("Invalid email");
            return false;
        }else{
            email.setError(null);
            return true;
        }

    }

    private boolean validate_password(String password1_string,String password2_string){
        pattern=Pattern.compile(regex_pattern_password);
        matcher=pattern.matcher(password1_string);
        if(password1_string.isEmpty()){
            password1.setError("Password field cannot be empty");
            return false;
        }else if(password2_string.isEmpty()){
            password2.setError("Password field cannot be empty");
            return false;
        }else if(!matcher.matches()){
            password1.setError("Password should contain at least  1 letter,1 number & 1 special character.");
            return false;
        }else if(!password1_string.equals(password2_string)){
            password2.setError("Password's do not match.");
            return false;
        }else{
            password2.setError(null);
            password1.setError(null);
            return true;
        }
    }
}