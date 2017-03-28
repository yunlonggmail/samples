package com.yunlong.samples.animation.tween.fragment;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.yunlong.lib.base.BaseFragment;
import com.yunlong.lib.utils.StringsUtils;
import com.yunlong.lib.utils.ToastUtils;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * AlphaAnimation
 */

public class TranslateAnimationFragment extends BaseFragment implements View.OnClickListener {

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

    @Bind(R.id.et_from_x_delta)
    EditText etFromXDelta;

    @Bind(R.id.et_from_y_delta)
    EditText etFromYDelta;

    @Bind(R.id.et_to_x_delta)
    EditText etToXDelta;

    @Bind(R.id.et_to_y_delta)
    EditText etToYDelta;

    @Bind(R.id.rb_from_x_absolute)
    RadioButton rbFromXAbsolute;

    @Bind(R.id.rb_from_x_self)
    RadioButton rbFromXSelf;

    @Bind(R.id.rb_from_x_parent)
    RadioButton rbFromXParent;

    @Bind(R.id.rb_from_y_absolute)
    RadioButton rbFromYAbsolute;

    @Bind(R.id.rb_from_y_self)
    RadioButton rbFromYSelf;

    @Bind(R.id.rb_from_y_parent)
    RadioButton rbFromYParent;

    @Bind(R.id.rb_to_x_absolute)
    RadioButton rbToXAbsolute;

    @Bind(R.id.rb_to_x_self)
    RadioButton rbToXSelf;

    @Bind(R.id.rb_to_x_parent)
    RadioButton rbToXParent;

    @Bind(R.id.rb_to_y_absolute)
    RadioButton rbToYAbsolute;

    @Bind(R.id.rb_to_y_self)
    RadioButton rbToYSelf;

    @Bind(R.id.rb_to_y_parent)
    RadioButton rbToYParent;

    @Bind(R.id.rb_constructor_4)
    RadioButton rbConstructor4;

    @Bind(R.id.rb_constructor_8)
    RadioButton rbConstructor8;


    public TranslateAnimationFragment() {
        super(R.layout.f_animation_tween_translate);
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
        float fromXDelta = StringsUtils.isEmpty(etFromXDelta.getText()) ? 0 : Float.parseFloat(etFromXDelta.getText().toString());
        float fromYDelta = StringsUtils.isEmpty(etFromYDelta.getText()) ? 0 : Float.parseFloat(etFromYDelta.getText().toString());
        float toXDelta;
        float toYDelta;

        int fromXType;
        if (rbFromXSelf.isChecked()) {
            fromXType = Animation.RELATIVE_TO_SELF;
        } else if (rbFromXParent.isChecked()) {
            fromXType = Animation.RELATIVE_TO_PARENT;
        } else {
            fromXType = Animation.ABSOLUTE;
        }

        int fromYType;
        if (rbFromYSelf.isChecked()) {
            fromYType = Animation.RELATIVE_TO_SELF;
        } else if (rbFromYParent.isChecked()) {
            fromYType = Animation.RELATIVE_TO_PARENT;
        } else {
            fromYType = Animation.ABSOLUTE;
        }

        int toXType;
        if (rbToXSelf.isChecked()) {
            toXType = Animation.RELATIVE_TO_SELF;
            toXDelta = StringsUtils.isEmpty(etToXDelta.getText()) ? 1 : Float.parseFloat(etToXDelta.getText().toString());
        } else if (rbToXParent.isChecked()) {
            toXType = Animation.RELATIVE_TO_PARENT;
            toXDelta = StringsUtils.isEmpty(etToXDelta.getText()) ? 1 : Float.parseFloat(etToXDelta.getText().toString());
        } else {
            toXType = Animation.ABSOLUTE;
            toXDelta = StringsUtils.isEmpty(etToXDelta.getText()) ? 100 : Float.parseFloat(etToXDelta.getText().toString());
        }

        int toYType;
        if (rbToYSelf.isChecked()) {
            toYType = Animation.RELATIVE_TO_SELF;
            toYDelta = StringsUtils.isEmpty(etToYDelta.getText()) ? 1 : Float.parseFloat(etToYDelta.getText().toString());
        } else if (rbToYParent.isChecked()) {
            toYType = Animation.RELATIVE_TO_PARENT;
            toYDelta = StringsUtils.isEmpty(etToYDelta.getText()) ? 1 : Float.parseFloat(etToYDelta.getText().toString());
        } else {
            toYType = Animation.ABSOLUTE;
            toYDelta = StringsUtils.isEmpty(etToYDelta.getText()) ? 100 : Float.parseFloat(etToYDelta.getText().toString());
        }
        TranslateAnimation translateAnimation = null;
        if (rbConstructor4.isChecked()) {
            translateAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        } else if (rbConstructor8.isChecked()) {
            translateAnimation = new TranslateAnimation(fromXType, fromXDelta, toXType, toXDelta, fromYType, fromYDelta, toYType, toYDelta);
        }
        if (translateAnimation == null) {
            ToastUtils.show(mActivity, "请选择构造方法");
            return;
        }

        Long duration = StringsUtils.isEmpty(etDuration.getText()) ? 3000 : Long.parseLong(etDuration.getText().toString());
        translateAnimation.setDuration(duration);

        Long startOffset = StringsUtils.isEmpty(etStartOffset.getText()) ? 1000 : Long.parseLong(etStartOffset.getText().toString());
        translateAnimation.setDuration(startOffset);

        int repeatMode = rbReverse.isChecked() ? Animation.REVERSE : Animation.RESTART;
        translateAnimation.setRepeatMode(repeatMode);

        int repeatCount = StringsUtils.isEmpty(etRepeatCount.getText()) ? 0 : Integer.parseInt(etRepeatCount.getText().toString());
        translateAnimation.setRepeatCount(repeatCount);

        translateAnimation.setFillAfter(cbFillAfter.isChecked());
        translateAnimation.setFillBefore(cbFillBefore.isChecked());
        translateAnimation.setFillEnabled(cbFillEnabled.isChecked());

        ivLogo.startAnimation(translateAnimation);
        btnStartJava.startAnimation(translateAnimation);
    }


    /**
     * 开始执行XML动画
     */
    public void startXML() {
        Animation mAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.tween_animation_translate);
        ivLogo.startAnimation(mAnimation);
        btnStartXML.startAnimation(mAnimation);
    }
}
