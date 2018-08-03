package com.liger.practice.rx;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;
import com.liger.practice.constant.RouterConstant;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author Liger
 * @date 2018/8/3 23:54
 */

@Route(path = RouterConstant.TIMER_ACTIVITY)
public class RxActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        initRx();
        TimeUtil.timer(3, new TimeUtil.IRxNext() {
            @Override
            public void doNext(long number) {
                Log.d("liger", "doNext: ");
            }
        });

    }

    @SuppressLint("CheckResult")
    private void initRx() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Log.d("liger", "subscribe: emitter " + emitter);
                emitter.onNext(2);
                Log.d("liger", "subscribe: emitter " + emitter);
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


}
