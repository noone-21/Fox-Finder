package com.example.lostandfound;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class database extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public database(Context context) {
        super(context,"Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(name TEXT ,email TEXT primary key,password TEXT,phone TEXT)");

         }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");

    }

    public Boolean insertData(String name,String email,String password,String phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("name",name);
        contentValues.put("password",password);
        contentValues.put("phone",phone);
        long result = MyDB.insert("users",null,contentValues);
        if(result == -1) return false;
        else
            return true;
    }

    public Boolean updateData(String name,String email,String password,String phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(!name.isEmpty())
            contentValues.put("name",name);
        if(!password.isEmpty())
            contentValues.put("password",password);
        if(!phone.isEmpty())
            contentValues.put("phone",phone);
        Cursor cursor = MyDB.rawQuery("select * from users where email = ?",new String[]{email});
        if(cursor.getCount()>0){
        long result = MyDB.update(   "users",contentValues,"email=?",new String[]{email});
        if(result == -1) return false;
        else
            return true;}
        else
            return false;
    }
    public Boolean checkEmail(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?",new String[] {email});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean loginCheck(String email,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password = ?",new String[]{email,password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Cursor getData(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email=?" ,new String[]{email});
        return cursor;
    }
}
