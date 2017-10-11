package com.yunlong.samples.design.factory.method.impl.product;

/**
 * Created by shiyunlong on 2017/10/10.
 * 抽象产品
 * 它定义产品的接口，是工厂方法模式所创建对象的超类型，也就是产品对象的公共父类。
 */

public interface Product {
    /**
     * 产品的抽象方法，由具体产品实现，每个产品的具体实现可以不一致
     *
     * @return
     */
    String doSomething();
}
