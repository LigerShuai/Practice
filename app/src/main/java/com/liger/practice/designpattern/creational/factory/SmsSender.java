package com.liger.practice.designpattern.creational.factory;

/**
 * @author zs
 * @date 2018/8/30 0030.
 */
public class SmsSender implements Sender {

    @Override
    public void send() {
        System.out.println("send: sms");
    }
}
