package com.liger.practice.designpattern.creational.factory.factorymethod;

import com.liger.practice.designpattern.creational.factory.MailSender;
import com.liger.practice.designpattern.creational.factory.Sender;

/**
 * @author zs
 * @date 2018/8/31 0031.
 */
public class SendMailFactory implements Provider {

    @Override
    public Sender produce() {
        return new MailSender();
    }
}
