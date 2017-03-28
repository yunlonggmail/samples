package com.yunlong.samples.animation.drawable;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * 帧动画界面
 */

public class DrawableAnimationActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.animation.DrawableAnimation";

    @Bind(R.id.iv_rotate)
    ImageView ivRotate;

    @Bind(R.id.btn_start_xml)
    Button btnStartXML;

    @Bind(R.id.iv_xml)
    ImageView ivXML;

    @Bind(R.id.btn_start_java)
    Button btnStartJava;

    @Bind(R.id.iv_java)
    ImageView ivJava;

    @Override
    protected int getResourceId() {
        return R.layout.a_drawable_animation;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_drawable_animation);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnStartXML.setOnClickListener(this);
        btnStartJava.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ivRotate.setImageResource(R.drawable.drawable_anim_rotate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_xml:
                startXML();
                break;
            case R.id.btn_start_java:
                startJava();
                break;
        }
    }

    private void startXML() {
        ivXML.setBackgroundResource(R.drawable.drawable_frame_anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) ivXML.getBackground();
        animationDrawable.start();
    }

    private void startJava() {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.d0), 100);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.d1), 100);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.d2), 100);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.d3), 100);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.d4), 100);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.d5), 100);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.d6), 100);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.d7), 100);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.d8), 100);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.d9), 100);
        animationDrawable.setOneShot(false);
        ivJava.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();
    }
}
