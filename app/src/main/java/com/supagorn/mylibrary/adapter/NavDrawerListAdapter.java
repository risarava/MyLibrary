package com.supagorn.mylibrary.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.manager.DrawableColor;
import com.supagorn.mylibrary.model.NavDrawer;

import java.util.ArrayList;

/**
 * Created by iabellah on 2016-04-27.
 */


public class NavDrawerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavDrawer> navDrawers;

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawer> navDrawers) {
        this.context = context;
        this.navDrawers = navDrawers;
    }

    @Override
    public int getCount() {
        return navDrawers.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

        imgIcon.setImageDrawable(DrawableColor.changeDrawableColor(context, Color.WHITE,
                navDrawers.get(position).getIcon()));
        txtTitle.setText(navDrawers.get(position).getTitle());

        return convertView;
    }
}
