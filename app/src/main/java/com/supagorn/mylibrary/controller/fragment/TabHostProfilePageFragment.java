package com.supagorn.mylibrary.controller.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.supagorn.mylibrary.R;

/**
 * Created by iabellah on 2016-10-19.
 */

public class TabHostProfilePageFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_tabhost, container, false);
        return rootView;
    }
}
