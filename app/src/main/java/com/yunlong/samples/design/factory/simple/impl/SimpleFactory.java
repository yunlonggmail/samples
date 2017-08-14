package com.yunlong.samples.design.factory.simple.impl;

/**
 * Created by shiyunlong on 2017/8/14.
 * 工厂角色，工厂类
 * 简单工厂的核心，负责实现创建所有产品实例的内部逻辑；
 * 工厂类可以被外界调用，创建所需的产品对象；
 * 在工厂类中提供了静态的工厂方法factoryMethod()，它的返回类型为抽象产品类型Product;
 */

public class SimpleFactory {
    /**
     * 具体产品A的类型
     */
    public static final String SIMPLE_CONCRETE_PRODUCT_A = "simple_concrete_product_a";
    /**
     * 具体产品B的类型
     */
    public static final String SIMPLE_CONCRETE_PRODUCT_B = "simple_concrete_product_b";

    /**
     * 生产产品的方法
     *
     * @param arg：产品的名称
     * @return：相应名称的产品
     */
    public static SimpleProduct factoryMethod(String arg) {
        switch (arg) {
            case SIMPLE_CONCRETE_PRODUCT_A:
                return new ConcreteSimpleProductA();
            case SIMPLE_CONCRETE_PRODUCT_B:
                return new ConcreteSimpleProductB();
            default:
                return null;
        }

    }
}
