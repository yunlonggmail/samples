package com.yunlong.samples.design.factory.abstraction.impl.product;

/**
 * Created by shiyunlong on 2017/10/11.
 * 具体产品角色
 * 它定义具体产品工厂生产的具体产品对象，实现抽象产品接口中声明的业务方法
 */

public class ConcreteProductB1 implements AbstractProductB {
    /**
     * B类产品的事情
     *
     * @return
     */
    @Override
    public String doSthB() {
        return "B类产品1";
    }
}
