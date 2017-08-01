package com.yunlong.samples.design.strategy.impl;

/**
 * Created by shiyunlong on 2017/8/1.
 * 具体策略实现类A
 */

public class ConcreteStrategyB implements Strategy {

    @Override
    public void algorithm() {
        System.out.print("策略具体实现类-B：实现了算法B");
    }
}
