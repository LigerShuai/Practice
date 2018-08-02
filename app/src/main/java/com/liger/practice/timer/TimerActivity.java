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

    //播报耗时3秒
    private static final int TTS_TIME = 3000;
    private static final int WELCOME_TIME = 3000;
    private static final int WELCOME_HIDE = 5000;
    private static final int MUSIC_TIME = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        initView();

        initTask();
    }

    private void initTask() {
        //第一轮
        showDescription("你好啊");
        showTts("你好啊", new OnTtsListener() {
            @Override
            public void begin() {

            }

            @Override
            public void comleted() {
                TimerManager.postToMainThreadDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideTts();
                        TimerManager.postToMainThreadDelayed(new Runnable() {
                            @Override
                            public void run() {
                                hideDescription();
                                TimerManager.postToMainThreadDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // 第二轮
                                        showDescription("给你推荐了好听的歌曲");
                                        showTts("给你推荐了好听的歌曲", new OnTtsListener() {
                                            @Override
                                            public void begin() {

                                            }

                                            @Override
                                            public void comleted() {
                                                TimerManager.postToMainThreadDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        hideTts();
                                                        TimerManager.postToMainThreadDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                hideDescription();
                                                            }
                                                        }, MUSIC_TIME);
                                                    }
                                                }, TTS_TIME);
                                            }
                                        });
                                    }
                                }, WELCOME_HIDE);

                            }
                        }, WELCOME_TIME);
                    }
                }, TTS_TIME);
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
