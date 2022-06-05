package com.example.bookmall.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.bookmall.models.User;

public class UserMapper extends MyDataBaseHelper{
    private static final String TABLE_NAME = "user";
    private static final String SQL_Create_User =
            "CREATE TABLE IF NOT EXISTS user(_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT);";
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
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", name);
        contentValues.put("password", password);
        db.insert(TABLE_NAME,null, contentValues);
    }

    public User isUserExist(SQLiteDatabase db, String username, String pwd){

        Cursor cursor = db.query("user",
                null,
                "username=? AND password=?",
                new String[]{username, pwd},
                null,
                null,
                null,
                String.valueOf(1));
        cursor.moveToFirst();
        User user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        cursor.close();
        return user;
    }
}
