package com.example.generalaeronotics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {

    public static final String db="login.db";

    public Dbhelper(@Nullable Context context) {
        super(context,"login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("Create table users(email TEXT primary key,password TEXT,firstname TEXT,lastname TEXT,phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int i, int i1) {
        Mydb.execSQL("drop table if exists users");
    }

    public boolean insert_data_into_users(String email,String password,String Firstname,String Lastname,String Phone_number){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("email",email);
        cv.put("password",password);
        cv.put("firstname",Firstname);
        cv.put("lastname",Lastname);
        cv.put("phone",Phone_number);
        long result=db.insert("users",null,cv);
        if(result==-1){
            return false;
        }else
                return true;
    }

    public boolean check_user_existance(String email,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cr=db.rawQuery("select * from users where email=? and password=?",new String[] { email,password});
        if(cr.getCount()>0){
            return true;
        }else
            return false;

    }

    public String get_firstname(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cr=db.rawQuery("select firstname from users where email=?",new String[]{email});
        if(cr.moveToFirst()){
            return cr.getString(0);
        }else{
            return " ";
        }


    }
}
