package com.liger.practice.floatwindow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.PermissionListener;

/**
 * @author zs
 * @date 2018/8/8 0008.
 */
public class FloatActivity2 extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float);
        View view = FloatWindow.get().getView();
        Button button = view.findViewById(R.id.view_float_btn);
        button.setText("adasd");
    }

}
