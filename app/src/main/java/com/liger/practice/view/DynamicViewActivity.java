package com.liger.practice.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author zs
 * @date 2018/6/11 0011.
 */
public class DynamicViewActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(this);
        textView.setText("ss");
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(textView);

        TextView textView2 = new TextView(this);
        textView2.setText("的方式看见的");
        textView2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(textView2);

        ImageView divider = new ImageView(this);
        LinearLayout.LayoutParams dividerParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 10);
        dividerParam.gravity = Gravity.CENTER_HORIZONTAL;
        divider.setBackgroundColor(Color.RED);
        dividerParam.setMargins(30, 0, 0, 30);
        divider.setLayoutParams(dividerParam);

        linearLayout.addView(divider);
        setContentView(linearLayout);
    }
}
