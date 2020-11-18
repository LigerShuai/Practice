package com.liger.practice.rx;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 创建操作符
 *
 * @author zs
 * @date 2018/8/15 0015.
 */
public class RxCreateActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_create);
        ButterKnife.bind(this);
    }

    /**
     * 使用三部曲
     * 1.初始化 Observable
     * 2.初始化 Observer
     * 3.建立订阅关系
     */
    @OnClick(R.id.activity_rx_create_btn)
    void createClick() {
        createOperator();
    }

    @OnClick(R.id.just_btn)
    void justClick() {
        justOperator();
    }

    @OnClick(R.id.fromArray_btn)
    void fromArrayClick() {
        fromArrayOperator();
    }

    @OnClick(R.id.fromCallable_btn)
    void fromCallableClick() {
        fromCallableOperator();
    }

    @OnClick(R.id.fromFuture_btn)
    void fromFutureClick() {
        fromFutureOperator();
    }

    @OnClick(R.id.fromIterable_btn)
    void fromIterableClick() {
        fromIterableOperator();
    }

    @OnClick(R.id.defer_btn)
    void deferClick() {
        deferOperator();
    }

    @OnClick(R.id.timer_btn)
    void timerClick() {
        timerOperator();
    }

    @OnClick(R.id.interval_btn)
    void intervalClick() {
        intervalOperator();
    }

    @OnClick(R.id.intervalRange_btn)
    void intervalRangeClick() {
        intervalRangeOperator();
    }

    @OnClick(R.id.range_btn)
    void rangeClick() {
        rangeOperator();
    }

    @OnClick(R.id.rangeLong_btn)
    void rangeLongClick() {
        rangeLongOperator();
    }

    @OnClick(R.id.empty_btn)
    void emptyClick() {
        emptyOperator();
    }

    @OnClick(R.id.never_btn)
    void neverClick() {
        neverOperator();
    }

    @OnClick(R.id.error_btn)
    void errorClick() {
        errorOperator();
    }

    /**
     * 创建一个被观察者
     */
    private void createOperator() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "subscribe: current thread: " + Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }


        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
        
        /*Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "subscribe: current thread: " + Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        Observer observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        observable.subscribe(observer);*/
    }

    /**
     * 创建一个被观察者，并发送事件，发送的事件不可以超过10个以上。
     */
    private void justOperator() {
        Observable.just(1, 2, 3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    /**
     * 这个方法和 just() 类似，只不过 fromArray 可以传入多于10个的变量，并且可以传入一个数组
     */
    private void fromArrayOperator() {
        Integer[] array = {1, 2, 3, 4};
        Observable.fromArray(array)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "fromArray onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "fromArray onComplete: ");
                    }
                });
    }

    /**
     * 这里的 Callable 是 java.util.concurrent 中的 Callable，Callable 和 Runnable 的用法基本一致，只是它会返回一个结果值，这个结果值就是发给观察者的。
     */
    @SuppressLint("CheckResult")
    private void fromCallableOperator() {
        Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "accept: " + integer);
            }
        });
    }

    /**
     * 参数中的 Future 是 java.util.concurrent 中的 Future，Future 的作用是增加了 cancel() 等方法操作 Callable，它可以通过 get() 方法来获取 Callable 返回的值。
     */
    @SuppressLint("CheckResult")
    private void fromFutureOperator() {
        final FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Log.d(TAG, "call: ");
                return "返回结果";
            }
        });

        Observable.fromFuture(futureTask)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        futureTask.run();
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept: " + s);

                    }
                });
    }

    /**
     * 直接发送一个 List 集合数据给观察者
     */
    private void fromIterableOperator() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Observable.fromIterable(list)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "fromIterable onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    /**
     * 直到有观察者（Observer）订阅时，才动态创建被观察者对象（Observable） & 发送事件
     */
    //1、第一次对 i 赋值
    private Integer i = 100;

    private void deferOperator() {
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                Log.d(TAG, "call: ");
                return Observable.just(i);
            }
        });

        //2、第二次对 i 赋值
        i = 200;

        //3、此时，有观察者订阅 Observable，才会调用 defer 创建 Observable
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "deferOperator onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "deferOperator onComplete: ");

            }
        });
    }

    /**
     * 延迟指定时间后，发送1个数值0（Long类型）
     */
    private void timerOperator() {
        Observable
                .timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "timer onNext: " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    /**
     * 每隔一段时间就会发送一个事件，这个事件是从0开始，不断增1的数字
     */
    private void intervalOperator() {
        Observable
                .interval(0, 2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "intervalOperator onNext: " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    /**
     * 可以指定发送事件的开始值和数量，其他与 interval() 的功能一样。
     */
    private void intervalRangeOperator() {
        Observable.intervalRange(2, 5, 1, 2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "intervalRangeOperator onSubscribe: ");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "intervalRangeOperator onNext: " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "intervalRangeOperator onComplete: ");
                    }
                });
    }

    /**
     * 连续发送1个事件序列，可指定范围
     */
    private void rangeOperator() {
        Observable.range(2, 5)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "rangeOperator onSubscribe: ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "rangeOperator onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "rangeOperator onComplete: ");
                    }
                });
    }

    /**
     * 作用与 range() 一样，只是数据类型为 Long.
     * 用法与 range() 一样
     */
    private void rangeLongOperator() {
        Observable.rangeLong(10, 6)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "rangeLongOperator onSubscribe: ");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "rangeLongOperator onNext: " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "rangeLongOperator onComplete: ");
                    }
                });
    }

    /**
     * 直接发送 onComplete() 事件
     */
    private void emptyOperator() {
        Observable.empty()
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "emptyOperator onSubscribe: ");
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG, "emptyOperator onNext: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "emptyOperator onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "emptyOperator onComplete: ");
                    }
                });
    }

    /**
     * 不发送任何事件
     */
    private void neverOperator() {
        Observable.never()
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "neverOperator onSubscribe: ");
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG, "neverOperator onNext: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "neverOperator onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "neverOperator onComplete: ");
                    }
                });
    }

    /**
     * 直接发送 onError() 事件
     */
    private void errorOperator() {
        Observable.error(new NullPointerException())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "errorOperator onSubscribe: ");
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG, "errorOperator onNext: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "errorOperator onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "errorOperator onComplete: ");
                    }
                });
    }
}
