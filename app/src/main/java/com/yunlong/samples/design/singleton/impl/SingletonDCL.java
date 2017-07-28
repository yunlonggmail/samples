package com.yunlong.samples.design.singleton.impl;

import java.io.ObjectStreamException;

/**
 * Created by shiyunlong on 2017/7/19.
 * Double CheckLock模式
 * <p>
 * 优点：
 * 资源利用率高，第一次执行getInstance时单例对象才会被实例化，效率高
 * <p>
 * 缺点：
 * 第一次加载反应稍慢，也由于Java内存模型的原因偶尔失败，并在高并发环境下也有一定的缺陷，虽然发生的概率很小
 * <p>
 * 问题：
 * DCL失效问题，在JDK1.5以前JVM的原因在高并发状态下，有的子线程中singletonDCL可能会出现空的问题
 */

public class SingletonDCL {
    /**
     * DCL失效问题解决：
     * 在JDK1.5及以后使用volatile能够解决该问题。
     */
    private volatile static SingletonDCL singletonDCL;

    /**
     * 私有化构造函数
     */
    private SingletonDCL() {
    }

    /**
     * 获取实例
     * 双层判空的意义
     * 1、第一层判断主要是为了不必要的同步
     * 2、第二层判断是为了在非空状态下创建实例
     *
     * @return
     */
    public static synchronized SingletonDCL getInstance() {
        if (singletonDCL == null) {
            synchronized (SingletonDCL.class) {
                if (singletonDCL == null)
                    singletonDCL = new SingletonDCL();
            }
        }
        return singletonDCL;
    }

    /**
     * 方法
     */
    public void doSomething() {

    }

    /**
     * 反序列化使用
     * 为了避免在反序列化可能会出现创建新的实例对象的情况。
     *
     * @return
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {
        return singletonDCL;
    }
}
