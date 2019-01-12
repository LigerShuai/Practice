package com.liger.practice.designpattern.creational.factory.abstractfactory;

/**
 * @author zs
 * @date 2018/8/31 0031.
 */
public class Test {

    public static void main(String[] args) {
        SportCarFactory sportCarFactory = new SportCarFactory();
        sportCarFactory.getBenzCar().gasUp();
        sportCarFactory.getTeslaCar().charge();

        BusinessCarFactory businessCarFactory = new BusinessCarFactory();
        businessCarFactory.getBenzCar().gasUp();
        businessCarFactory.getTeslaCar().charge();
    }
}
