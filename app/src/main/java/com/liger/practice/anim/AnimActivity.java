package com.liger.practice.anim;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;
import com.liger.practice.constant.RouterConstant;

/**
 * @author zs
 * @date 2018/8/6 0006.
 */

@Route(path = RouterConstant.ANIM_ACTIVITY)
public class AnimActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "AnimActivity";
    private ImageView mImageView;
    private Button mStart, mShowBtn;
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        initView();
        int left = mImageView.getLeft();
        int top = mImageView.getTop();
        int right = mImageView.getRight();
        int bottom = mImageView.getBottom();

        Log.d(TAG, "onCreate: left = " + left + " top = " + top + " right = " + right + " bottom = " + bottom);

        float x = mImageView.getX();
        float y = mImageView.getY();
        Log.d(TAG, "onCreate: x = " + x + " y = " + y);

       /* translation(100);
        mImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
//                translation(0);
            }
        }, 2000);*/

    }

    private void translation(float distance) {
        ObjectAnimator valueAnimator = ObjectAnimator.ofFloat(mImageView, "translationY", distance);
        valueAnimator.setDuration(2000);
        valueAnimator.start();

        /*ObjectAnimator.ofFloat(mImageView,"translationY",distance)
                .setDuration(2000)
                .start();*/
    }

    private void anim1() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mShowBtn,"textColor",0xffffffff,0xffff0000);
        objectAnimator.setDuration(3000);
        objectAnimator.setRepeatCount(Animation.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }

    private void initView() {
        mImageView = findViewById(R.id.activity_anim_iv);
        mStart = findViewById(R.id.btn_start);
        mShowBtn = findViewById(R.id.btn_show);
        mTextView = findViewById(R.id.activity_anim_tv);
        mStart.setOnClickListener(this);
        mTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
//                anim1();
//                mImageView.offsetTopAndBottom(60);
                mTextView.setText("Test");
                mTextView.offsetTopAndBottom(60);
            default:
                break;
        }
    }

}