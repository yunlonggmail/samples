package com.yunlong.samples.design.factory.abstraction.impl.factory;

import com.yunlong.samples.design.factory.abstraction.impl.product.AbstractProductA;
import com.yunlong.samples.design.factory.abstraction.impl.product.AbstractProductB;
import com.yunlong.samples.design.factory.abstraction.impl.product.ConcreteProductA1;
import com.yunlong.samples.design.factory.abstraction.impl.product.ConcreteProductB1;

/**
 * Created by shiyunlong on 2017/10/11.
 * 具体产品角色
 * 他实现了在抽象工厂中声明的创建产品的方法，生成一组具体的产品，这些产品构成了一个产品族，每一个产品都位于某个产品等级结构中
 */

public class ConcreteFactory1 implements Factory {
    /**
     * 创建A1类产品
     *
     * @return
     */
    @Override
    public AbstractProductA createProductA() {
        return new ConcreteProductA1();
    }

    /**
     * 创建B1类产品
     *
     * @return
     */
    @Override
    public AbstractProductB createProductB() {
        return new ConcreteProductB1();
    }
}
