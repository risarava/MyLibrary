package com.supagorn.mylibrary.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.adapter.RecyclerViewAdapter;
import com.supagorn.mylibrary.model.Library;
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

        adapter = new RecyclerViewAdapter(this, libraryArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerViewAdapter.RecyclerItemClickListener(mContext, recyclerView,
                        new RecyclerViewAdapter.RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Class classz = null;
                                switch (position) {
                                    case 0:
                                        MyToast myToast = new MyToast();
                                        myToast.showToast(mContext, "Preview toast");
                                        break;
                                    case 1:
                                        classz = SampleAlertDialog.class;
                                        break;
                                    case 2:
                                        classz = SelectImageGallery.class;
                                        break;
                                    case 3:
                                        classz = FacebookActivity.class;
                                        break;
                                    case 4:
                                        classz = RetrofitActivity.class;
                                        break;
                                    case 5:
                                        classz = SendEmailActivity.class;
                                        break;
                                    case 6:
                                        classz = TabHostActivity.class;
                                        break;
                                    case 7:
                                        classz = BottomTabsActivity.class;
                                        break;
                                    case 8:
                                        classz = DrawerLayoutActivity.class;
                                        break;
                                    case 9:
                                        classz = ImagePitchZoomActivity.class;
                                        break;
                                    case 10:
                                        classz = ViewPagerAndIndicatorActivity.class;
                                        break;

                                    default:
                                        break;
                                }

                                Intent intent = new Intent(mContext, classz);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
        );
    }
}
