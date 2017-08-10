package com.yunlong.samples.design.state.impl.second;

/**
 * Created by shiyunlong on 2017/8/10.
 * 正常状态
 * 点击后变为两倍状态
 */

public class MagnifierNormalState implements IMagnifierState {
    @Override
    public void click(Magnifier magnifier) {
        if (magnifier != null)
            magnifier.setState(new MagnifierTwiceState());
    }

    @Override
    public String display() {
        return "正常状态";
    }
}
