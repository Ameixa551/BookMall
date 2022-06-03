package com.example.bookmall.activity.ui.home;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookmall.adapter.CategoryAdapter;
import com.example.bookmall.adapter.OnItemClickListener;
import com.example.bookmall.dao.CategoryMapper;
import com.example.bookmall.databinding.FragmentHomeBinding;
import com.example.bookmall.models.Category;

import java.util.List;

public class HomeFragment extends Fragment implements OnItemClickListener<Category> {

    private FragmentHomeBinding binding;
    private CategoryMapper mapper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        mapper = new CategoryMapper(getContext());
        SQLiteDatabase db = mapper.getWritableDatabase();
        mapper.onCreate(db);
        List<Category> categories = mapper.selectAll(db);
        CategoryAdapter categoryAdapter = new CategoryAdapter(this.getContext(), categories, this);
        binding.fragmentHomeCategoryListView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.fragmentHomeCategoryListView.setAdapter(categoryAdapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(Category item) {
        Toast.makeText(this.getContext(), "点击", Toast.LENGTH_SHORT).show();
    }
}