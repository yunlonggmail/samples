package com.yunlong.samples.animation.tween.fragment;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.yunlong.lib.base.BaseFragment;
import com.yunlong.lib.utils.StringsUtils;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * AlphaAnimation
 */

public class AlphaAnimationFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.btn_start_java)
    Button btnStartJava;

    @Bind(R.id.btn_start_xml)
    Button btnStartXML;

    @Bind(R.id.iv_logo)
    ImageView ivLogo;

    @Bind(R.id.et_duration)
    EditText etDuration;

    @Bind(R.id.et_start_offset)
    EditText etStartOffset;

    @Bind(R.id.rb_reverse)
    RadioButton rbReverse;

    @Bind(R.id.rb_restart)
    RadioButton rbRestart;

    @Bind(R.id.et_repeat_count)
    EditText etRepeatCount;

    @Bind(R.id.cb_fill_before)
    CheckBox cbFillBefore;

    @Bind(R.id.cb_fill_after)
    CheckBox cbFillAfter;

    @Bind(R.id.cb_fill_enabled)
    CheckBox cbFillEnabled;

    @Bind(R.id.et_from_alpha)
    EditText etFromAlpha;

    @Bind(R.id.et_to_alpha)
    EditText etToAlpha;


    public AlphaAnimationFragment() {
        super(R.layout.f_animation_tween_alpha);
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
        float fromAlpha = StringsUtils.isEmpty(etFromAlpha.getText()) ? 1 : processAlpha(Float.parseFloat(etFromAlpha.getText().toString()));
        float toAlpha = StringsUtils.isEmpty(etToAlpha.getText()) ? 0 : processAlpha(Float.parseFloat(etToAlpha.getText().toString()));
        AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);

        Long duration = StringsUtils.isEmpty(etDuration.getText()) ? 3000 : Long.parseLong(etDuration.getText().toString());
        alphaAnimation.setDuration(duration);

        Long startOffset = StringsUtils.isEmpty(etStartOffset.getText()) ? 1000 : Long.parseLong(etStartOffset.getText().toString());
        alphaAnimation.setDuration(startOffset);

        int repeatMode = rbReverse.isChecked() ? Animation.REVERSE : Animation.RESTART;
        alphaAnimation.setRepeatMode(repeatMode);

        int repeatCount = StringsUtils.isEmpty(etRepeatCount.getText()) ? 0 : Integer.parseInt(etRepeatCount.getText().toString());
        alphaAnimation.setRepeatCount(repeatCount);

        alphaAnimation.setFillAfter(cbFillAfter.isChecked());
        alphaAnimation.setFillBefore(cbFillBefore.isChecked());
        alphaAnimation.setFillEnabled(cbFillEnabled.isChecked());

        ivLogo.startAnimation(alphaAnimation);
        btnStartJava.startAnimation(alphaAnimation);
    }

    /**
     * 处理透明度
     */
    public float processAlpha(float alpha) {
        if (alpha < 0)
            return 0;
        while (alpha > 1) {
            alpha--;
        }
        return alpha;
    }

    /**
     * 开始执行XML动画
     */
    public void startXML() {
        Animation mAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.tween_animation_alpha);
        ivLogo.startAnimation(mAnimation);
        btnStartXML.startAnimation(mAnimation);
    }
}
