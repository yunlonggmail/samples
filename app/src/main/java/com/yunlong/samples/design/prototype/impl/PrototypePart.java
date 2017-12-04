package com.yunlong.samples.design.prototype.impl;

import com.yunlong.lib.utils.LogUtils;

/**
 * Created by shiyunlong on 2017/12/4.
 * 原型类型的一部分，用于校验深度克隆
 */

public class PrototypePart implements Cloneable {
    /**
     * 原型的类型
     */
    public String part;

    @Override
    public PrototypePart clone() {
        PrototypePart prototypePart = null;
        try {
            prototypePart = (PrototypePart) super.clone();
            prototypePart.part = this.part.concat(" has Cloned！");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            LogUtils.E(PrototypePart.this.getClass().getName(), e.toString());
        }

        return prototypePart;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append("part:");
        sb.append(part);
        sb.append(" ]");
        return sb.toString();
    }
}
