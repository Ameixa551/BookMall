package com.example.bookmall.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class UserMapper extends MyDataBaseHelper{
    public UserMapper(@Nullable Context context) {
        super(context);
    }

    public void addUser(SQLiteDatabase db, String name, String password){
        db.execSQL("INSERT INTO user(username,password)VALUES(?,?)",new Object[]{name,password});
    }

    public long getTotalUser(SQLiteDatabase db){
        String query = "SELECT COUNT(*) FROM user";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        return count;
    }

    public boolean isUserExist(SQLiteDatabase db, String username, String pwd){
        Cursor cursor = db.query("user",
                new String[]{"_id"},
                "username=? AND password=?",
                new String[]{username, pwd},
                null,
                null,
                null,
                String.valueOf(1));
        boolean isExist = cursor.moveToFirst();
        cursor.close();
        return isExist;
    }
}
