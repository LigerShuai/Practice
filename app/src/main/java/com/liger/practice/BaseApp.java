package com.liger.practice;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.launcher.ARouter;
import com.liger.practice.greendao.DaoMaster;
import com.liger.practice.greendao.DaoSession;

/**
 * @author zs
 * @date 2018/6/15 0015.
 */
public class BaseApp extends Application {

    private static Context mContext;
    public static BaseApp mInstance;

    private boolean isDebug = true;

    private DaoSession mDaoSession;

    public static Context getContext() {
        return mContext;
    }

    public static BaseApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mInstance = this;
        initGreenDao();
        initARouter();
    }

    private void initARouter() {
        if (isDebug) {
            ARouter.openLog();
            //如果在InstantRun模式下运行，必须开启调试模式！
            ARouter.openDebug();
        }
        ARouter.init(this);
    }


    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "test2.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
