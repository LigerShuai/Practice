package com.liger.practice.designpattern.structural.adapter.classadapter;

/**
 * @author zs
 * @date 2018/8/31 0031.
 */
public interface Targetable {

    /**
     * 与source类中的方法相同
     */
    void sourceMethod();

    /**
     * 新类中的方法
     */
    void targetMethod();
}
