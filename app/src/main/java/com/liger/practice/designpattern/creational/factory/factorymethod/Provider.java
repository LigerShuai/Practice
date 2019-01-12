package com.liger.practice.designpattern.creational.factory.factorymethod;

import com.liger.practice.designpattern.creational.factory.Sender;

/**
 * @author zs
 * @date 2018/8/31 0031.
 */
public interface Provider {
    Sender produce();
}
