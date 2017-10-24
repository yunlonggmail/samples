package com.yunlong.samples.design.builder.impl;

/**
 * Created by shiyunlong on 2017/10/24.
 * 抽象建造者
 * 它为创建一个产品Product的各个部件指定抽象接口，在该接口中一般声明两类方法，
 * 一类方法是buildPartX()，他们拥有创建复杂对象的各个部件；
 * 另一类方法是getResult()，它们拥有返回复杂对象。
 * Builder既可以是抽象类，也可以是接口
 */

public interface Builder {

    Product product = new Product();

    /**
     * 创建A部分
     */
    void buildPartA();

    /**
     * 创建B部分
     */
    void buildPartB();

    /**
     * 创建C部分
     */
    void buildPartC();

    /**
     * 获取具体的产品
     */
    Product getResult();
}
