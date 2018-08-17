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
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 转换操作符
 *
 * @author zs
 * @date 2018/8/16 0016.
 */
public class RxCombineActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_combine);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.concat_btn)
    void concatClick() {
        concatOperator();
    }

    @OnClick(R.id.concatArray_btn)
    void concatArrayClick() {
        concatArrayOperator();
    }

    @OnClick(R.id.merge_btn)
    void mergeClick() {
        mergeOperator();
    }

    @OnClick(R.id.concatArrayDelayError_btn)
    void concatArrayDelayErrorClick() {
        concatArrayDelayErrorOperator();
    }

    @OnClick(R.id.mergeArrayDelayError_btn)
    void mergeArrayDelayErrorClick() {
        mergeArrayDelayErrorOperator();
    }

    @OnClick(R.id.zip_btn)
    void zipClick() {
        zipOperator();
    }

    @OnClick(R.id.combineLatest_btn)
    void combineLatestClick() {
        combineLatestOperator();
    }

    @OnClick(R.id.reduce_btn)
    void reduceClick() {
        reduceOperator();
    }

    @OnClick(R.id.collect_btn)
    void collectClick() {
        collectOperator();
    }

    @OnClick(R.id.startWith_btn)
    void startWithClick() {
        startWithOperator();
    }

    @OnClick(R.id.count_btn)
    void countClick() {
        countOperator();
    }

    /**
     * 可以将多个被观察者组合在一起，然后按照之前发送顺序发送事件。
     * concat() 最多只可以组合4个被观察者。
     */
    private void concatOperator() {
        Observable.concat(Observable.just(1, 2),
                Observable.just(3, 4),
                Observable.just(5, 6),
                Observable.just(7, 8))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

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
                });
    }

    /**
     * 与 concat() 作用一样，不过可发送多个被观察者。
     */
    private void concatArrayOperator() {
        Observable.concatArray(Observable.just(1, 2),
                Observable.just(3, 4),
                Observable.just(5, 6),
                Observable.just(7, 8),
                Observable.just(9, 10))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

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
                });
    }

    /**
     * 与 concat() 作用基本一样，最多可合并4个被观察者
     * concat(): 是串行发送事件
     * merge():  是并行发送事件
     * mergeArray 同merge，可发送多个被观察者
     */
    private void mergeOperator() {
        //同时发送第一与第二个被观察者的事件
        Observable.merge(
                Observable.interval(1, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                return "A" + aLong;
                            }
                        }),
                Observable.interval(1, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                return "B" + aLong;
                            }
                        }))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });

        //concat只有等第一个被观察者发送完事件之后，才发送第二个被观察者的事件
        Observable.concat(
                Observable.interval(1, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                return "A" + aLong;
                            }
                        }),
                Observable.interval(1, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                return "B" + aLong;
                            }
                        }))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
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
     * 在 concatArray() 和 mergeArray() 两个方法当中，如果其中有一个被观察者发送了一个 Error 事件，那么就会停止发送事件.
     * 如果你想 onError() 事件延迟到所有被观察者都发送完事件后再执行的话，就可以使用
     * concatArrayDelayError() 和 mergeArrayDelayError()
     */
    private void concatArrayDelayErrorOperator() {
        //验证一下发送 onError() 事件是否会中断其他被观察者发送事件
       /* Observable.concatArray(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(100);
                        emitter.onError(new NullPointerException());
                    }
                }),
                Observable.just(1, 2, 3))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });*/

        //改成concatArrayDelayError
        Observable.concatArrayDelayError(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(100);
                        emitter.onError(new NullPointerException());
                    }
                }),
                Observable.just(1, 2, 3))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    private void mergeArrayDelayErrorOperator() {
    }

    /**
     * zip 专用于合并事件，该合并不是连接，而是两两配对，也就意味着，最终配对出的 Observable 发射事件数目只和少的那个相同。
     */
    private void zipOperator() {
        Observable.zip(
                Observable.intervalRange(1, 6, 1, 1, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                String s1 = "A" + aLong;
                                Log.d(TAG, "A发送的事件: " + s1);
                                return s1;
                            }
                        }),
                Observable.intervalRange(1, 3, 1, 1, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                String s2 = "B" + aLong;
                                Log.d(TAG, "B发送的事件: " + s2);
                                return s2;
                            }
                        }),
                new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) throws Exception {
                        return s + s2;
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 与 zip() 类似，但 combineLatest() 发送事件的序列是与发送的时间线有关。
     * 与其他被观察者最近发送的事件结合起来
     */
    private void combineLatestOperator() {
        Observable.combineLatest(
                Observable.intervalRange(1, 4, 1, 1, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                String s1 = "A" + aLong;
                                Log.d(TAG, "A 发送的事件: " + s1);
                                return s1;
                            }
                        }),
                Observable.intervalRange(1, 5, 2, 2, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                String s2 = "B" + aLong;
                                Log.d(TAG, "B发送的事件: " + s2);
                                return s2;
                            }
                        }),
                new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) throws Exception {
                        return s + s2;
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
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
     * 与 scan 操作符类似，scan 每处理一次数据就会将事件发送给观察者，而 reduce 会将所有数据处理完毕才会发送事件给观察者。
     */
    @SuppressLint("CheckResult")
    private void reduceOperator() {
        Observable.just(0, 1, 2, 3)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        int result = integer + integer2;
                        Log.d(TAG, "integer: " + integer + " integer2: " + integer2 + " result = " + result);
                        return result;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "accept: " + integer);
                    }
                });
    }

    /**
     * 将数据添加到数据结构当中。
     * 如下，将int数据添加到List中
     */
    @SuppressLint("CheckResult")
    private void collectOperator() {
        Observable.just(1, 2, 3, 4)
                .collect(new Callable<List<Integer>>() {
                             @Override
                             public List<Integer> call() throws Exception {
                                 return new ArrayList<>();
                             }
                         },
                        new BiConsumer<List<Integer>, Integer>() {
                            @Override
                            public void accept(List<Integer> integers, Integer integer) throws Exception {
                                integers.add(integer);
                            }
                        })
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Log.d(TAG, "accept: " + integers);
                    }
                });
    }

    /**
     * 在发送事件之前追加事件，追加的事件会先发出
     * startWith() 追加一个事件
     * startWithArray() 可以追加多个事件
     */
    @SuppressLint("CheckResult")
    private void startWithOperator() {
        Observable.just(5, 6, 7)
                .startWithArray(2, 3, 4)
                .startWith(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "accept: " + integer);
                    }
                });
    }

    /**
     * 返回被观察者发送事件的数量
     */
    @SuppressLint("CheckResult")
    private void countOperator() {
        Observable.just(1, 2, 3)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "accept: " + aLong);
                    }
                });
    }

}
