package com.supagorn.mylibrary.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.adapter.ViewPagerAdapter;
import com.supagorn.mylibrary.model.Library;
import com.supagorn.mylibrary.ui.CustomDisplaySize;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by iabellah on 2016-10-22.
 */

public class ViewPagerAndIndicatorActivity  extends Activity{
    private int image[] = {R.drawable.ic_recents, R.drawable.ic_favorites, R.drawable.ic_friends,
            R.drawable.ic_nearby, R.drawable.ic_restaurants};
    private ArrayList<Library> promotionItemArrayList = new ArrayList<Library>();

    private CircleIndicator indicator;
    private ViewPager viewPager;

    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage_and_indicator);
        CustomDisplaySize.setDisplaySize(getWindowManager(), getWindow());
        setupUI();
    }

    private void setupUI() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        indicator = (CircleIndicator) findViewById(R.id.indicator);

        setViewPager();
    }

    private void setViewPager() {
        indicator.removeAllViews();
        promotionItemArrayList.clear();
        for (int i = 0; i < image.length; i++) {
            Library library = new Library();
            library.setImage(image[i]);
            promotionItemArrayList.add(library);
        }

        adapter = new ViewPagerAdapter(this, promotionItemArrayList);
        viewPager.setAdapter(adapter);
        // set indicator
        indicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());
    }
}
