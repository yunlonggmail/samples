package com.yunlong.samples.design.state.impl.first;

/**
 * Created by shiyunlong on 2017/8/10.
 * 开机状态
 * 可以调大电视声音直到100
 * 可以调小电视声音直到1
 * 可以下一电视节目：下一节目直到100然后切换到1
 * 可以前一电视节目：前一节目直到1然后切换到100
 */

public class TVPowerOnState implements ITVState {
    /**
     * 电视
     */
    private TV mTV;

    public TVPowerOnState(TV tv) {
        this.mTV = tv;
    }

    @Override
    public void turnUp() {
        int sound = mTV.getSound();
        if (sound < 100) {
            sound++;
            mTV.setSound(sound);
        } else
            System.out.println("电视声音已经最大了");

    }

    @Override
    public void turnDown() {
        int sound = mTV.getSound();
        if (sound > 0) {
            sound--;
            mTV.setSound(sound);
        } else
            System.out.println("电视已经静音了");

    }

    @Override
    public void preShow() {
        int channel = mTV.getChannel();
        if (channel == 1) {
            channel = 100;
        } else {
            channel--;
        }
        mTV.setShowChannel(channel);
    }

    @Override
    public void nextShow() {
        int channel = mTV.getChannel();
        if (channel == 100) {
            channel = 1;
        } else {
            channel++;
        }
        mTV.setShowChannel(channel);
    }

    @Override
    public String toString() {
        return "开机状态";
    }
}
