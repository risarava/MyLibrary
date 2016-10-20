package com.supagorn.mylibrary.controller.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *  Created by iabellah on 2016-10-20.
 */
public class SampleFragment extends Fragment {
    private static final String ARG_TEXT = "ARG_TEXT";

    public SampleFragment() {
    }

    public static SampleFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);

        SampleFragment sampleFragment = new SampleFragment();
        sampleFragment.setArguments(args);

        return sampleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setPadding(50, 50, 50, 50);
        textView.setTextSize(20);
        textView.setText(getArguments().getString(ARG_TEXT));

        return textView;
    }
}
