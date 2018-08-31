package com.liger.practice.designpattern.structural.adapter.objectadapter;

import com.liger.practice.designpattern.structural.adapter.classadapter.Source;
import com.liger.practice.designpattern.structural.adapter.classadapter.Targetable;

/**
 * @author zs
 * @date 2018/8/31 0031.
 */
public class Test {

    public static void main(String[] args) {
        Source source = new Source();
        Targetable target = new Wrapper(source);
        target.sourceMethod();
        target.targetMethod();
    }
}
