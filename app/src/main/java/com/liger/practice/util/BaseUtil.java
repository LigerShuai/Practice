package com.liger.practice.util;

import com.liger.practice.BaseApp;

/**
 * @author zs
 * @date 2018/6/15 0015.
 */
public class BaseUtil {

    /**
     * 获取状态栏高度
     *
     * @return 默认返回-1
     */
    public static int getStatusHeight() {
        int resourceId = BaseApp.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return BaseApp.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return -1;
    }
}
