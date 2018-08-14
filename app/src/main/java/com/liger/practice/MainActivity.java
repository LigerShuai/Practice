package com.liger.practice;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.liger.practice.adview.AdViewActivity;
import com.liger.practice.aidlpractice.AIDLActivity;
import com.liger.practice.base.BaseActivity;
import com.liger.practice.constant.RouterConstant;
import com.liger.practice.floatwindow.FloatActivity;
import com.liger.practice.floatwindow.FloatActivity2;
import com.liger.practice.greendao.GreenDaoHelper;
import com.liger.practice.greendao.User;
import com.liger.practice.util.AppUtil;
import com.liger.practice.view.ConstraintActivity;
import com.liger.practice.view.DynamicViewActivity;

/**
 * @author zs
 * @date 2018/5/29 0029.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;
    private Intent floatIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("shuai", "onCreate: ");
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);
        initView();
//        saveData();
    }

    private void initView() {
        findViewById(R.id.btn_aidl).setOnClickListener(this);
        findViewById(R.id.btn_dynamic_view).setOnClickListener(this);
        findViewById(R.id.btn_float_view).setOnClickListener(this);
        findViewById(R.id.btn_float_view2).setOnClickListener(this);
        findViewById(R.id.btn_ad_view).setOnClickListener(this);
        findViewById(R.id.btn_launch).setOnClickListener(this);
        findViewById(R.id.btn_db).setOnClickListener(this);
        findViewById(R.id.btn_timer).setOnClickListener(this);
        findViewById(R.id.btn_rx).setOnClickListener(this);
        findViewById(R.id.btn_anim).setOnClickListener(this);
        findViewById(R.id.btn_child_view).setOnClickListener(this);
        findViewById(R.id.btn_constraint).setOnClickListener(this);
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
        ARouter.getInstance().destroy();
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
                floatIntent = new Intent(this, FloatActivity.class);
                askForPermission();
                break;
            case R.id.btn_float_view2:
                startActivity(new Intent(this, FloatActivity2.class));
                break;
            case R.id.btn_ad_view:
                startActivity(new Intent(this, AdViewActivity.class));
                break;
            case R.id.btn_launch:
                AppUtil.launchApp(this, "com.liger.server");
                break;
            case R.id.btn_db:
//                startActivity(new Intent(this, DbActivity.class));
                ARouter.getInstance().build(RouterConstant.DB_ACTIVITY).navigation();
                break;
            case R.id.btn_timer:
                ARouter.getInstance().build(RouterConstant.TIMER_ACTIVITY).navigation();
                break;
            case R.id.btn_rx:
                ARouter.getInstance().build(RouterConstant.RX_ACTIVITY).navigation();
                break;
            case R.id.btn_anim:
                ARouter.getInstance().build(RouterConstant.ANIM_ACTIVITY).navigation();
                break;
            case R.id.btn_child_view:
                ARouter.getInstance().build(RouterConstant.CHILD_VIEW_ACTIVITY).navigation();
                break;
            case R.id.btn_constraint:
                startActivity(new Intent(this, ConstraintActivity.class));
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

    /**
     * 请求用户给予悬浮窗的权限
     */
    public void askForPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "当前无权限，请授权！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            } else {
                startActivity(floatIntent);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "权限授予成功！", Toast.LENGTH_SHORT).show();
                    //启动FxService
                    startActivity(floatIntent);
                }
            }
        }
    }
}

