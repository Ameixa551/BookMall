package com.example.bookmall.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bookmall.R;
import com.example.bookmall.databinding.ActivityMallBinding;
import com.example.bookmall.models.User;

public class MallActivity extends AppCompatActivity {

    private ActivityMallBinding binding;
    private MallViewModel mallViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMallBinding.inflate(getLayoutInflater());

        mallViewModel = new ViewModelProvider(this).get(MallViewModel.class);
        User user = (User)getIntent().getSerializableExtra("userInfo");
        mallViewModel.setUserInfo(user);


        setContentView(binding.getRoot());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_mall);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}