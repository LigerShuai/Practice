package com.liger.practice.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author zs
 * @date 2018/7/23 0023.
 */

@Entity
public class User {

    @Id
    private long id;
    private int age;
    private String name;

    @Generated(hash = 1271781227)
    public User(long id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
