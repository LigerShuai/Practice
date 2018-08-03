package com.liger.practice.timer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;
import com.liger.practice.constant.RouterConstant;
import com.liger.practice.rx.TimeUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Liger
 * @date 2018/8/2 23:32
 */
@Route(path = RouterConstant.TIMER_ACTIVITY)
public class TimerActivity2 extends BaseActivity {

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

//        initTask();
        rxTask2();
    }

    private void rxTask2() {
        //第一轮
        showDescription("你好啊");
        showTts("你好啊", new OnTtsListener() {
            @Override
            public void begin() {

            }

            @Override
            public void completed() {
                hideTts();
                //3s后文字消失
                TimeUtil.timer(3, new TimeUtil.IRxNext() {
                    @Override
                    public void doNext(long number) {
                        hideDescription();

                        //第二轮
                        TimeUtil.timer(3, new TimeUtil.IRxNext() {
                            @Override
                            public void doNext(long number) {
                                showDescription("给你推荐了好听的歌曲");
                                showTts("给你推荐了好听的歌曲", new OnTtsListener() {
                                    @Override
                                    public void begin() {

                                    }

                                    @Override
                                    public void completed() {
                                        hideTts();
                                        //3s 后文字消失
                                        TimeUtil.timer(3, new TimeUtil.IRxNext() {
                                            @Override
                                            public void doNext(long number) {
                                                hideDescription();
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    private void rxTask1() {
        //第一轮
        showDescription("你好啊");

        showTts("你好啊", new OnTtsListener() {
            @Override
            public void begin() {

            }

            @Override
            public void completed() {
                TimeUtil.timer(3, new TimeUtil.IRxNext() {
                    @Override
                    public void doNext(long number) {
                        hideTts();
                        TimeUtil.timer(3, new TimeUtil.IRxNext() {
                            @Override
                            public void doNext(long number) {
                                hideDescription();

                                //第二轮
                                showDescription("给你推荐了好听的歌曲");
                                showTts("给你推荐了好听的歌曲", new OnTtsListener() {
                                    @Override
                                    public void begin() {

                                    }

                                    @Override
                                    public void completed() {

                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

    }

    private void initTask() {
        //第一轮
        showDescription("你好啊");
        showTts("你好啊", new OnTtsListener() {
            @Override
            public void begin() {

            }

            @Override
            public void completed() {
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
                                            public void completed() {
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

    private Disposable mTtsDisposable;

    /**
     * 模拟耗时任务
     */
    @SuppressLint("CheckResult")
    private void showTts(String text, final OnTtsListener onTtsListener) {
        mTts.setText(text);
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Thread.sleep(3000);
                emitter.onNext(1);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mTtsDisposable = d;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("liger", "onNext: ");
                        onTtsListener.completed();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("liger", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("liger", "onComplete: ");
                    }
                });

    }

    private void hideDescription() {
        mDescription.setText("");
    }

    private void hideTts() {
        mTts.setText("");
    }
}
