package com.yunlong.samples.design.singleton.impl;

/**
 * Created by shiyunlong on 2017/7/24.
 * 枚举单例模式
 * <p>
 * 优点：
 * 默认线程是安全的，任何情况下都是一个单例，即使是在序列化的时候
 */

public enum SingletonEnum {
    INSTANCE;

    /**
     * 方法
     */
    public void doSomething() {

    }
}
