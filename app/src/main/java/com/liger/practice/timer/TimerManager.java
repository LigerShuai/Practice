package com.liger.practice.timer;


import android.os.Handler;
import android.os.Looper;

/**
 * @author Liger
 * @date 2018/8/2 23:35
 */
public class TimerManager {

    private static Handler mMainHandler = new Handler(Looper.getMainLooper());

    public static void postToMainThreadDelayed(Runnable runnable, long delay) {
        if (runnable == null) {
            return;
        }
        mMainHandler.postDelayed(runnable, delay);
    }

    public static void removeRunnableFromMainThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        mMainHandler.removeCallbacks(runnable);
    }

}
