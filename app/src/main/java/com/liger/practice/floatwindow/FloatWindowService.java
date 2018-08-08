package com.liger.practice.floatwindow;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

/**
 * 悬浮窗服务
 *
 * @author zs
 * @date 2018/6/15 0015.
 */
public class FloatWindowService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FloatWindowManager.createFloatView(this);
    }

    @Override
    public void onDestroy() {
        FloatWindowManager.removeFloatView(this);
        super.onDestroy();
    }
}
