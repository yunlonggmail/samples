package com.yunlong.samples.design.factory.method.impl.product;

import com.yunlong.lib.utils.StringsUtils;

/**
 * Created by shiyunlong on 2017/10/10.
 * 具体产品角色
 * 它实现了抽象产品接口，某种类型的具体产品有专门的具体工厂创建，具体工厂和具体产品之间一一对应。
 */

public class ConcreteProductA implements Product {
    /**
     * 产品的型号
     */
    public int model = -1;
    /**
     * 产品的说明
     */
    public String desc = "";

    /**
     * 默认的产品构造函数
     */
    public ConcreteProductA() {
        model = 1;
        desc = "默认产品说明";
    }

    /**
     * 带有型号的构造参数
     * 说明为默认产品说明
     *
     * @param i：型号为i
     */
    public ConcreteProductA(int i) {
        model = i;
        desc = "默认产品说明";
    }

    /**
     * 带有产品说明的构造参数
     * 默认产品型号
     *
     * @param str：产品说明
     */
    public ConcreteProductA(String str) {
        model = 1;
        desc = str;
    }

    /**
     * 带有产品型号和产品说明的构造参数
     *
     * @param i        :产品型号
     * @param str：产品说明
     */
    public ConcreteProductA(int i, String str) {
        model = i;
        desc = str;
    }


    @Override
    public String doSomething() {
        StringBuilder sb = new StringBuilder();
        sb.append("这是具体产品A\n");
        if (model > 0) {
            sb.append("产品型号：" + model);
            if(model==1){
                sb.append("(默认型号)");
            }
            sb.append("\n");
        }
        //判断产品说明是否为空
        if (!StringsUtils.isEmpty(desc)) {
            sb.append("产品说明：" + desc);
            sb.append("\n");
        }
        return sb.toString();
    }
}
