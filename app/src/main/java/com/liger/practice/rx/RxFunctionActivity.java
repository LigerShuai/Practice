package com.liger.practice.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 转换操作符
 *
 * @author zs
 * @date 2018/8/16 0016.
 */
public class RxFunctionActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_function);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.delay_btn)
    void delayClick() {
        delayOperator();
    }

    private void delayOperator() {
    }

    @OnClick(R.id.doOnEach_btn)
    void doOnEachClick() {
        doOnEachOperator();
    }

    private void doOnEachOperator() {
    }

    @OnClick(R.id.doOnNext_btn)
    void doOnNextClick() {
        doOnNextOperator();
    }

    private void doOnNextOperator() {
    }

    @OnClick(R.id.doAfterNext_btn)
    void doAfterNextClick() {
        doAfterNextOperator();
    }

    private void doAfterNextOperator() {
    }

    @OnClick(R.id.doOnComplete_btn)
    void doOnCompleteClick() {
        doOnCompleteOperator();
    }

    private void doOnCompleteOperator() {
    }

    @OnClick(R.id.doOnError_btn)
    void doOnErrorClick() {
        doOnErrorOperator();
    }

    private void doOnErrorOperator() {
    }

    @OnClick(R.id.doOnSubscribe_btn)
    void doOnSubscribeClick() {
        doOnSubscribeOperator();
    }

    private void doOnSubscribeOperator() {
    }

    @OnClick(R.id.doOnDispose_btn)
    void doOnDisposeClick() {
        doOnDisposeOperator();
    }

    private void doOnDisposeOperator() {
    }

    @OnClick(R.id.doOnLifecycle_btn)
    void doOnLifecycleClick() {
        doOnLifecycleOperator();
    }

    private void doOnLifecycleOperator() {

    }

    @OnClick(R.id.doOnTerminate_btn)
    void doOnTerminateClick() {
        doOnTerminateOperator();
    }

    private void doOnTerminateOperator() {

    }

    @OnClick(R.id.doFinally_btn)
    void doFinallyClick() {
        doFinallyOperator();
    }

    private void doFinallyOperator() {

    }

    @OnClick(R.id.onErrorReturn_btn)
    void onErrorReturnClick() {
        onErrorReturnOperator();
    }

    private void onErrorReturnOperator() {

    }

    @OnClick(R.id.onErrorResumeNext_btn)
    void onErrorResumeNextClick() {
        onErrorResumeNextOperator();
    }

    private void onErrorResumeNextOperator() {

    }

    @OnClick(R.id.onExceptionResumeNext_btn)
    void onExceptionResumeNextClick() {
        onExceptionResumeNextOperator();
    }

    private void onExceptionResumeNextOperator() {

    }

    @OnClick(R.id.retry_btn)
    void retryClick() {
        retryOperator();
    }

    private void retryOperator() {

    }

    @OnClick(R.id.retryUntil_btn)
    void retryUntilClick() {
        retryUntilOperator();
    }

    private void retryUntilOperator() {

    }

    @OnClick(R.id.retryWhen_btn)
    void retryWhenClick() {
        retryWhenOperator();
    }

    private void retryWhenOperator() {

    }

    @OnClick(R.id.repeat_btn)
    void repeatClick() {
        repeatOperator();
    }

    private void repeatOperator() {

    }

    @OnClick(R.id.repeatWhen_btn)
    void repeatWhenClick() {
        repeatWhenOperator();
    }

    private void repeatWhenOperator() {

    }

    @OnClick(R.id.subscribeOn_btn)
    void subscribeOnClick() {
        subscribeOnOperator();
    }

    private void subscribeOnOperator() {

    }

    @OnClick(R.id.observeOn_btn)
    void observeOnClick() {
        observeOnOperator();
    }

    private void observeOnOperator() {

    }

}
