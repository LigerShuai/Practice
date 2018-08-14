package com.liger.practice.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.view.View;
import android.widget.Button;

import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;

/**
 * @author zs
 * @date 2018/8/9 0009.
 */
public class ConstraintActivity extends BaseActivity implements View.OnClickListener {

    private ConstraintLayout mConstraintLayout;
    private Button mStart, mEnd;

    private ConstraintSet mAnim = new ConstraintSet();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        initView();
    }

    private void initView() {
        mConstraintLayout = findViewById(R.id.cons);
        mStart = findViewById(R.id.btn_a);
        mEnd = findViewById(R.id.btn_b);
        mStart.setOnClickListener(this);
        mEnd.setOnClickListener(this);
    }

    private void show() {
        TransitionManager.beginDelayedTransition(mConstraintLayout);
        mAnim.clone(mConstraintLayout);
        mAnim.setVisibility(R.id.tv_b, ConstraintSet.VISIBLE);
        mAnim.applyTo(mConstraintLayout);
    }

    private void hide() {
        TransitionManager.beginDelayedTransition(mConstraintLayout);
        mAnim.clone(mConstraintLayout);
        mAnim.setVisibility(R.id.tv_b, ConstraintSet.GONE);
        mAnim.applyTo(mConstraintLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_a:
                show();
                break;
            case R.id.btn_b:
                hide();
                break;
            default:
                break;
        }
    }
}
