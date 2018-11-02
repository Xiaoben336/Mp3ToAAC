package com.example.zjf.mp3toaac;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

public class MyApplication extends Application {
    private static Context mContext;
    public static int screenWidth;
    public static int screenHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        DisplayMetrics displayMetrics = getApplicationContext().getResources()
                .getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;
        screenHeight= displayMetrics.heightPixels;
    }

    public static Context getContext(){
        return mContext;
    }
}
