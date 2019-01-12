package com.liger.practice.designpattern.creational.builder;

/**
 * 力量型
 *
 * @author zs
 * @date 2018/8/31 0031.
 */
public class PowerHeroBuilder extends HeroBuilder {

    @Override
    protected HeroBuilder setHeroType() {
        mGloryHero.setHeroType(GloryHero.HERO_POWER);
        return this;
    }
}
