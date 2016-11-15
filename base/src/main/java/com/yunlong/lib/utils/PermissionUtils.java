package com.yunlong.lib.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.support.annotation.NonNull;

import com.yunlong.base.R;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {


    public interface RequestPermissionsListener {
        void permissionGranted();
    }

    public static void requestPermissions(Activity activity, @NonNull String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] denyPermissions = checkPermissions(activity, permissions);
//            if (denyPermissions.length <= 0) {
//                listener.permissionGranted();
//            }
            if (permissions.length > 0)
                activity.requestPermissions(permissions, requestCode);
        }
    }

    /**
     * 未授权有哪些
     *
     * @param permissions
     * @return
     */
    public static String[] checkPermissions(Activity activity, @NonNull String[] permissions) {
        List<String> denyPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (activity.checkPermission(permission,
                    Process.myPid(),
                    Process.myUid()) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(permission);
            }
        }
        return denyPermissions.toArray(new String[denyPermissions.size()]);
    }

    /**
     * 用来规避某些需要特殊权限的操作,比如OutUtils,如果没有权限则不请求权限操作
     *
     * @param activity
     * @param permissions
     * @return
     */
    public static boolean checkPermissionsOK(Activity activity, @NonNull String[] permissions) {
        if (activity == null)
            throw new IllegalArgumentException("illegal argument...");
        if (permissions.length <= 0)
            return false;
        boolean isOk = true;
        for (String permission : permissions) {
            isOk &= (activity.checkPermission(permission,
                    Process.myPid(),
                    Process.myUid()) == PackageManager.PERMISSION_GRANTED);
        }

        return isOk;
    }

    /**
     * writeSetting 权限授权
     *
     * @param activity
     */
    public static void checkWriteSettingPermission(Activity activity, RequestPermissionsListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(activity)) {
                Uri selfPackageUri = Uri.parse("package:" + activity.getPackageName());
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, selfPackageUri);
                activity.startActivity(intent);
            } else {
                listener.permissionGranted();
            }
        }
    }

    /**
     * 跳转到app自身的setting页面
     *
     * @param activity
     */
    public static void goToMyAppSetting(Activity activity) {
        Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + activity.getPackageName()));
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(myAppSettings);
        activity.finish();
    }

    /**
     * 不能弹窗请求的要暗自请求
     *
     * @param activity
     * @param permissions
     * @return
     */
    public static boolean showPermissions(Activity activity, @NonNull String[] permissions) {
        List<String> shouldShowPermissions = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (activity.shouldShowRequestPermissionRationale(permission)) {
                    shouldShowPermissions.add(permission);
                }
            }
        }
        return !shouldShowPermissions.isEmpty();
    }

    /**
     * 判断权限结果是否被拒绝
     *
     * @param grantResults
     * @return
     */
    public static boolean isGrant(@NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            boolean isGrant = true;
            for (int result : grantResults) {
                isGrant &= (result == PackageManager.PERMISSION_GRANTED);
            }
            return isGrant;
        } else {
            // 权限被用户拒绝
            return false;
        }
    }

    /**
     * 展示询问对话框
     * @param activity
     * @param permissions
     */
    public static void showNeverAskDialog(final Activity activity, String[] permissions) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < permissions.length; i++) {
            if (i == 0)
                sb.append("\n");
            String permissionStr = "\"" + permissions[i] + "\"";
            sb.append(permissionStr);
            if (i != permissions.length - 1)
                sb.append("\n");
        }
        DialogFactory.showCommDialog(
                activity,
                activity.getString(R.string.permission_request_deny_title),
                activity.getString(R.string.permission_request_deny_info, sb.toString()),
                "",
                activity.getString(R.string.permission_request_open_setting),
                false,
                new DialogFactory.OnWhichListener() {
                    @Override
                    public void onConfirmClick(int which) {
                        if (which == DialogFactory.OnWhichListener.CHOOSE_SUBMIT)
                            goToMyAppSetting(activity);
                    }
                }
        );
    }
}