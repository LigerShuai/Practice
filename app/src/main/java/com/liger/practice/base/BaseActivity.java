package com.liger.practice.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * @author zs
 * @date 2018/5/29 0029.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = "----";
    private static final int STACK_TRACE_DEEP = 4;

    private void getTag() {
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();  // 最核心的方法
        String clsName = traces[STACK_TRACE_DEEP].getFileName();
        String methodName = traces[STACK_TRACE_DEEP].getMethodName();
        TAG = clsName + " " + methodName + ": ";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        /*if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }*/
        getTag();
    }
}
