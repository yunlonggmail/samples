package com.yunlong.samples.design.state.impl.second;

/**
 * Created by shiyunlong on 2017/8/10.
 * 两倍状态
 * 点击后变成4倍状态
 */

public class MagnifierTwiceState implements IMagnifierState {
    @Override
    public void click(Magnifier magnifier) {
        if (magnifier != null)
            magnifier.setState(new Magnifier4TimesState());
    }

    @Override
    public String display() {
        return "两倍状态";
    }
}
