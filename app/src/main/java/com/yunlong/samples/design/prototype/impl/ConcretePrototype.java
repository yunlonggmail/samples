package com.yunlong.samples.design.prototype.impl;

import com.yunlong.lib.utils.LogUtils;

/**
 * Created by shiyunlong on 2017/12/4.
 * 具体的原型类，这个类进行简单的深克隆
 */

public class ConcretePrototype implements Prototype {
    /**
     * 名称
     */
    public String name;
    /**
     * 数字
     */
    public int num;
    /**
     * 什么
     */
    public boolean what;
    /**
     * 部分
     */
    public PrototypePart prototypePart;

    @Override
    public Prototype clone() {
        ConcretePrototype concretePrototype = null;
        try {
            concretePrototype = (ConcretePrototype) super.clone();
            concretePrototype.name = this.name.concat(" has Cloned！");
            concretePrototype.prototypePart = this.prototypePart.clone();
        } catch (Exception e) {
            LogUtils.E(ConcretePrototype.this.getClass().getName(), e.toString());
        }


        return concretePrototype;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        sb.append("\n");
        sb.append("name:");
        sb.append(name);
        sb.append("\n");
        sb.append("num:");
        sb.append(num);
        sb.append("\n");
        sb.append("what:");
        sb.append(what);
        sb.append("\n");
        sb.append("prototypePart：");
        sb.append(prototypePart.toString());
        sb.append("\n");
        sb.append("}");
        sb.append("\n");
        return sb.toString();
    }
}
