package com.supagorn.mylibrary.model;

/**
 * Created by iabellah on 2016-04-27.
 */
public class NavDrawer {

    private String title;
    private int icon;

    public NavDrawer() {
    }

    public NavDrawer(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return this.title;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}