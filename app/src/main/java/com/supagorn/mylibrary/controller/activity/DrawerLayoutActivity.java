package com.supagorn.mylibrary.controller.activity;

import android.app.Fragment;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.adapter.NavDrawerListAdapter;
import com.supagorn.mylibrary.controller.fragment.SampleFragment;
import com.supagorn.mylibrary.model.NavDrawer;
import com.supagorn.mylibrary.ui.CustomDisplaySize;

import java.util.ArrayList;

/**
 * Created by iabellah on 2016-10-21.
 */

public class DrawerLayoutActivity extends AppCompatActivity {
    private Context mContext;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private TextView mToolbarTitleTextView;
    private TextView mHeaderNavTextView;
    private View headerNavDrawerView;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private Toolbar myToolbar;
    private ArrayList<NavDrawer> navDrawers;
    private NavDrawerListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        CustomDisplaySize.setDisplaySize(getWindowManager(), getWindow());
        setupUI();
    }

    private void setupUI() {
        myToolbar = (Toolbar) findViewById(R.id.mToolBar);
        mToolbarTitleTextView = (TextView) findViewById(R.id.toolbar_Title_TextView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) findViewById(R.id.list_slidermenu);
        mContext = this;

        setDrawerLayout();
    }

    private void setDrawerLayout() {
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        setSupportActionBar(myToolbar);
        setTitle("");
        navDrawers = new ArrayList<NavDrawer>();

        for (int i = 0; i < navMenuTitles.length; i++) {
            navDrawers.add(new NavDrawer(navMenuTitles[i], navMenuIcons.getResourceId(i, 0)));
        }

        // Recycle the typed array
        navMenuIcons.recycle();
        // add string header
        headerNavDrawerView = getLayoutInflater().inflate(R.layout.drawer_header, null);
        mHeaderNavTextView = (TextView) headerNavDrawerView.findViewById(R.id.header_Textview);
        mHeaderNavTextView.setText(R.string.app_name);
        mDrawerListView.addHeaderView(headerNavDrawerView, headerNavDrawerView, false);
        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(mContext, navDrawers);
        mDrawerListView.setAdapter(adapter);
        // set onClick listview drawer
        mDrawerListView.setOnItemClickListener(new SlideMenuClickListener());
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        //set nav drawer selected position 1
        displayView(1);
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 1:
                fragment = SampleFragment.newInstance(navMenuTitles[position - 1]);
                break;
            case 2:
                fragment = SampleFragment.newInstance(navMenuTitles[position - 1]);
                break;
            case 3:
                fragment = SampleFragment.newInstance(navMenuTitles[position - 1]);
                break;
            case 4:
                fragment = SampleFragment.newInstance(navMenuTitles[position - 1]);
                break;
            case 5:
                fragment = SampleFragment.newInstance(navMenuTitles[position - 1]);
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerListView.setItemChecked(position, true);
            mDrawerListView.setSelection(position);
            mToolbarTitleTextView.setText(navMenuTitles[position - 1]);
            mDrawerLayout.closeDrawer(mDrawerListView);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
