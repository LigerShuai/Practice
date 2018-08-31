package com.liger.practice.designpattern.creational.builder;

/**
 * 具体建造者
 * 智力型
 *
 * @author zs
 * @date 2018/8/31 0031.
 */
public class AgileHeroBuilder extends HeroBuilder {

    @Override
    protected HeroBuilder setHeroType() {
        mGloryHero.setHeroType(GloryHero.HERO_AGILE);
        return this;
    }
}
