package com.liger.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.liger.practice.adview.AdViewActivity;
import com.liger.practice.aidlpractice.AIDLActivity;
import com.liger.practice.base.BaseActivity;
import com.liger.practice.floatwindow.FloatWindowService;
import com.liger.practice.view.DynamicViewActivity;

/**
 * @author zs
 * @date 2018/5/29 0029.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_aidl).setOnClickListener(this);
        findViewById(R.id.btn_dynamic_view).setOnClickListener(this);
        findViewById(R.id.btn_float_view).setOnClickListener(this);
        findViewById(R.id.btn_ad_view).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_aidl:
                startActivity(new Intent(this, AIDLActivity.class));
                break;
            case R.id.btn_dynamic_view:
                startActivity(new Intent(this, DynamicViewActivity.class));
                break;
            case R.id.btn_float_view:
                startService(new Intent(this, FloatWindowService.class));
                break;
            case R.id.btn_ad_view:
                startActivity(new Intent(this, AdViewActivity.class));
                break;
            default:
                break;
        }
    }
}
