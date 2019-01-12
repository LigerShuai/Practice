package com.liger.practice.designpattern.creational.factory.abstractfactory;

/**
 * 商务车工厂
 *
 * @author zs
 * @date 2018/8/31 0031.
 */
public class BusinessCarFactory implements CarFactory {

    @Override
    public BenzCar getBenzCar() {
        return new BenzBusinessCar();
    }

    @Override
    public TeslaCar getTeslaCar() {
        return new TeslaBusinessCar();
    }
}
