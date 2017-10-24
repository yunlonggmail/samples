package com.yunlong.samples.design.builder.impl;

/**
 * Created by shiyunlong on 2017/10/24.
 * 指挥者
 * 指挥者又称为导演类，它负责安排复杂对象的建造次序，指挥者与抽象建造者之间存在关联关系，
 * 可以在其construct()建造方法中调用建造者对象的部件构造和装配方法，完成复杂对象的建造。
 * 客户端一般只需要与指挥者进行交互，在客户端确定具体建造者类型，并实例化具体建造者对象，
 * 然后通过指挥者类的构造函数或者Setter方法将该对象传入指挥者类中。
 */

public class Director {

    private Builder mBuilder;

    public Director(Builder builder) {
        mBuilder = builder;
    }

    /**
     * 具体的指挥方法
     *
     * @return
     */
    public Product construct() {
        mBuilder.buildPartA();
        mBuilder.buildPartB();
        mBuilder.buildPartC();
        return mBuilder.getResult();
    }

}
