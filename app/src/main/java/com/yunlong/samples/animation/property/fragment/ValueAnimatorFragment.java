package com.yunlong.samples.animation.property.fragment;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
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

public class ValueAnimatorFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.btn_of_int)
    Button btnOfInt;

    @Bind(R.id.btn_of_float)
    Button btnOfFloat;

    @Bind(R.id.btn_of_argb)
    Button btnOfArgb;

    @Bind(R.id.btn_start_xml)
    Button btnStartXML;

    @Bind(R.id.iv_logo)
    ImageView ivLogo;

    public ValueAnimatorFragment() {
        super(R.layout.f_animator_value);
    }

    @Override
    protected void initView() {
        btnOfInt.setOnClickListener(this);
        btnOfFloat.setOnClickListener(this);
        btnOfArgb.setOnClickListener(this);
        btnStartXML.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_of_argb:
                startOfArgb();
                break;
            case R.id.btn_of_float:
                startOfFloat();
                break;
            case R.id.btn_of_int:
                startOfInt();
                break;
            case R.id.btn_start_xml:
                startXML();
                break;
        }
    }

    /**
     * 设置控件的背景色，前景色
     */
    public void startOfArgb() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return;

        //起始颜色为红色
        int startColor = 0xffff0000;
        //终止颜色为绿色
        int endColor = 0xff0000ff;

        ValueAnimator valueAnimator = ValueAnimator.ofArgb(startColor, endColor);
        valueAnimator.setTarget(btnOfArgb);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                btnOfArgb.setBackgroundColor(color);
            }
        });
        valueAnimator.start();
    }

    /**
     * 可以用于设置控件的属性，如果X,Y等
     */
    public void startOfInt() {
        int end = 10;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, end);
        valueAnimator.setTarget(btnOfArgb);
        valueAnimator.setDuration(end * 1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int num = (int) animation.getAnimatedValue();
                btnOfInt.setText(mActivity.getResources().getString(R.string.a_animator_btn_int, num));
            }
        });
        valueAnimator.start();
    }

    /**
     * 可以用于设置控件的属性，如果X,Y等
     */
    public void startOfFloat() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f, 2.0f, 1.0f, 0.5f);
        valueAnimator.setTarget(btnOfArgb);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float num = (float) animation.getAnimatedValue();
                btnOfFloat.setScaleX(num);
                btnOfFloat.setText(mActivity.getResources().getString(R.string.a_animator_btn_float, num));
            }
        });
        valueAnimator.start();
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
