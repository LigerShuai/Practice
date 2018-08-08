package com.liger.practice.anim;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;
import com.liger.practice.constant.RouterConstant;

/**
 * 子view超出父view
 *
 * @author zs
 * @date 2018/8/6 0006.
 */

@Route(path = RouterConstant.CHILD_VIEW_ACTIVITY)
public class ChildViewActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_view);
    }

}
