package com.yunlong.samples.incremental;

/**
 * Created by shiyunlong on 2016/10/11.
 * 将Apk包合成
 */
public class BsPatch {

    static {
        System.loadLibrary("bspatch");
    }

    public static native int bspatch(String oldApk, String newApk, String patch);

}