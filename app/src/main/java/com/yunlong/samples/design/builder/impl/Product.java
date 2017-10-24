package com.yunlong.samples.design.builder.impl;

/**
 * Created by shiyunlong on 2017/10/24.
 * 产品角色
 * 它是被构建的复杂对象，包含多个组成部件，具体创建者创建该产品的内部表示并定义它的装配过程
 */

public class Product {
    /**
     * A部分，使用String简单表示类型
     */
    private String partA;
    /**
     * B部分，使用String简单表示类型
     */
    private String partB;
    /**
     * C部分，使用String简单表示类型
     */
    private String partC;

    /**
     * set方法，设置PartA
     *
     * @param partA
     */
    public void setPartA(String partA) {
        this.partA = partA;
    }

    /**
     * set方法，设置PartB
     *
     * @param partB
     */
    public void setPartB(String partB) {
        this.partB = partB;
    }

    /**
     * set方法，设置PartC
     *
     * @param partC
     */
    public void setPartC(String partC) {
        this.partC = partC;
    }

    @Override
    public String toString() {
        return "【PartA：" + partA + "\n"
                + "PartB：" + partB + "\n"
                + "PartC：" + partC + "\n】";
    }
}
