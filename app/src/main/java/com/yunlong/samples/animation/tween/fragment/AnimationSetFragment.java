package com.yunlong.samples.animation.tween.fragment;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.yunlong.lib.base.BaseFragment;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * AlphaAnimation
 */

public class AnimationSetFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.btn_start_java)
    Button btnStartJava;

    @Bind(R.id.btn_start_xml)
    Button btnStartXML;

    @Bind(R.id.iv_logo)
    ImageView ivLogo;

    public AnimationSetFragment() {
        super(R.layout.f_animation_set);
    }

    @Override
    protected void initView() {
        btnStartJava.setOnClickListener(this);
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
            case R.id.btn_start_xml:
                startXML();
                break;
        }
    }

    /**
     * 开始执行Java内部自定义动画
     */
    public void startJava() {
        AnimationSet ivAnimationSet = new AnimationSet(true);
        AnimationSet btnAnimationSet = new AnimationSet(true);

        Animation alphaAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.tween_animation_alpha);
        Animation rotateAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.tween_animation_rotate);
        Animation translateAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.tween_animation_translate);
        Animation scaleAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.tween_animation_scale);

        btnAnimationSet.addAnimation(alphaAnimation);
        btnAnimationSet.addAnimation(rotateAnimation);
        btnAnimationSet.addAnimation(translateAnimation);
        btnAnimationSet.addAnimation(scaleAnimation);

        ivAnimationSet.addAnimation(alphaAnimation);
        ivAnimationSet.addAnimation(rotateAnimation);
        ivAnimationSet.addAnimation(translateAnimation);
        ivAnimationSet.addAnimation(scaleAnimation);

        btnStartJava.startAnimation(btnAnimationSet);
        ivLogo.startAnimation(ivAnimationSet);
    }


    /**
     * 开始执行XML动画
     */
    public void startXML() {
        Animation mAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.tween_animation_set);
        Animation mBtnAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.tween_animation_set);
        ivLogo.startAnimation(mAnimation);
        btnStartXML.startAnimation(mBtnAnimation);
    }
}
