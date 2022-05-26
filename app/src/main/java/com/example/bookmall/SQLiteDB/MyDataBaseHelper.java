package com.example.bookmall.SQLiteDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bookshoppingmall.db";

    private static final String SQL_Create_User =
            "CREATE TABLE user(_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT);";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + "user";
    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_Create_User);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public void addUser(SQLiteDatabase db,String name,String password){
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
        return cursor.moveToFirst();

    }
}
