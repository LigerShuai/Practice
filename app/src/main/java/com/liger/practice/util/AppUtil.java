package com.liger.practice.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * @author zs
 * @date 2018/6/29 0029.
 */
public class AppUtil {

    public static void launchApp(Context context, String pkg) {
        if (!AppUtil.isAppInstalled(context, pkg)) {
            Log.d("shuai", "launchApp: 未安装");
            return;
        }
        PackageManager pm = context.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(pkg);
        if (intent != null) {
            try {
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            return context != null && !TextUtils.isEmpty(packageName) && context.getPackageManager().getApplicationInfo(packageName, 0) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
