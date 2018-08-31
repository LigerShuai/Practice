package com.liger.practice.designpattern.creational.builder;

/**
 * 敏捷型
 *
 * @author zs
 * @date 2018/8/31 0031.
 */
public class IntellectualHeroBuilder extends HeroBuilder {

    @Override
    protected HeroBuilder setHeroType() {
        mGloryHero.setHeroType(GloryHero.HERO_INTELLECTUAL);
        return this;
    }
}
