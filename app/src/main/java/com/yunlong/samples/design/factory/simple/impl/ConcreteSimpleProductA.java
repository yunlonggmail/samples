package com.yunlong.samples.design.factory.simple.impl;

/**
 * Created by shiyunlong on 2017/8/14.
 * 具体的产品角色A
 * 简单工厂模式的创建目标，所有被创建的对象都充当这个角色某个具体类的实例。
 * 每一个具体产品角色都继承了抽象产品角色，需要实现在抽象产品中声明的抽象方法。
 */

public class ConcreteSimpleProductA extends SimpleProduct {
    /**
     * 具体的不同方法的实现
     */
    @Override
    public String methodDiff() {
        return this.getClass().getName();
    }
}
