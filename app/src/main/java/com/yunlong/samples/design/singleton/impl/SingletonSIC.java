package com.yunlong.samples.design.singleton.impl;

import java.io.ObjectStreamException;

/**
 * Created by shiyunlong on 2017/7/24.
 * 使用静态内部类(static inner class)实现
 * <p>
 * 内部类的字段只有在使用时才会被实例化，也就是说只有在第一次调用getInstance方法时才会被调用。
 * <p>
 * 优点：
 * 这种方式不仅能够确保线程安全，也能够保证单例对象的唯一性，同时也延迟了单例的实例化，所以比较推荐
 */

public class SingletonSIC {

    private SingletonSIC() {
    }

    public static SingletonSIC getInstance() {
        return SingletonHolder.SINGLETON_SIC;
    }

    private static class SingletonHolder {

        private static final SingletonSIC SINGLETON_SIC = new SingletonSIC();

    }

    /**
     * 方法
     */
    public void doSomething() {

    }

    /**
     * 反序列化使用
     *
     * @return
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {
        return SingletonHolder.SINGLETON_SIC;
    }

}
