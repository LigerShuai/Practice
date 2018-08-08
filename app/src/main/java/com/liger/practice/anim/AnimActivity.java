package com.liger.practice.anim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;
import com.liger.practice.constant.RouterConstant;

/**
 * @author zs
 * @date 2018/8/6 0006.
 */

@Route(path = RouterConstant.ANIM_ACTIVITY)
public class AnimActivity extends BaseActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        initView();
        int left = mImageView.getLeft();
        int top = mImageView.getTop();
        int right = mImageView.getRight();
        int bottom = mImageView.getBottom();

        Log.d("shuai", "onCreate: left = " + left + " top = " + top + " right = " + right + " bottom = " + bottom);
    }

    private void initView() {
        mImageView = findViewById(R.id.activity_anim_iv);
    }
}
