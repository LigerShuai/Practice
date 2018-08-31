package com.liger.practice.designpattern.creational.builder;

import io.reactivex.annotations.NonNull;

/**
 * @author zs
 * @date 2018/8/31 0031.
 */
public class Director {

    private HeroBuilder mBuilder;

    public Director(@NonNull HeroBuilder builder) {
        mBuilder = builder;
    }

    public GloryHero getGloryHero() {
        if (mBuilder != null) {
            return mBuilder.build();
        }
        return null;
    }
}
