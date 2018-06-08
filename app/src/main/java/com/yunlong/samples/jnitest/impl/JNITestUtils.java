package com.yunlong.samples.jnitest.impl;

public class JNITestUtils {

    static {
        System.loadLibrary("JNI_Test");
    }

    public static JNITestUtils mJNITestUtils;

    public static synchronized JNITestUtils newInstance() {
        if (mJNITestUtils == null) {
            synchronized (JNITestUtils.class) {
                if (mJNITestUtils == null)
                    mJNITestUtils = new JNITestUtils();
            }
        }
        return mJNITestUtils;
    }


    public native String dynamicFunc1(String name);

}
