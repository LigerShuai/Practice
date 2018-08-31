package com.liger.practice.designpattern.structural.adapter.classadapter;

/**
 * @author zs
 * @date 2018/8/31 0031.
 */
public class Adapter extends Source implements Targetable {

    @Override
    public void targetMethod() {
        System.out.println("this is the targetable method");
    }

}
