package com.liger.practice.rx;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

/**
 * 转换操作符
 *
 * @author zs
 * @date 2018/8/15 0015.
 */
public class RxConvertActivity extends BaseActivity {

    private List<Person> mPersonList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_convert);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.map_btn)
    void mapClick() {
        mapOperator();
    }

    @OnClick(R.id.flatMap_btn)
    void mapClick2() {
        map();
    }

    @OnClick(R.id.flatMap_btn)
    void flatMapClick() {
        flatMapOperator();
    }


    @OnClick(R.id.concatMap_btn)
    void concatMapClick() {
        concatMapOperator();
    }

    @OnClick(R.id.buffer_btn)
    void bufferClick() {
        bufferOperator();
    }

    @OnClick(R.id.groupBy_btn)
    void groupByClick() {
        groupByOperator();
    }

    @OnClick(R.id.scan_btn)
    void scanClick() {
        scanOperator();
    }

    @OnClick(R.id.window_btn)
    void windowClick() {
        windowOperator();
    }

    /**
     * 将圆形事件转换为矩形事件
     */
    private void mapOperator() {
        Observable
                .just(1, 2, 3)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "将int转换为string: " + integer;
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
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    /**
     * 需求：将 Person 集合中的每个元素中的 Plan 的 action 打印出来
     * 使用 map 实现 flatmap 的功能
     */
    private void map() {
        Observable.fromIterable(mPersonList)
                .map(new Function<Person, List<Plan>>() {
                    @Override
                    public List<Plan> apply(Person person) throws Exception {
                        return person.getPlanList();
                    }
                })
                .subscribe(new Observer<List<Plan>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Plan> plans) {
                        for (Plan plan : plans) {
                            List<String> actionList = plan.getActionList();
                            for (String action : actionList) {
                                Log.d(TAG, "onNext action " + action);
                            }
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

    private void flatMapOperator0() {
        final Person[] people = {};
        Observable
                .fromArray(people)
                .flatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) throws Exception {
                        return Observable.fromIterable(person.getPlanList());
                    }
                })
                .subscribe(new Observer<Plan>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Plan plan) {
                        Log.d(TAG, "onNext: " + plan.getContent());
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
     * 需求：将 Person 集合中的每个元素中的 Plan 的 action 打印出来
     * 它可以把一个发射器 Observable 通过某种方法转换为多个 Observables，然后再把这些分散的 Observables装进一个单一的发射器 Observable.
     * 但需要注意的是，flatMap 转发的事件是无序的.
     */
    private void flatMapOperator() {
        initFlatMapData();
        Log.d(TAG, "mPersonList.size : " + mPersonList.size());
        Observable.fromIterable(mPersonList)
                .flatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) throws Exception {
                        return Observable.fromIterable(person.getPlanList());
                    }
                })
                .flatMap(new Function<Plan, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Plan plan) throws Exception {
                        return Observable.fromIterable(plan.getActionList());
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext action: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mPersonList.clear();
                        Log.d(TAG, "onComplete: " + mPersonList.size());
                    }
                });

    }

    /**
     * flatMap:    转发的事件是无序的
     * contactMap: 转发的事件是有序的
     */
    private void concatMapOperator() {
    }

    /**
     * 从需要发送的事件当中获取一定数量的事件，并将这些事件放到缓冲区当中一并发出。
     * count:指缓冲区元素的数量
     * skip: 代表缓冲区满了之后，发送下一次事件序列的时候要跳过多少元素
     */
    private void bufferOperator() {
        Observable.just(1, 2, 3, 4, 5)
                .buffer(2, 1)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.d(TAG, "缓冲区大小: " + integers.size());
                        for (Integer i : integers) {
                            Log.d(TAG, "元素: " + i);
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

    /**
     * 将发送的数据进行分组，每个分组都会返回一个被观察者。
     * 将数据按照除以3的余数进行分组
     */
    private void groupByOperator() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .groupBy(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer % 3;
                    }
                })
                .subscribe(new Observer<GroupedObservable<Integer, Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(final GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                        integerIntegerGroupedObservable
                                .subscribe(new Observer<Integer>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                    }

                                    @Override
                                    public void onNext(Integer integer) {
                                        Log.d(TAG, "GroupedObservable onNext groupName: " + integerIntegerGroupedObservable.getKey()
                                                + " value: " + integer);
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.d(TAG, "GroupedObservable onComplete: ");
                                    }
                                });
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
     * reduce/scan
     * 将数据以一定的逻辑聚合起来
     */
    @SuppressLint("CheckResult")
    private void scanOperator() {
        Observable.just(1, 2, 3, 4, 5)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        Log.d(TAG, "apply: ");
                        Log.d(TAG, "apply integer: " + integer);
                        Log.d(TAG, "apply integer2: " + integer2);
                        return integer + integer2;
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
     * 发送指定数量的事件时，就将这些事件分为一组。
     * count 代表指定的数量，例如将 count 指定为2，那么每发2个数据就会将这2个数据分成一组。
     */
    private void windowOperator() {
        Observable.just(1, 2, 3, 4, 5)
                .window(2)
                .subscribe(new Observer<Observable<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Observable<Integer> integerObservable) {
                        Log.d(TAG, "onNext: ");
                        integerObservable.subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d(TAG, "integerObservable onSubscribe: ");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                Log.d(TAG, "integerObservable onNext: " + integer);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG, "integerObservable onComplete: ");
                            }
                        });
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

    private void initFlatMapData() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            stringList.add("Action " + i);
        }

        Plan plan;
        List<Plan> planList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            plan = new Plan();
            plan.setActionList(stringList);
            plan.setContent("Plan content " + i);
            plan.setTime("Plan time " + i);
            planList.add(plan);
        }

        Person person;
        for (int i = 0; i < 2; i++) {
            person = new Person();
            person.setPlanList(planList);
            person.setName("Person name " + i);
            mPersonList.add(person);
        }
    }

}
