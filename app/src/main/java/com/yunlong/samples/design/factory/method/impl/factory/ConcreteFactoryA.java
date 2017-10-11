package com.yunlong.samples.design.factory.method.impl.factory;

import com.yunlong.samples.design.factory.method.impl.product.ConcreteProductA;
import com.yunlong.samples.design.factory.method.impl.product.Product;

/**
 * Created by shiyunlong on 2017/10/10.
 * 具体产品工厂
 * 它是抽象工厂的子类，实现了抽象工厂中定义的工厂方法，并可由客户端调用，返回一个具体产品类的实例
 */

public class ConcreteFactoryA implements Factory {

    /**
     * 创建一个具体的产品A
     * 该产品是默认型号和默认产品说明
     *
     * @return
     */
    @Override
    public Product createProduct() {
        Product product = new ConcreteProductA();
        return product;
    }

    /**
     * 创建一个具体的产品A，带有具体型号
     *
     * @param i：根据i，设置产品的参数
     * @return
     */
    @Override
    public Product createProduct(int i) {
        Product product = new ConcreteProductA(i);
        return product;
    }

    /**
     * 创建一个具体的产品A，带有产品说明
     *
     * @param str：根据字符串，设置产品的说明
     * @return
     */
    @Override
    public Product createProduct(String str) {
        Product product = new ConcreteProductA(str);
        return product;
    }

    /**
     * 创建一个具体的产品A，带有产品型号和产品说明
     *
     * @param i：根据i，设置产品的参数
     * @param str：根据字符串，设置产品的说明
     * @return
     */
    @Override
    public Product createProduct(int i, String str) {
        Product product = new ConcreteProductA(i, str);
        return product;
    }
}
