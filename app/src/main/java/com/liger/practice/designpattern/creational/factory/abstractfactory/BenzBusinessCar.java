package com.liger.practice.designpattern.creational.factory.abstractfactory;

/**
 * 具体产品: 奔驰商务车
 *
 * @author zs
 * @date 2018/8/31 0031.
 */
public class BenzBusinessCar implements BenzCar {
    @Override
    public void gasUp() {
        System.out.println("给我的奔驰商务车加满汽油");
    }
}
