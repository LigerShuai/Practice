package com.liger.practice.designpattern.creational.factory.abstractfactory;

/**
 * 特斯拉跑车
 *
 * @author zs
 * @date 2018/8/31 0031.
 */
public class TeslaSportCar implements TeslaCar {
    @Override
    public void charge() {
        System.out.println("给我的特斯拉跑车充满电");
    }
}
