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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.bookmall.adapter.BookAdapter;
import com.example.bookmall.adapter.CategoryAdapter;
import com.example.bookmall.adapter.OnClickListener;
import com.example.bookmall.dao.BookMapper;
import com.example.bookmall.dao.CategoryMapper;
import com.example.bookmall.databinding.FragmentHomeBinding;
import com.example.bookmall.models.Book;
import com.example.bookmall.models.Category;

import java.util.List;

public class HomeFragment extends Fragment{
    private FragmentHomeBinding binding;
    private BookAdapter bookAdapter;
    private CategoryAdapter categoryAdapter;
    private BookMapper bookMapper;
    private CategoryMapper categoryMapper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        categoryMapper = new CategoryMapper(getContext());
        bookMapper = new BookMapper(getContext());
        SQLiteDatabase categoryDB = categoryMapper.getWritableDatabase();

        // 类别
        categoryMapper.onCreate(categoryDB);
        List<Category> categories = categoryMapper.selectAll(categoryDB);
        categoryAdapter = new CategoryAdapter(categories, item -> {
            SQLiteDatabase db = bookMapper.getReadableDatabase();
            List<Book> books = bookMapper.selectByCategory(db, item.getId());
            bookAdapter.setBookList(books);
            bookAdapter.notifyDataSetChanged();
        });

        binding.fragmentHomeCategoryListView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.fragmentHomeCategoryListView.setAdapter(categoryAdapter);

        // 书籍
        SQLiteDatabase bookDB = bookMapper.getWritableDatabase();
        bookMapper.onCreate(bookDB);
        List<Book> books = bookMapper.selectByCategory(bookDB, 1);
        bookAdapter = new BookAdapter(books, new OnClickListener<Book>() {
            @Override
            public void onItemClick(Book item) {
                Toast.makeText(getContext(), "点击图片", Toast.LENGTH_SHORT).show();
            }
        });
        binding.fragmentHomeBookListView.setAdapter(bookAdapter);
        binding.fragmentHomeBookListView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}