package com.example.bookmall.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookmall.models.User;

public class MallViewModel extends ViewModel {
    private final MutableLiveData<User> userInfo = new MutableLiveData<>();

    public void setUserInfo(User user){
        userInfo.setValue(user);
    }

    public LiveData<User> getUserInfo() {
        return userInfo;
    }
}
