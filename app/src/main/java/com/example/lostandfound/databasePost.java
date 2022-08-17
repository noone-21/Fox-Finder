package com.example.lostandfound;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databasePost extends SQLiteOpenHelper  {
    public static final String DBNAME = "Post.db";

    public databasePost(Context context) {
        super(context,"Post.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table posts(itemName TEXT, pName TEXT ,description TEXT,image blob,pPhone TEXT,type TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists posts");
    }

    public Boolean insertDataPost(String name,String itemName,String description,byte[] img,String phone,String type){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("itemName",itemName);
        contentValues.put("pName",name);
        contentValues.put("description",description);
        contentValues.put("image",img);
        contentValues.put("pPhone",phone);
        contentValues.put("type",type);
        long result = MyDB.insert("posts",null,contentValues);
        if(result == -1) return false;
        else
            return true;
    }

    public Cursor getData(String type){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from posts where type=?" ,new String[]{type});
        return cursor;
    }

}
