package com.yunlong.samples.design.state.impl.first;

/**
 * Created by shiyunlong on 2017/8/10.
 * 环境类：电视
 * <p>
 * 执行某种操作之后进行状态变化
 * <p>
 * 相应的操作有
 * 点击电源按钮：如果开机就关机，如果关机就开机
 * 点击调大声音按钮：加大声音
 * 点击调小声音按钮：调小声音
 * 点击上一节目按钮：上一节目
 * 点击下一节目按钮：下一节目
 */

public class TV {
    /**
     * 声音
     */
    private int mSound = 1;
    /**
     * 电视节目频道
     */
    private int mShowChannel = 1;
    /**
     * 当前状态
     */
    private ITVState mCurrentState;
    /**
     * 电源打开状态
     */
    private ITVState mPowerOnState;
    /**
     * 电源关闭状态
     */
    private ITVState mPowerOffState;

    public TV() {
        mPowerOnState = new TVPowerOnState(this);
        mPowerOffState = new TVPowerOffState();
        mCurrentState = mPowerOffState;
    }

    /**
     * 点击电源
     */
    public void powerTV() {
        if (mCurrentState == mPowerOffState) {
            mCurrentState = mPowerOnState;
            System.out.println("电视开机啦。。。");
        } else {
            mCurrentState = mPowerOffState;
            System.out.println("电视关键啦。。。");
        }
    }

    /**
     * 调大声音
     */
    public void turnUpSound() {
        if (mCurrentState != null)
            mCurrentState.turnUp();
    }

    /**
     * 调小声音
     */
    public void turnDownShound() {
        if (mCurrentState != null)
            mCurrentState.turnDown();
    }

    /**
     * 上一节目
     */
    public void preShow() {
        if (mCurrentState != null)
            mCurrentState.preShow();
    }

    /**
     * 下一节目
     */
    public void nextShow() {
        if (mCurrentState != null)
            mCurrentState.nextShow();
    }

    /**
     * 设置声音
     *
     * @param sound
     */
    public void setSound(int sound) {
        this.mSound = sound;
        System.out.print("当前电视声音是：" + mSound);
    }

    /**
     * 获取声音
     */
    public int getSound() {
        return mSound;
    }

    /**
     * 设置节目
     */
    public void setShowChannel(int showChannel) {
        this.mShowChannel = showChannel;
        System.out.print("当前电视节目是：" + mShowChannel);
    }

    /**
     * 获取当前频道
     */
    public int getChannel() {
        return mShowChannel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("状态：");
        sb.append(mCurrentState);
        sb.append("\n");
        if (mCurrentState == mPowerOnState) {
            sb.append("当前频道：");
            sb.append(mShowChannel);
            sb.append("\n");
            sb.append("当前声音：");
            sb.append(mSound);
            sb.append("\n");
        }
        return sb.toString();
    }
}
