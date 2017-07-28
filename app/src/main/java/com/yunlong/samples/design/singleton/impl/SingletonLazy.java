package com.yunlong.samples.design.singleton.impl;

import java.io.ObjectStreamException;

/**
 * Created by shiyunlong on 2017/7/19.
 * 懒汉式
 * 懒汉模式是声明一个静态对象并在用户第一次调用时进行初始化
 * <p>
 * 优点：
 * 单例对象只有在使用时才被实例化，在一定程度上节约了资源
 * <p>
 * 缺点：
 * 第一次加载时需要及时的实例化，反应稍慢，最大的问题是每次调用getInstance都进行同步，造成不必要的开销。
 */

public class SingletonLazy {

    private static SingletonLazy singletonLazy;

    /**
     * 私有化构造函数
     */
    private SingletonLazy() {
    }

    /**
     * 获取实例
     * 第一次调用时进行初始化，并加入同步代码块，解决多线程问题。
     *
     * @return
     */
    public static synchronized SingletonLazy getInstance() {
        if (singletonLazy == null)
            singletonLazy = new SingletonLazy();
        return singletonLazy;
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
        return singletonLazy;
    }
}
