package com.liger.practice.floatwindow;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.liger.practice.R;
import com.liger.practice.util.BaseUtil;

/**
 * 悬浮窗服务
 *
 * @author zs
 * @date 2018/6/15 0015.
 */
public class FloatWindowService extends Service {

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mParams;

    private LinearLayout mFloatView;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createFloatWindow();
    }

    @Override
    public void onDestroy() {
        if (mFloatView != null) {
            mWindowManager.removeView(mFloatView);
        }
        super.onDestroy();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void createFloatWindow() {
        mWindowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
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

        mFloatView = (LinearLayout) LayoutInflater.from(getApplication()).inflate(R.layout.view_float, null);
        mFloatView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mParams.x = (int) (event.getRawX() - 150);
                mParams.y = (int) (event.getRawY() - 150 - BaseUtil.getStatusHeight());
                mWindowManager.updateViewLayout(mFloatView, mParams);
                return false;
            }
        });
        mWindowManager.addView(mFloatView, mParams);
    }

}
