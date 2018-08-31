package com.liger.practice.designpattern.creational.builder;

/**
 * @author zs
 * @date 2018/8/31 0031.
 */
public class Test {

    public static void main(String[] args) {
        PowerHeroBuilder builder = new PowerHeroBuilder();
        builder.setHeroName("蒙多")
                .setHeroDes("大肉")
                .setHeroType()
                .addHeroSkill("R", "回血");

        Director director = new Director(builder);
        GloryHero hero = director.getGloryHero();

        String heroName = hero.getHeroName();
        String heroDes = hero.getHeroDes();
        String heroSkill = hero.getHeroSkills().get("R");
        int heroType = hero.getHeroType();

        System.out.println("name = " + heroName + " des = " + heroDes + " skill = " + heroSkill + " type = " + heroType);
    }
}
