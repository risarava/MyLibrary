package com.supagorn.mylibrary.controller.activity;

import android.app.Activity;
import android.os.Bundle;

import com.supagorn.mylibrary.R;
import com.supagorn.mylibrary.ui.CustomDisplaySize;

/**
 * Created by iabellah on 2016-10-22.
 */

public class ImagePitchZoomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pitch_zoom);
        CustomDisplaySize.setDisplaySize(getWindowManager(), getWindow());
    }
}
