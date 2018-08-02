package com.liger.practice.timer;


import android.os.Handler;
import android.os.Looper;

/**
 * @author Liger
 * @date 2018/8/2 23:35
 */
public class TimerManager {

    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    private TimerManager() {

    }

    private static class TimerManagerHolder {
        private static final TimerManager INSTANCE = new TimerManager();

    }

    public static TimerManager getInstance() {
        return TimerManagerHolder.INSTANCE;
    }

    public void postToMainThreadDelayed(Runnable runnable, long delay) {
        if (runnable == null) {
            return;
        }
        mMainHandler.postDelayed(runnable, delay);
    }

    public void removeRunnableFromMainThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        mMainHandler.removeCallbacks(runnable);
    }

}
