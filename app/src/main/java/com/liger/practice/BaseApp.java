package com.liger.practice;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.liger.practice.floatwindow.FloatActivity2;
import com.liger.practice.greendao.DaoMaster;
import com.liger.practice.greendao.DaoSession;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.PermissionListener;

/**
 * @author zs
 * @date 2018/6/15 0015.
 */
public class BaseApp extends Application {

    private static final String TAG = "BaseApp";
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
        initFloatWindow();
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

    /**
     * 初始化悬浮窗
     */
    private void initFloatWindow() {
        View mFloatView = LayoutInflater.from(this).inflate(R.layout.view_float, null);
        FloatWindow.with(getApplicationContext())
                .setView(mFloatView)
                .setWidth(200)
                .setHeight(200)
                .setX(50)
                .setY(100)
//                .setPermissionListener(mPermissionListener)
                .setMoveType(MoveType.active)
                .setFilter(true, FloatActivity2.class)
                .setDesktopShow(false)
                .build();
    }

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSuccess() {
            Log.d(TAG, "onSuccess");
        }

        @Override
        public void onFail() {
            Log.d(TAG, "onFail");
        }
    };
}
