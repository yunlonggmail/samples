package com.yunlong.samples.animation.property.fragment;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.yunlong.lib.base.BaseFragment;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * 属性集合和关键帧
 */

public class PKFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.btn_pk_p)
    Button btnPkP;

    @Bind(R.id.btn_pk_k)
    Button btnPkK;

    @Bind(R.id.btn_start_xml)
    Button btnStartXML;

    @Bind(R.id.iv_logo)
    ImageView ivLogo;

    public PKFragment() {
        super(R.layout.f_animator_pk);
    }

    @Override
    protected void initView() {
        btnPkP.setOnClickListener(this);
        btnPkK.setOnClickListener(this);
        btnStartXML.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pk_k:
                startPkK();
                break;
            case R.id.btn_pk_p:
                startPkP();
                break;
            case R.id.btn_start_xml:
                startXML();
                break;
        }
    }

    /**
     * 使用PropertyValueHolder设置动画
     */
    public void startPkP() {
        PropertyValuesHolder propertyXValuesHolder = PropertyValuesHolder.ofFloat("x", 0, 100f);
        PropertyValuesHolder propertyRotationXValuesHolder = PropertyValuesHolder.ofFloat("rotationX", 0, 360f);
        PropertyValuesHolder propertyScaleXValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.5f);
        PropertyValuesHolder propertyAlphaValuesHolder = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.5f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(btnPkP, propertyXValuesHolder, propertyAlphaValuesHolder, propertyRotationXValuesHolder, propertyScaleXValuesHolder);
        objectAnimator.setDuration(3000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }

    /**
     * 使用KeyFrame设置动画
     */
    public void startPkK() {
        Keyframe kf1 = Keyframe.ofFloat(0, 0);
        kf1.setInterpolator(new LinearInterpolator());
        Keyframe kf2 = Keyframe.ofFloat(0.5f, 360);
        kf2.setInterpolator(new BounceInterpolator());
        Keyframe kf7 = Keyframe.ofFloat(1f, 180);
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofKeyframe("rotationX", kf1, kf2, kf7);

        Keyframe kf3 = Keyframe.ofFloat(0, 1.0f);
        Keyframe kf4 = Keyframe.ofFloat(1, 0.5f);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofKeyframe("scaleX", kf3, kf4);

        Keyframe kf5 = Keyframe.ofFloat(0, 1);
        Keyframe kf6 = Keyframe.ofFloat(1, 0.5f);
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofKeyframe("alpha", kf5, kf6);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(btnPkK, pvh1, pvh2, pvh3);
        objectAnimator.setDuration(3000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();

    }

    /**
     * 开始执行XML动画
     */
    public void startXML() {
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(mActivity, Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? R.animator.animator_21 : R.animator.animator);
        animatorSet.setTarget(ivLogo);
        animatorSet.start();
    }
}
