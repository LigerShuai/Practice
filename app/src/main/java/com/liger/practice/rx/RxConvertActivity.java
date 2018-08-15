package com.liger.practice.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * 转换操作符
 *
 * @author zs
 * @date 2018/8/15 0015.
 */
public class RxConvertActivity extends BaseActivity {

//    private static final String TAG = "RxConvertActivity";

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
        Observable.just(1, 2, 3)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "将int转换为string: " + integer;
                    }
                }).subscribe(new Observer<String>() {
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

    private void flatMapOperator() {
    }

    private void concatMapOperator() {
    }

    private void bufferOperator() {
    }

    private void groupByOperator() {
    }

    private void scanOperator() {
    }

    private void windowOperator() {
    }
}
