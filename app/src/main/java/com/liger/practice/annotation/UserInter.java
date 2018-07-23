package com.liger.practice.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 使用注解替代枚举
 * https://blog.csdn.net/wzgiceman/article/details/53483665
 *
 * @author zs
 * @date 2018/7/3 0003.
 */
public class UserInter {

    public static final int CHILD = 0x1;
    public static final int MAN = 0x2;
    public static final int WOMAN = 0x3;
    public static final int OTHER = 0x4;

    @IntDef({CHILD, MAN, WOMAN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface UserInters {
    }

    private int userType;

    @UserInters
    public int getUserType() {
        return userType;
    }

    public void setUserType(@UserInters int userType) {
        this.userType = userType;
    }
}
