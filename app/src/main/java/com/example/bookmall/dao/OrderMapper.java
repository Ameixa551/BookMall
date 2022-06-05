package com.example.bookmall.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.bookmall.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper extends MyDataBaseHelper{
    private static final String TABLE_NAME = "orders";
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " +TABLE_NAME+
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "cart_time INTEGER," +
                    "pay_time INTEGER," +
                    "is_paid INTEGER," +
                    "sum_price REAL," +
                    "book_id INTEGER," +
                    "book_num INTEGER," +
                    "uid INTEGER," +
                    "CONSTRAINT fk_book_id FOREIGN KEY(book_id) REFERENCES book(id)," +
                    "CONSTRAINT fk_uid FOREIGN KEY(uid) REFERENCES user(_id)) ";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "+ TABLE_NAME;

    public OrderMapper(@Nullable Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        super.onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        super.onUpgrade(sqLiteDatabase, i, i1);
    }

    public void addOrder(SQLiteDatabase sqLiteDatabase, Order order){
        ContentValues values = new ContentValues();
        values.put("cart_time", order.getCartTime());
        values.put("pay_time", order.getPayTime());
        values.put("is_paid", order.getIsPaid());
        values.put("sum_price", order.getSumPrice());
        values.put("book_id", order.getBookId());
        values.put("book_num", order.getBookNum());
        values.put("uid", order.getUid());
        sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    public void updateBookNum(SQLiteDatabase sqLiteDatabase, Order order){
        ContentValues values = new ContentValues();
        values.put ("book_num", order.getBookNum());
        values.put("sum_price", order.getSumPrice());
        sqLiteDatabase.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(order.getId())});
    }

    public void updateOrderState(SQLiteDatabase sqLiteDatabase, Order order){
        ContentValues values = new ContentValues();
        values.put ("is_paid", order.getIsPaid()? 1:0);
        values.put ("pay_time", order.getPayTime());
        sqLiteDatabase.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(order.getId()), });
    }

    public void deleteOrder(SQLiteDatabase sqLiteDatabase, Order order){
        sqLiteDatabase.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(order.getId())});
    }

    public Order selectByBookId(SQLiteDatabase sqLiteDatabase, int uid, int bookId){
        Order order = null;
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,null,"book_id=? AND uid=? AND is_paid=?",new String[]{String.valueOf(bookId), String.valueOf(uid), "0"},null,
                null,null,null);
        if(cursor.moveToFirst()){
            order = new Order(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3) != 0,
                    cursor.getFloat(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7));
        }
        cursor.close();
        return order;
    }

    public List<Order> selectCartOrder(SQLiteDatabase sqLiteDatabase, int uid){
        List<Order> orders = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,null,"is_paid=? AND uid=?",new String[]{"0", String.valueOf(uid)},null,
                null,null,null);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                Order order = new Order(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3) != 0,
                        cursor.getFloat(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7));
                orders.add(order);
            }
            cursor.close();
        }
        return orders;
    }

    public List<Order> selectPaidOrder(SQLiteDatabase sqLiteDatabase, int uid){
        List<Order> orders = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,null,"is_paid=? AND uid=?",new String[]{"1", String.valueOf(uid)},null,
                null,"pay_time desc",null);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                Order order = new Order(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3) != 0,
                        cursor.getFloat(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7));
                orders.add(order);
            }
            cursor.close();
        }
        return orders;
    }
}
