package com.supagorn.mylibrary.controller.activity;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.os.Bundle;
import android.support.v13.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.controller.fragment.TabHostHomePageFragment;
import com.supagorn.mylibrary.controller.fragment.TabHostProfilePageFragment;
import com.supagorn.mylibrary.ui.CustomDisplaySize;

/**
 * Created by iabellah on 2016-10-19.
 */

public class TabHostActivity extends Activity {

    private static final String TAB_HOME_TITLE = "Home";
    private static final String TAB_PROFILE_TITLE = "Profile";

    private FragmentTabHost mFragmentTabHost;

    private LocalActivityManager mlocalActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);
        CustomDisplaySize.setDisplaySize(getWindowManager(), getWindow());
        setupUI(savedInstanceState);
    }

    private void setupUI(Bundle savedInstance) {
        mlocalActivityManager = new LocalActivityManager(this, false);
        mlocalActivityManager.dispatchCreate(savedInstance);
        initFragmentTabhost();
    }

    private void initFragmentTabhost() {
        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mFragmentTabHost.setup(this, getFragmentManager(), android.R.id.tabcontent);
        addTabFragment(R.drawable.tab_home, TabHostHomePageFragment.class, TAB_HOME_TITLE);
        addTabFragment(R.drawable.tab_profile, TabHostProfilePageFragment.class, TAB_PROFILE_TITLE);

        mFragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //do something when tab change
            }
        });
    }

    private void addTabFragment(int drawableId, Class<?> aClass, String tag) {
        TabHost.TabSpec spec = mFragmentTabHost.newTabSpec(tag);
        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, mFragmentTabHost.getTabWidget(), false);
        ImageView icon = (ImageView) tabIndicator.findViewById(R.id.tab_icon);
        TextView title = (TextView) tabIndicator.findViewById(R.id.tab_title);
        icon.setImageResource(drawableId);
        title.setText(tag);
        spec.setIndicator(tabIndicator);
        mFragmentTabHost.addTab(spec, aClass, null);
    }
}
