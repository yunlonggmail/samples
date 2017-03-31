package com.yunlong.samples.animation.tween.interpolator;

import android.view.animation.Interpolator;

/**
 * Created by shiyunlong on 2017/3/29.
 */

public class YLInterpolator implements Interpolator {


    @Override
    public float getInterpolation(float input) {
        return (float) Math.sin(input);
    }
}
