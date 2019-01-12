package com.liger.practice.designpattern.creational.factory.abstractfactory;

/**
 * 抽象工厂
 *
 * @author zs
 * @date 2018/8/31 0031.
 */
public interface CarFactory {

    BenzCar getBenzCar();

    TeslaCar getTeslaCar();
}
