package com.liger.practice.designpattern.structural.adapter.objectadapter;

import com.liger.practice.designpattern.structural.adapter.classadapter.Source;
import com.liger.practice.designpattern.structural.adapter.classadapter.Targetable;

/**
 * @author zs
 * @date 2018/8/31 0031.
 */
public class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source) {
        this.source = source;
    }

    @Override
    public void sourceMethod() {
        source.sourceMethod();
    }

    @Override
    public void targetMethod() {
        System.out.println("wrapper --> this is the targetable method");
    }
}
