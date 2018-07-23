package com.liger.practice.floatwindow;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.liger.practice.R;
import com.liger.practice.util.BaseUtil;

/**
 * @author zs
 * @date 2018/6/15 0015.
 */
public class FloatWindowManager {

    private static WindowManager sWindowManager;
    private static WindowManager.LayoutParams mParams;

    private static ActivityManager sActivityManager;

    /**
     * 悬浮窗
     */
    private static LinearLayout mFloatView;

    /**
     * 创建悬浮窗
     */
    @SuppressLint("ClickableViewAccessibility")
    public static void createFloatView(Context context) {
        final WindowManager manager = getWindowManager(context);
        mParams = new WindowManager.LayoutParams();

        //设置窗口类型，系统提示型窗口，显示在应用程序之上
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        //设置为背景透明
        mParams.format = PixelFormat.RGBA_8888;
        //设置为不可聚焦
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        mParams.gravity = Gravity.START | Gravity.TOP;
        mParams.x = 0;
        mParams.y = 0;
        mParams.width = 300;
        mParams.height = 100;

        mFloatView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_float, null);
        mFloatView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mParams.x = (int) (event.getRawX() - 150);
                mParams.y = (int) (event.getRawY() - 150 - BaseUtil.getStatusHeight());
                manager.updateViewLayout(mFloatView, mParams);
                return false;
            }
        });
        manager.addView(mFloatView, mParams);
    }

    public static void removeFloatView(Context context) {
        WindowManager manager = getWindowManager(context);
        if (mFloatView != null) {
            manager.removeView(mFloatView);
            mFloatView = null;
        }
    }

    /**
     * 是否有悬浮窗显示在屏幕上
     */
    private static boolean isFloatShowing() {
        return mFloatView != null;
    }

    private static WindowManager getWindowManager(Context context) {
        if (sWindowManager == null) {
            sWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return sWindowManager;
    }

    private static ActivityManager getActivityManager(Context context) {
        if (sActivityManager == null) {
            sActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        }
        return sActivityManager;
    }
}
