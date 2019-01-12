package com.liger.practice.designpattern.creational.builder;

import android.support.annotation.IntDef;

import java.util.HashMap;
import java.util.Map;

/**
 * Product 产品类
 * 王者荣耀英雄
 *
 * @author zs
 * @date 2018/8/31 0031.
 */
public class GloryHero {

    //英雄分类：智力型，力量型，敏捷型
    public static final int HERO_INTELLECTUAL = 1;
    public static final int HERO_POWER = 2;
    public static final int HERO_AGILE = 3;

    @IntDef(value = {HERO_AGILE, HERO_POWER, HERO_INTELLECTUAL})
    public @interface HeroType {

    }

    //英雄属性
    private String heroName;
    private String heroDes;
    private int heroType;
    private Map<String, String> heroSkills = new HashMap<>();

    //施放技能
    public void executeSkill(String key) {
        if (heroSkills.containsKey(key)) {
            System.out.println("施放" + key + " 技能: " + heroSkills.get(key));
        }
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroDes() {
        return heroDes;
    }

    public void setHeroDes(String heroDes) {
        this.heroDes = heroDes;
    }

    public int getHeroType() {
        return heroType;
    }

    public void setHeroType(int heroType) {
        this.heroType = heroType;
    }

    public Map<String, String> getHeroSkills() {
        return heroSkills;
    }

    public void setHeroSkills(Map<String, String> heroSkills) {
        this.heroSkills = heroSkills;
    }

    public void addHeroSkill(String key, String skill) {
        heroSkills.put(key, skill);
    }

}
