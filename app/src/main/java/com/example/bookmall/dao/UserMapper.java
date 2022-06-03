package com.example.bookmall.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class UserMapper extends MyDataBaseHelper{
    private static final String SQL_Create_User =
            "CREATE TABLE user(_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT);";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS user";

    public UserMapper(@Nullable Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        super.onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL(SQL_Create_User);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        super.onUpgrade(sqLiteDatabase, i, i1);
    }

    public void addUser(SQLiteDatabase db, String name, String password){
        db.execSQL("INSERT INTO user(username,password)VALUES(?,?)",new Object[]{name,password});
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
