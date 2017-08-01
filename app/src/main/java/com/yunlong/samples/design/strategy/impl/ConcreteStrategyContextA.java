package com.yunlong.samples.design.strategy.impl;

/**
 * Created by shiyunlong on 2017/8/1.
 * 环境类
 */

public class ConcreteStrategyContextA extends StrategyContext {

    public ConcreteStrategyContextA() {
        //调用父类构造方法注入策略
        super(new ConcreteStrategyA());
    }
}
