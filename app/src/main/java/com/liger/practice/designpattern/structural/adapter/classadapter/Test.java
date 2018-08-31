package com.liger.practice.designpattern.structural.adapter.classadapter;

/**
 * 类的适配器模式
 *
 * @author zs
 * @date 2018/8/31 0031.
 */
public class Test {

    public static void main(String[] args) {
        Targetable target = new Adapter();
        target.sourceMethod();
        target.targetMethod();
    }
}
