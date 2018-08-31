package com.liger.practice.designpattern.creational.factory.simplefactory;

import com.liger.practice.designpattern.creational.factory.MailSender;
import com.liger.practice.designpattern.creational.factory.Sender;
import com.liger.practice.designpattern.creational.factory.SmsSender;

/**
 * 简单工厂模式: 多方法
 *
 * @author zs
 * @date 2018/8/30 0030.
 */
public class MultiSendFactory {

    public Sender produceMail() {
        return new MailSender();
    }

    public Sender produceSms() {
        return new SmsSender();
    }
}
