package com.yunlong.lib.utils;

import android.Manifest;
import android.app.Activity;
import android.os.Environment;

import java.io.File;

/**
 * Created by shiyunlong on 2017/2/27.
 * 文件基本类
 */

public class FileUtils {
    /**
     * 根本路径
     */
    private static final String BASE_FOLDER = "samples";
    /**
     * 临时文件夹
     */
    private static final String TMP_FOLDER = ".tmp";

    /**
     * Android6.0+检查写入SD卡权限
     *
     * @param activity:activity界面
     * @param requestCode:请求码
     */
    public static void checkWriteExtraStoragePermission(Activity activity, int requestCode) {
        PermissionUtils.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
    }

    /**
     * 获得基础的存储路径
     */
    public static String getBaseExtraStoragePath() {
        String basePath = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            basePath = Environment.getExternalStorageDirectory() + File.separator + BASE_FOLDER;
        } else {
            basePath = Environment.getDataDirectory() + File.separator + BASE_FOLDER;
        }
        File rootDir = new File(basePath);
        if (!rootDir.exists()) {
            rootDir.mkdirs();
        }
        return rootDir.getAbsolutePath();
    }

    /**
     * 获得 存储路径
     *
     * @param packagePath：路径信息
     * @return
     */
    public static String getExtraStoragePath(String packagePath) {
        String path = getBaseExtraStoragePath() + File.separator + packagePath;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * 获得临时文件的存储路径
     */
    public static String getTmpExtraStoragePath() {
        String tmpPath = getBaseExtraStoragePath() + File.separator + TMP_FOLDER;
        File tmpFile = new File(tmpPath);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        return tmpFile.getAbsolutePath();
    }

}
