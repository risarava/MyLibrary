package com.supagorn.mylibrary.manager;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/**
 * Created by iabellah on 2016-10-21.
 */

public class DrawableColor {

    public static Drawable changeDrawableColor(Context context, int color, int drawable) {
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_ATOP;
        Drawable icon = context.getResources().getDrawable(drawable);
        icon.setColorFilter(color, mode);
        return icon;
    }

    public static Drawable changeDrawableColor(int color, Drawable drawable) {
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_ATOP;
        drawable.setColorFilter(color, mode);
        return drawable;
    }
}
