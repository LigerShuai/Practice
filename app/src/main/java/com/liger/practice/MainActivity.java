package com.liger.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.liger.practice.aidlpractice.AIDLActivity;
import com.liger.practice.base.BaseActivity;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_aidl:
                startActivity(new Intent(MainActivity.this, AIDLActivity.class));
                break;
            default:
                break;
        }
    }
}
