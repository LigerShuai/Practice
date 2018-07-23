package com.liger.practice.annotation;

import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.liger.practice.R;
import com.liger.practice.base.BaseActivity;

/**
 * 期望函数传递固定的几个 int 值，三种实现方式
 *
 * @author zs
 * @date 2018/7/3 0003.
 */
public class AnnotationActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);

        normal();
        enums();
        annotation();

        doSomething("ss");
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                doSomething("ss");
            }
        }).start();*/

        doSth1("12345");
//        doSth1("123456");
        doSth2(9);
    }

    /**
     * 普通方式，传递的不是期望值，不报错
     */
    private void normal() {
        User user = new User();
        user.setUserType(User.child);

        // 不报错
        user.setUserType(100);
    }

    /**
     * 枚举方式,占用内存较多
     */
    private void enums() {
        UserE userE = new UserE();
        userE.setUserType(UserE.UserEnum.child);
    }

    /**
     * 注解方式，传递的不是期望值，报错
     */
    private void annotation() {
        UserInter userInter = new UserInter();
        userInter.setUserType(UserInter.CHILD);
        userInter.setUserType(UserInter.MAN);
        userInter.setUserType(UserInter.WOMAN);

        // 报错，达到目的
//        userInter.setUserType(100);
    }

    @WorkerThread
    private void doSomething(String str) {
        Log.d("shuai", "doSomething: " + str);
    }

    private void doSth1(@Size(min = 1, max = 5) String s) {
        Log.d("shuai", "doSomething: " + s);
    }

    private void doSth2(@IntRange(from = 1, to = 10) int i) {
        Log.d("shuai", "doSth2: " + i);
    }
}
