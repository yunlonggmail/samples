package com.yunlong.samples.design.state.impl.second;

/**
 * Created by shiyunlong on 2017/8/10.
 * 四倍状态
 * 点击后变成正常状态
 */

public class Magnifier4TimesState implements IMagnifierState {
    @Override
    public void click(Magnifier magnifier) {
        if (magnifier != null)
            magnifier.setState(new MagnifierNormalState());
    }

    @Override
    public String display() {
        return "四倍状态";
    }
}
