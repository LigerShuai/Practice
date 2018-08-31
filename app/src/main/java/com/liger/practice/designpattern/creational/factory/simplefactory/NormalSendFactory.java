package com.liger.practice.designpattern.creational.factory.simplefactory;

import com.liger.practice.designpattern.creational.factory.MailSender;
import com.liger.practice.designpattern.creational.factory.Sender;
import com.liger.practice.designpattern.creational.factory.SmsSender;

/**
 * 简单工厂模式: 普通
 *
 * @author zs
 * @date 2018/8/30 0030.
 */
public class NormalSendFactory {

    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            return null;
        }
    }
}
