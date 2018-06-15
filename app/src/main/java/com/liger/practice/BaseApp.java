package com.liger.practice;

import android.app.Application;
import android.content.Context;

/**
 * @author zs
 * @date 2018/6/15 0015.
 */
public class BaseApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
