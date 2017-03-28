package com.yunlong.samples.animation.tween.fragment;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
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

public class ScaleAnimationFragment extends BaseFragment implements View.OnClickListener {

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

    @Bind(R.id.et_from_x)
    EditText etFromX;

    @Bind(R.id.et_to_x)
    EditText etToX;

    @Bind(R.id.et_from_y)
    EditText etFromY;

    @Bind(R.id.et_to_y)
    EditText etToY;

    @Bind(R.id.rb_constructor_4)
    RadioButton rbConstructor4;

    @Bind(R.id.rb_constructor_6)
    RadioButton rbConstructor6;

    @Bind(R.id.rb_constructor_8)
    RadioButton rbConstructor8;

    @Bind(R.id.rb_x_absolute)
    RadioButton rbXAbsolute;

    @Bind(R.id.rb_x_self)
    RadioButton rbXSelf;

    @Bind(R.id.rb_x_parent)
    RadioButton rbXParent;

    @Bind(R.id.rb_y_absolute)
    RadioButton rbYAbsolute;

    @Bind(R.id.rb_y_self)
    RadioButton rbYSelf;

    @Bind(R.id.rb_y_parent)
    RadioButton rbYParent;

    @Bind(R.id.et_pivot_x)
    EditText etPivotX;

    @Bind(R.id.et_pivot_y)
    EditText etPivotY;


    public ScaleAnimationFragment() {
        super(R.layout.f_animation_tween_scale);
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
        float fromX = StringsUtils.isEmpty(etFromX.getText()) ? 1.0f : Float.parseFloat(etFromX.getText().toString());
        float fromY = StringsUtils.isEmpty(etToX.getText()) ? 1.0f : Float.parseFloat(etToX.getText().toString());
        float toX = StringsUtils.isEmpty(etFromY.getText()) ? 2.0f : Float.parseFloat(etFromY.getText().toString());
        float toY = StringsUtils.isEmpty(etToY.getText()) ? 2.0f : Float.parseFloat(etToY.getText().toString());

        float pivotX;
        float pivotY;
        int pivotXType;
        int pivotYType;

        if (rbXSelf.isChecked()) {
            pivotXType = RotateAnimation.RELATIVE_TO_SELF;
            pivotX = StringsUtils.isEmpty(etPivotX.getText()) ? 0.5f : processPivot(pivotXType, Float.parseFloat(etPivotX.getText().toString()));
        } else if (rbXParent.isChecked()) {
            pivotXType = RotateAnimation.RELATIVE_TO_PARENT;
            pivotX = StringsUtils.isEmpty(etPivotX.getText()) ? 0.5f : processPivot(pivotXType, Float.parseFloat(etPivotX.getText().toString()));
        } else {
            pivotXType = RotateAnimation.ABSOLUTE;
            pivotX = StringsUtils.isEmpty(etPivotX.getText()) ? 500.1f : processPivot(pivotXType, Float.parseFloat(etPivotX.getText().toString()));
        }

        if (rbYSelf.isChecked()) {
            pivotYType = RotateAnimation.RELATIVE_TO_SELF;
            pivotY = StringsUtils.isEmpty(etPivotX.getText()) ? 0.5f : processPivot(pivotXType, Float.parseFloat(etPivotX.getText().toString()));
        } else if (rbYParent.isChecked()) {
            pivotYType = RotateAnimation.RELATIVE_TO_PARENT;
            pivotY = StringsUtils.isEmpty(etPivotX.getText()) ? 0.5f : processPivot(pivotXType, Float.parseFloat(etPivotX.getText().toString()));
        } else {
            pivotYType = RotateAnimation.ABSOLUTE;
            pivotY = StringsUtils.isEmpty(etPivotX.getText()) ? 505.5f : processPivot(pivotXType, Float.parseFloat(etPivotX.getText().toString()));
        }

        ScaleAnimation scaleAnimation = null;
        if (rbConstructor4.isChecked()) {
            scaleAnimation = new ScaleAnimation(fromX, toX, fromY, toY);
        } else if (rbConstructor6.isChecked()) {
            scaleAnimation = new ScaleAnimation(fromX, toX, fromY, toY, pivotX, pivotY);
        } else if (rbConstructor8.isChecked()) {
            scaleAnimation = new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotX, pivotYType, pivotY);
        }

        if (scaleAnimation == null) {
            ToastUtils.show(mActivity, "请选择构造参数");
            return;
        }

        Long duration = StringsUtils.isEmpty(etDuration.getText()) ? 3000 : Long.parseLong(etDuration.getText().toString());
        scaleAnimation.setDuration(duration);

        Long startOffset = StringsUtils.isEmpty(etStartOffset.getText()) ? 1000 : Long.parseLong(etStartOffset.getText().toString());
        scaleAnimation.setDuration(startOffset);

        int repeatMode = rbReverse.isChecked() ? Animation.REVERSE : Animation.RESTART;
        scaleAnimation.setRepeatMode(repeatMode);

        int repeatCount = StringsUtils.isEmpty(etRepeatCount.getText()) ? 0 : Integer.parseInt(etRepeatCount.getText().toString());
        scaleAnimation.setRepeatCount(repeatCount);

        scaleAnimation.setFillAfter(cbFillAfter.isChecked());
        scaleAnimation.setFillBefore(cbFillBefore.isChecked());
        scaleAnimation.setFillEnabled(cbFillEnabled.isChecked());

        ivLogo.startAnimation(scaleAnimation);
        btnStartJava.startAnimation(scaleAnimation);
    }

    /**
     * 处理透明度
     */
    public float processPivot(int pivotType, float pivotX) {
        switch (pivotType) {
            case RotateAnimation.ABSOLUTE:
                return pivotX;
            case RotateAnimation.RELATIVE_TO_SELF:
            case RotateAnimation.RELATIVE_TO_PARENT:
//                if (pivotX < 0) {
//                    pivotX = 0;
//                }
//                while (pivotX > 1) {
//                    pivotX--;
//                }
                return pivotX;
        }
        return pivotX;
    }

    /**
     * 开始执行XML动画
     */
    public void startXML() {
        Animation mAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.tween_animation_scale);
        ivLogo.startAnimation(mAnimation);
        btnStartXML.startAnimation(mAnimation);
    }
}
