package com.supagorn.mylibrary.ui;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by iabellah on 2016-10-10.
 */

public class MyToast {
    private Toast mCurrentToast;

    public void showToast(Context context, String text) {
        if (mCurrentToast != null) {
            mCurrentToast.cancel();
        }
        mCurrentToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        mCurrentToast.show();
    }

    public void cancelToast() {
        if (mCurrentToast != null) {
            mCurrentToast.cancel();
        }
    }
}
