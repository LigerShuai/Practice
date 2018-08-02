package com.liger.practice.timer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;
import com.liger.practice.constant.RouterConstant;

/**
 * @author Liger
 * @date 2018/8/2 23:32
 */
@Route(path = RouterConstant.TIMER_ACTIVITY)
public class TimerActivity extends BaseActivity {

    private TextView mTts;
    private TextView mDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        initView();

        showDescription("你好啊");

        showTts("你好啊", new OnTtsListener() {
            @Override
            public void begin() {

            }

            @Override
            public void comleted() {
                TimerManager.getInstance().postToMainThreadDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideTts();
                        TimerManager.getInstance().postToMainThreadDelayed(new Runnable() {
                            @Override
                            public void run() {
                                hideDescription();
                            }
                        }, 3000);
                    }
                }, 3000);
            }
        });
    }

    private void initView() {
        mDescription = findViewById(R.id.activity_timer_description);
        mTts = findViewById(R.id.activity_timer_tts);
    }

    private void showDescription(String text) {
        mDescription.setText(text);
    }

    private void showTts(String text, OnTtsListener onTtsListener) {
        mTts.setText(text);
        onTtsListener.comleted();
    }

    private void hideDescription() {
        mDescription.setText("");
    }

    private void hideTts() {
        mTts.setText("");
    }
}
