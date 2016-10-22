package com.supagorn.mylibrary.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.supagorn.mylibrary.model.Library;

import java.util.ArrayList;

/**
 * Created by iabellah on 2016-05-10.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Library> libraryArrayList;

    public ViewPagerAdapter(Context context, ArrayList<Library> libraryArrayList) {
        this.context = context;
        this.libraryArrayList = libraryArrayList;
    }

    @Override
    public int getCount() {
        return libraryArrayList.size();
    }

    @Override
    public View instantiateItem(final ViewGroup container, final int position) {
        ImageView img = new ImageView(container.getContext());
        img.setImageResource(libraryArrayList.get(position).getImage());
        container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

