package com.supagorn.mylibrary.ui;

import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by iabellah on 2016-10-10.
 */

public class CustomDisplaySize {

    public static void setDisplaySize(WindowManager windowManager, Window window) {
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        window.setLayout((int)(width*.9),(int)(height*.8));
    }
}
