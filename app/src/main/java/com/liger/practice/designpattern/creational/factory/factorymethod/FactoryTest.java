package com.liger.practice.designpattern.creational.factory.factorymethod;

/**
 * 工厂方法模式，扩展性较好，只需新增工厂类
 *
 * @author zs
 * @date 2018/8/31 0031.
 */
public class FactoryTest {

    public static void main(String[] args) {
        new SendMailFactory().produce().send();
        new SendSmsFactory().produce().send();
    }
}
