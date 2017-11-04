package com.ramonapp.android.movieapp.interfaces;

/**
 * Created by vahid on 8/15/2017.
 */

public interface FragmentCallback {
    public void setToolbarLayout(boolean isSub);
    public void setToolbarTitle(String title);
    public void showWaiting();
    public void dismissWaiting();
}
