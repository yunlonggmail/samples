package com.yunlong.samples.design.state.impl.second;

/**
 * Created by shiyunlong on 2017/8/10.
 * 放大镜状态
 */

public interface IMagnifierState {
    /**
     * 点击
     */
    void click(Magnifier magnifier);

    /**
     * 显示
     */
    String display();
}
