package com.yunlong.samples.animation.tween.fragment;

import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yunlong.lib.base.BaseFragment;
import com.yunlong.lib.utils.DisplayUtils;
import com.yunlong.lib.utils.StringsUtils;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * AlphaAnimation
 */

public class InterpolatorSetFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.et_num)
    EditText etNum;

    @Bind(R.id.btn_start_anim)
    Button btnStartAnim;


    @Bind(R.id.tv_1)
    TextView tv1;

    @Bind(R.id.tv_2)
    TextView tv2;

    @Bind(R.id.tv_3)
    TextView tv3;

    @Bind(R.id.tv_4)
    TextView tv4;

    @Bind(R.id.tv_5)
    TextView tv5;

    @Bind(R.id.tv_6)
    TextView tv6;

    @Bind(R.id.tv_7)
    TextView tv7;

    @Bind(R.id.tv_8)
    TextView tv8;

    @Bind(R.id.tv_9)
    TextView tv9;

    @Bind(R.id.tv_10)
    TextView tv10;

    @Bind(R.id.tv_11)
    TextView tv11;

    @Bind(R.id.tv_12)
    TextView tv12;

    @Bind(R.id.v_right)
    View vRight;

    public InterpolatorSetFragment() {
        super(R.layout.f_animation_interpolator);
    }

    @Override
    protected void initView() {
        btnStartAnim.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_anim:
                checkNum();
                break;
        }
    }

    /**
     * 检查Num
     */
    private void checkNum() {
        String numStr = etNum.getText().toString();
        if (StringsUtils.isEmpty(numStr)) {
            startAll();
            return;
        }
        int num = Integer.parseInt(numStr);

        switch (num) {
            case 1:
                start1();
                break;
            case 2:
                start2();
                break;
            case 3:
                start3();
                break;
            case 4:
                start4();
                break;
            case 5:
                start5();
                break;
            case 6:
                start6();
                break;
            case 7:
                start7();
                break;
            case 8:
                start8();
                break;
            case 9:
                start9();
                break;
            case 10:
                start10();
                break;
            case 11:
                start11();
                break;
            case 12:
                start12();
                break;
            default:
                startAll();
        }
    }

    /**
     * 开启所有动画
     */
    private void startAll() {
        start1();
        start2();
        start3();
        start4();
        start5();
        start6();
        start7();
        start8();
        start9();
        start10();
        start11();
        start12();
    }

    /**
     * 开启1动画
     */
    private void start1() {
        Animation animation = getAnimation();
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        tv1.startAnimation(animation);
    }

    /**
     * 开启1动画
     */
    private void start2() {
        Animation animation = getAnimation();
        animation.setInterpolator(new AccelerateInterpolator());
        tv2.startAnimation(animation);
    }

    /**
     * 开启1动画
     */
    private void start3() {
        Animation animation = getAnimation();
        animation.setInterpolator(new AnticipateInterpolator());
        tv3.startAnimation(animation);
    }

    /**
     * 开启1动画
     */
    private void start4() {
        Animation animation = getAnimation();
        animation.setInterpolator(new AnticipateOvershootInterpolator());
        tv4.startAnimation(animation);
    }

    /**
     * 开启1动画
     */
    private void start5() {
        Animation animation = getAnimation();
        animation.setInterpolator(new BounceInterpolator());
        tv5.startAnimation(animation);
    }

    /**
     * 开启1动画
     */
    private void start6() {
        int dis = vRight.getLeft()-tv6.getLeft();
        TranslateAnimation translateAnimation = new TranslateAnimation(0, dis, 0, 0);
        translateAnimation.setDuration(3000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new CycleInterpolator(1));
        tv6.startAnimation(translateAnimation);
    }

    /**
     * 开启1动画
     */
    private void start7() {
        Animation animation = getAnimation();
        animation.setInterpolator(new DecelerateInterpolator());
        tv7.startAnimation(animation);
    }

    /**
     * 开启1动画
     */
    private void start8() {
        Animation animation = getAnimation();
        animation.setInterpolator(new LinearInterpolator());
        tv8.startAnimation(animation);
    }

    /**
     * 开启1动画
     */
    private void start9() {
        Animation animation = getAnimation();
        animation.setInterpolator(new OvershootInterpolator());
        tv9.startAnimation(animation);
    }

    /**
     * 开启1动画
     */
    private void start10() {
        Animation animation = getAnimation();
        animation.setInterpolator(new FastOutLinearInInterpolator());
        tv10.startAnimation(animation);
    }

    /**
     * 开启1动画
     */
    private void start11() {
        Animation animation = getAnimation();
        animation.setInterpolator(new FastOutSlowInInterpolator());
        tv11.startAnimation(animation);
    }

    /**
     * 开启1动画
     */
    private void start12() {
        Animation animation = getAnimation();
        animation.setInterpolator(new LinearOutSlowInInterpolator());
        tv12.startAnimation(animation);
    }

    private Animation getAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, DisplayUtils.getScreenWidth() - 300, 0, 0);
        translateAnimation.setDuration(3000);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

}
