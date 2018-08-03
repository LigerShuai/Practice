package com.liger.practice.rx;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * @author Liger
 * @date 2018/8/4 00:54
 */
public class TimeUtil {
    private static Disposable mDisposable;

    public static void timer(long delay, final IRxNext next) {
        Observable.timer(delay, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (aLong != null) {
                            next.doNext(aLong);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void cancel() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            Log.d("liger", "cancel: 定时器取消");
        }
    }

    public interface IRxNext {
        void doNext(long number);
    }
}
