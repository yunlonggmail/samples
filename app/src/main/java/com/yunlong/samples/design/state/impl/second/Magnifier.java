package com.yunlong.samples.design.state.impl.second;

/**
 * Created by shiyunlong on 2017/8/10.
 * 放大镜
 * 有三种状态：
 * 1、正常状态
 * 2、二倍状态
 * 3、四倍状态
 */

public class Magnifier {
    /**
     * 当前状态
     */
    private IMagnifierState mCurrentState = new MagnifierNormalState();

    /**
     * 点击
     */
    public void click() {
        if (mCurrentState != null)
            mCurrentState.click(this);
    }

    /**
     * 设置状态
     *
     * @param iMagnifierState
     */
    public void setState(IMagnifierState iMagnifierState) {
        this.mCurrentState = iMagnifierState;
    }

    @Override
    public String toString() {
        return "当前状态：" + mCurrentState.display();
    }
}
