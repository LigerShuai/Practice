package com.liger.practice.adview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author zs
 * @date 2018/6/19 0019.
 */
@SuppressLint("AppCompatCustomView")
public class AdView2 extends ImageView {

    private int itemHeight;
    /**
     * 初始显示比率
     */
    private float rate = 1;

    public AdView2(Context context) {
        super(context);
    }

    public AdView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AdView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AdView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        itemHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        int w = getWidth();
        int h = getWidth() / drawable.getIntrinsicWidth() * drawable.getIntrinsicHeight();
        //设置图片显示的范围
        drawable.setBounds(0, 0, w, h);
        //图片可以移动的最大距离
        int maxY = h - itemHeight;
        canvas.save();
        canvas.translate(0, -rate * maxY);
        super.onDraw(canvas);
        canvas.restore();
    }
}
