package com.liger.practice.floatwindow;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;

/**
 * @author zs
 * @date 2018/8/8 0008.
 */
public class FloatActivity extends BaseActivity {

    private HomeWatcherReceiver mHomeKeyReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerHomeKeyReceiver(this);
        FloatWindowManager.createFloatView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterHomeKeyReceiver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FloatWindowManager.removeFloatView(this);
    }

    private void registerHomeKeyReceiver(Context context) {
        mHomeKeyReceiver = new HomeWatcherReceiver();
        final IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.registerReceiver(mHomeKeyReceiver, homeFilter);
    }

    private void unregisterHomeKeyReceiver(Context context) {
        if (null != mHomeKeyReceiver) {
            context.unregisterReceiver(mHomeKeyReceiver);
        }
    }
}
