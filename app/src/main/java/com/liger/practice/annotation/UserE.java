package com.liger.practice.annotation;

/**
 * 使用注解替代枚举
 * https://blog.csdn.net/wzgiceman/article/details/53483665
 *
 * @author zs
 * @date 2018/7/3 0003.
 */
public class UserE {

    public enum UserEnum {
        child,
        man,
        woman,
        other
    }

    private UserEnum userType;

    public UserEnum getUserType() {
        return userType;
    }

    public void setUserType(UserEnum userType) {
        this.userType = userType;
    }

}
