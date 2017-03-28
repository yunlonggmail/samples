package com.yunlong.samples.animation.tween.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yunlong.lib.base.BaseFragment;
import com.yunlong.samples.R;
import com.yunlong.samples.animation.tween.anim.Rotate3DAnimation;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/27.
 */

public class AnimationFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.btn_start_java)
    Button btnStartJava;

    @Bind(R.id.btn_start_xml)
    Button btnStartXML;

    @Bind(R.id.iv_logo)
    ImageView ivLogo;

    public AnimationFragment() {
        super(R.layout.f_animation);
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

    private void startJava() {
        Rotate3DAnimation rotate3DAnimation = new Rotate3DAnimation(0, 360);
        rotate3DAnimation.setDuration(3000);
        rotate3DAnimation.setFillAfter(true);
        btnStartJava.startAnimation(rotate3DAnimation);
        ivLogo.startAnimation(rotate3DAnimation);
    }

    private void startXML() {

    }
}
