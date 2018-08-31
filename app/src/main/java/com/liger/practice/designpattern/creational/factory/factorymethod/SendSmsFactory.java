package com.liger.practice.designpattern.creational.factory.factorymethod;

import com.liger.practice.designpattern.creational.factory.Sender;
import com.liger.practice.designpattern.creational.factory.SmsSender;

/**
 * @author zs
 * @date 2018/8/31 0031.
 */
public class SendSmsFactory implements Provider{

    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
