package com.yunlong.samples.animation.property.fragment;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yunlong.lib.base.BaseFragment;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * AlphaAnimation
 */

public class AnimatorFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.btn_start_java)
    Button btnStartJava;

    @Bind(R.id.btn_start_set)
    Button btnStartSet;

    @Bind(R.id.btn_start_xml)
    Button btnStartXML;

    @Bind(R.id.iv_logo)
    ImageView ivLogo;

    public AnimatorFragment() {
        super(R.layout.f_animator);
    }

    @Override
    protected void initView() {
        btnStartJava.setOnClickListener(this);
        btnStartSet.setOnClickListener(this);
        btnStartXML.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_java:
                startJava();
                break;
            case R.id.btn_start_set:
                startSet();
                break;
            case R.id.btn_start_xml:
                startXML();
                break;
        }
    }

    /**
     * 开始执行Java内部自定义动画
     */
    public void startJava() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btnStartJava, "rotationY", 0.0f, 360F);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }

    /**
     * 开始执行动画集合
     */
    public void startSet() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator rotationYAnimator = ObjectAnimator.ofFloat(btnStartSet, "rotationY", 0, 360);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(btnStartSet, "alpha", 1.0f, 0.5f);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(btnStartSet, "scaleX", 1.0f, 0.5f);
        animatorSet.playTogether(rotationYAnimator, alphaAnimator, scaleXAnimator);
        animatorSet.setDuration(3000);
        animatorSet.start();
    }


    /**
     * 开始执行XML动画
     */
    public void startXML() {
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(mActivity, Build.VERSION.SDK_INT >= 21 ? R.animator.animator_21 : R.animator.animator);
        animatorSet.setTarget(ivLogo);
        animatorSet.start();
    }
}
