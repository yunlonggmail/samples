package com.yunlong.samples.design.factory.simple.impl;

/**
 * Created by shiyunlong on 2017/8/14.
 * 产品类：抽象产品角色
 * 它是工厂类所创建的所有对象的父类，封装了各个产品对象的共有方法，
 * 它的引入将提高系统的灵活性，使得工厂类定义一个通用的工厂方法，因为所有创建的具体产品对象都是其子类对象。
 */

public abstract class SimpleProduct {
    /**
     * 共有的方法，可以有一个或多个
     */
    public void methodSame() {

    }

    /**
     * 不同的方法，可以有一个或多个
     */
    public abstract String methodDiff();

}
