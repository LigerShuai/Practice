package com.liger.practice;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.liger.practice.greendao.DaoMaster;
import com.liger.practice.greendao.DaoSession;

/**
 * @author zs
 * @date 2018/6/15 0015.
 */
public class BaseApp extends Application {

    private static Context mContext;
    public static BaseApp mInstance;

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mInstance = this;
        initGreenDao();
    }

    public static Context getContext() {
        return mContext;
    }

    public static BaseApp getInstance() {
        return mInstance;
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "test.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
