package com.example.bookmall.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.bookmall.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper extends MyDataBaseHelper{
    private static final String TABLE_NAME = "category";
    private static final String SQL_CREATE_CATEGORY =
            "CREATE TABLE IF NOT EXISTS category(id INTEGER PRIMARY KEY, name TEXT);";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "+ TABLE_NAME;

    public CategoryMapper(@Nullable Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        super.onCreate(sqLiteDatabase);
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "艺术"));
        categories.add(new Category(2, "教育"));
        categories.add(new Category(3, "科技"));
        categories.add(new Category(4, "生活"));
        categories.add(new Category(5, "人文"));
        sqLiteDatabase.execSQL(SQL_CREATE_CATEGORY);
        this.insert(sqLiteDatabase, categories);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        super.onUpgrade(sqLiteDatabase, i, i1);
    }

    @SuppressLint("Recycle")
    public List<Category> selectAll(SQLiteDatabase db){
        List<Category> categories = new ArrayList<>();

        Cursor cursor = db.query(true, TABLE_NAME, null, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()){
            while (cursor.moveToNext()){
                Category category = new Category(cursor.getInt(0), cursor.getString(1));
                categories.add(category);
            }
            cursor.close();
        }

        return categories;
    }

    private void insert(SQLiteDatabase sqLiteDatabase, List<Category> categories){
        for (Category c : categories) {
            ContentValues cv = new ContentValues();
            cv.put("id", c.getId());
            cv.put("name", c.getName());
            sqLiteDatabase.insert(TABLE_NAME, "", cv);
        }
    }
}
