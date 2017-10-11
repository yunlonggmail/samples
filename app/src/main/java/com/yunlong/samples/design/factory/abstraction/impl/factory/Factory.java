package com.yunlong.samples.design.factory.abstraction.impl.factory;

import com.yunlong.samples.design.factory.abstraction.impl.product.AbstractProductA;
import com.yunlong.samples.design.factory.abstraction.impl.product.AbstractProductB;

/**
 * Created by shiyunlong on 2017/10/11.
 * 抽象工厂角色
 * 它声明了用于创建一族产品的方法，每一个方法对应一种产品
 */

public interface Factory {
    /**
     * 创建A类产品
     */
    AbstractProductA createProductA();

    /**
     * 创建B类产品
     *
     * @return
     */
    AbstractProductB createProductB();
}
