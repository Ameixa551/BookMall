package com.example.bookmall.activity.ui.dashboard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookmall.dao.BookMapper;
import com.example.bookmall.dao.OrderMapper;
import com.example.bookmall.models.DisplayOrder;
import com.example.bookmall.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DashboardViewModel extends ViewModel implements Observable {

    private MutableLiveData<List<DisplayOrder>> orderList;
    private MutableLiveData<Float> totalPrice;
    private MutableLiveData<Integer> selectCount;
    private OrderMapper orderMapper;
    private BookMapper bookMapper;
    private SQLiteDatabase orderDB;
    private SQLiteDatabase bookDB;

    public DashboardViewModel() {
        orderList = new MutableLiveData<>();
        totalPrice = new MutableLiveData<>();
        selectCount = new MutableLiveData<>();
    }

    public void addContext(Context context){
        orderMapper = new OrderMapper(context);
        bookMapper = new BookMapper(context);
        orderDB = orderMapper.getWritableDatabase();
        bookDB = bookMapper.getReadableDatabase();
    }

    public void getDisplayOrderList(int uid){
        List<Order> orders = orderMapper.selectCartOrder(orderDB, uid);
        List<DisplayOrder> displayOrders = new ArrayList<>();
        for(Order o: orders){
            displayOrders.add(new DisplayOrder(bookMapper.selectById(bookDB, o.getBookId()), o));
        }
        orderList.setValue(displayOrders);
    }

    public void getTotalCount(){
        float price = 0.0F;
        int count = 0;
        for(DisplayOrder order: Objects.requireNonNull(orderList.getValue())){
            if(order.getSelected()){
                count += 1;
                price += order.getSumPrice();
            }
        }
        totalPrice.setValue(price);
        selectCount.setValue(count);
        notifyChange();
    }

    public LiveData<List<DisplayOrder>> getOrderList() {
        return orderList;
    }

    public LiveData<Float> getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float price) {
        totalPrice.setValue(price);
    }

    public LiveData<Integer> getSelectCount() {
        return selectCount;
    }

    public void setSelectCount(Integer count) {
        selectCount.setValue(count);
    }

    public void deleteOrder(DisplayOrder order) {
        orderMapper.deleteOrder(orderDB, order.getOrder());
        this.getDisplayOrderList(order.getUid());
    }

    public void updateItemChecked(DisplayOrder order) {
        List<DisplayOrder> orders = orderList.getValue();
        assert orders != null;
        int idx = orders.indexOf(order);
        order.setSelected(!order.getSelected());
        orders.set(idx, order);
        orderList.setValue(orders);
    }

    private PropertyChangeRegistry callbacks=new PropertyChangeRegistry();
    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    private void notifyChange(){
        callbacks.notifyCallbacks(this,0,null);
    }

    private void notifyPropertyChanged(int fieldId){
        callbacks.notifyCallbacks(this,fieldId,null);
    }
}