package com.liger.practice.designpattern.creational.factory.simplefactory;

import com.liger.practice.designpattern.creational.factory.Sender;

/**
 * 简单工厂模式
 * 类的创建依赖工厂类，也就是说，如果想要拓展程序，必须对工厂类进行修改，违背了开闭原则
 *
 * @author zs
 * @date 2018/8/30 0030.
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
//        normal();
//        multi();
        staticFactory();
    }

    private static void staticFactory() {
        StaticSendFactory.produceMail().send();
        StaticSendFactory.produceSms().send();
    }

    private static void multi() {
        MultiSendFactory factory = new MultiSendFactory();
        Sender sender = factory.produceMail();
        sender.send();
        factory.produceSms().send();
    }

    private static void normal() {
        NormalSendFactory factory = new NormalSendFactory();
        Sender sender = factory.produce("sms");
        sender.send();
    }
}
