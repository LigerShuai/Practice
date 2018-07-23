package com.liger.practice.annotation;

/**
 * @author zs
 * @date 2018/7/3 0003.
 */
public class User {

    public static int child = 0x1;
    public static int man = 0x2;
    public static int woman = 0x3;
    public static int other = 0x4;

    private int userType;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
