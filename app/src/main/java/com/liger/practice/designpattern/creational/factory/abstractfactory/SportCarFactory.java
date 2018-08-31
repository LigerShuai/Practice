package com.liger.practice.designpattern.creational.factory.abstractfactory;

/**
 * 跑车工厂
 *
 * @author zs
 * @date 2018/8/31 0031.
 */
public class SportCarFactory implements CarFactory {

    @Override
    public BenzCar getBenzCar() {
        return new BenzSportCar();
    }

    @Override
    public TeslaCar getTeslaCar() {
        return new TeslaSportCar();
    }
}
