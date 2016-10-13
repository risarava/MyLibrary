package com.supagorn.mylibrary.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.adapter.RecyclerViewAdapter;
import com.supagorn.mylibrary.data.Library;
import com.supagorn.mylibrary.ui.MyToast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private ArrayList<Library> libraryArrayList = new ArrayList<Library>();
    private String[] mTitle;

    private RecyclerView recyclerView;

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupUI();
    }

    private void setupUI() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        mTitle = getResources().getStringArray(R.array.title);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        libraryArrayList.clear();
        for (String title : mTitle) {
            Library library = new Library();
            library.setTitle(title);
            libraryArrayList.add(library);
        }

        adapter = new RecyclerViewAdapter(libraryArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerViewAdapter.RecyclerItemClickListener(mContext, recyclerView,
                        new RecyclerViewAdapter.RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // int position = (Integer) rv.getTag();
                                Intent intent;
                                switch (position) {
                                    case 0:
                                        MyToast myToast = new MyToast();
                                        myToast.showToast(mContext, "Preview toast");
                                        break;
                                    case 1:
                                        intent = new Intent(mContext, SampleAlertDialog.class);
                                        startActivity(intent);
                                        break;
                                    case 2:
                                        intent = new Intent(mContext, SelectImageGallery.class);
                                        startActivity(intent);
                                        break;
                                    case 3:
                                        intent = new Intent(mContext, FacebookActivity.class);
                                        startActivity(intent);
                                        break;
                                }
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
        );
    }
}
