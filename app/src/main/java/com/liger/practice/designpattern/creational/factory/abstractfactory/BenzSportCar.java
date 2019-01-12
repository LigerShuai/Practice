package com.liger.practice.designpattern.creational.factory.abstractfactory;

/**
 * 具体产品: 奔驰跑车
 *
 * @author zs
 * @date 2018/8/31 0031.
 */
public class BenzSportCar implements BenzCar {
    @Override
    public void gasUp() {
        System.out.println("给我的奔驰跑车加满汽油");
    }
}
