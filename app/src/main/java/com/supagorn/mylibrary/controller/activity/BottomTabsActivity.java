package com.supagorn.mylibrary.controller.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.controller.fragment.SampleFragment;
import com.supagorn.mylibrary.ui.CustomDisplaySize;
import com.supagorn.mylibrary.ui.MyToast;

/**
 * Created by iabellah on 2016-10-20.
 */

public class BottomTabsActivity extends Activity {
    private Context mContext;
    private BottomBar bottomBar;
    private BottomBarTab nearbyTab;
    private MyToast myToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);
        CustomDisplaySize.setDisplaySize(getWindowManager(), getWindow());
        setupUI();
    }

    private void setupUI() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mContext = this;
        myToast = new MyToast();

        setTabClickListener();
        setTabReselectListener();

        updateBadge();
    }

    private void setTabClickListener() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment fragment = null;

                switch (tabId) {
                    case R.id.tab_recents:
                        fragment = SampleFragment.newInstance(getResources().getString(R.string.recents));
                        break;
                    case R.id.tab_favorites:
                        fragment = SampleFragment.newInstance(getResources().getString(R.string.favorites));
                        break;
                    case R.id.tab_nearby:
                        fragment = SampleFragment.newInstance(getResources().getString(R.string.nearby));
                        break;
                    case R.id.tab_friends:
                        fragment = SampleFragment.newInstance(getResources().getString(R.string.friends));
                        break;
                    case R.id.tab_food:
                        fragment = SampleFragment.newInstance(getResources().getString(R.string.food));
                        break;
                }

                getFragmentManager().beginTransaction().replace(R.id.frame_container,
                        fragment).addToBackStack(null).commit();
            }
        });
    }

    private void setTabReselectListener() {
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                String tab = null;
                switch (tabId) {
                    case R.id.tab_recents:
                        tab = getResources().getString(R.string.recents);
                        break;
                    case R.id.tab_favorites:
                        tab = getResources().getString(R.string.favorites);
                        break;
                    case R.id.tab_nearby:
                        tab = getResources().getString(R.string.nearby);
                        break;
                    case R.id.tab_friends:
                        tab = getResources().getString(R.string.friends);
                        break;
                    case R.id.tab_food:
                        tab = getResources().getString(R.string.food);
                        break;
                }
                myToast.showToast(mContext, tab);
            }
        });
    }

    private void updateBadge() {
        nearbyTab = bottomBar.getTabWithId(R.id.tab_nearby);
        nearbyTab.setBadgeCount(5);
    }
}
