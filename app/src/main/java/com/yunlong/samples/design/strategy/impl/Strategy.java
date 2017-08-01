package com.yunlong.samples.design.strategy.impl;

/**
 * Created by shiyunlong on 2017/8/1.
 * 抽象的策略类
 * 将具有不同同算法的策略抽象出来
 * 也就是将变化的代码抽象出来
 */

public interface Strategy {
    /**
     * 算法
     * <p>
     * 每个策略都需要实现的方法
     */
    void algorithm();
}
