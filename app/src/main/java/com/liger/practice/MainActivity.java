package com.liger.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.liger.practice.adview.AdViewActivity;
import com.liger.practice.aidlpractice.AIDLActivity;
import com.liger.practice.base.BaseActivity;
import com.liger.practice.floatwindow.FloatWindowService;
import com.liger.practice.greendao.DbActivity;
import com.liger.practice.greendao.GreenDaoHelper;
import com.liger.practice.greendao.User;
import com.liger.practice.util.AppUtil;
import com.liger.practice.view.DynamicViewActivity;

/**
 * @author zs
 * @date 2018/5/29 0029.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("shuai", "onCreate: ");
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_aidl).setOnClickListener(this);
        findViewById(R.id.btn_dynamic_view).setOnClickListener(this);
        findViewById(R.id.btn_float_view).setOnClickListener(this);
        findViewById(R.id.btn_ad_view).setOnClickListener(this);
        findViewById(R.id.btn_launch).setOnClickListener(this);
        findViewById(R.id.btn_db).setOnClickListener(this);
//        saveData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("shuai", "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("shuai", "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("shuai", "onDestroy: ");
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
            case R.id.btn_launch:
                AppUtil.launchApp(this, "com.liger.server");
                break;
            case R.id.btn_db:
                startActivity(new Intent(this, DbActivity.class));
                break;
            default:
                break;
        }
    }

    private void saveData() {
        User user1 = new User(1, 20, "Tom");
        User user2 = new User(2, 25, "John");
        User user3 = new User(3, 15, "Jack");
        GreenDaoHelper.getInstance().save(user1);
        GreenDaoHelper.getInstance().save(user2);
        GreenDaoHelper.getInstance().save(user3);
        Log.d("shuai", "saveData: " + user1.getName() + "\n" + user2.getName() + "\n" + user3.getName());
    }
}
