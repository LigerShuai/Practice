package com.liger.practice.designpattern.creational.builder;


/**
 * 抽象建造者
 *
 * @author zs
 * @date 2018/8/31 0031.
 */
public abstract class HeroBuilder {

    //最终建造的对象
    GloryHero mGloryHero;

    public HeroBuilder() {
        mGloryHero = new GloryHero();
    }

    public HeroBuilder setHeroName(String name) {
        mGloryHero.setHeroName(name);
        return this;
    }

    public HeroBuilder setHeroDes(String des) {
        mGloryHero.setHeroDes(des);
        return this;
    }

    public HeroBuilder addHeroSkill(String key, String skill) {
        mGloryHero.addHeroSkill(key, skill);
        return this;
    }

    protected abstract HeroBuilder setHeroType();

    public GloryHero build() {
        return mGloryHero;
    }

}
