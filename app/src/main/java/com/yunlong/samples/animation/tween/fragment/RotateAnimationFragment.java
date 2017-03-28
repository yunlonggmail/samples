package com.yunlong.samples.animation.tween.fragment;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
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

public class RotateAnimationFragment extends BaseFragment implements View.OnClickListener {

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

    @Bind(R.id.et_from_degree)
    EditText etFromDegree;

    @Bind(R.id.et_to_degree)
    EditText etToDegree;

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

    @Bind(R.id.rb_constructor_2)
    RadioButton rbConstructor2;

    @Bind(R.id.rb_constructor_4)
    RadioButton rbConstructor4;

    @Bind(R.id.rb_constructor_6)
    RadioButton rbConstructor6;


    public RotateAnimationFragment() {
        super(R.layout.f_animation_tween_rotate);
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
        float fromDegree = StringsUtils.isEmpty(etFromDegree.getText()) ? 0 : Float.parseFloat(etFromDegree.getText().toString());
        float toDegree = StringsUtils.isEmpty(etToDegree.getText()) ? 360 : Float.parseFloat(etToDegree.getText().toString());
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

        RotateAnimation rotateIvAnimation = null;
        RotateAnimation rotateBtnAnimation = null;

        if (rbConstructor2.isChecked()) {
            rotateIvAnimation = new RotateAnimation(fromDegree, toDegree);
            rotateBtnAnimation = new RotateAnimation(fromDegree, toDegree);
        } else if (rbConstructor4.isChecked()) {
            rotateIvAnimation = new RotateAnimation(fromDegree, toDegree, pivotX, pivotY);
            rotateBtnAnimation = new RotateAnimation(fromDegree, toDegree, pivotX, pivotY);
        } else if (rbConstructor6.isChecked()) {
            rotateIvAnimation = new RotateAnimation(fromDegree, toDegree, pivotXType, pivotX, pivotYType, pivotY);
            rotateBtnAnimation = new RotateAnimation(fromDegree, toDegree, pivotXType, pivotX, pivotYType, pivotY);
        }
        if (rotateIvAnimation == null) {
            ToastUtils.show(mActivity, "请选择构造参数");
            return;
        }
        Long ivDuration = StringsUtils.isEmpty(etDuration.getText()) ? 3000 : Long.parseLong(etDuration.getText().toString());
        rotateIvAnimation.setDuration(ivDuration);

        Long ivStartOffset = StringsUtils.isEmpty(etStartOffset.getText()) ? 1000 : Long.parseLong(etStartOffset.getText().toString());
        rotateIvAnimation.setDuration(ivStartOffset);

        int ivRepeatMode = rbReverse.isChecked() ? Animation.REVERSE : Animation.RESTART;
        rotateIvAnimation.setRepeatMode(ivRepeatMode);

        int ivRepeatCount = StringsUtils.isEmpty(etRepeatCount.getText()) ? 0 : Integer.parseInt(etRepeatCount.getText().toString());
        rotateIvAnimation.setRepeatCount(ivRepeatCount);

        rotateIvAnimation.setFillAfter(cbFillAfter.isChecked());
        rotateIvAnimation.setFillBefore(cbFillBefore.isChecked());
        rotateIvAnimation.setFillEnabled(cbFillEnabled.isChecked());


        Long btDuration = StringsUtils.isEmpty(etDuration.getText()) ? 3000 : Long.parseLong(etDuration.getText().toString());
        rotateBtnAnimation.setDuration(btDuration);

        Long btnStartOffset = StringsUtils.isEmpty(etStartOffset.getText()) ? 1000 : Long.parseLong(etStartOffset.getText().toString());
        rotateBtnAnimation.setDuration(btnStartOffset);

        int btnRepeatMode = rbReverse.isChecked() ? Animation.REVERSE : Animation.RESTART;
        rotateBtnAnimation.setRepeatMode(btnRepeatMode);

        int btnRepeatCount = StringsUtils.isEmpty(etRepeatCount.getText()) ? 0 : Integer.parseInt(etRepeatCount.getText().toString());
        rotateBtnAnimation.setRepeatCount(btnRepeatCount);

        rotateBtnAnimation.setFillAfter(cbFillAfter.isChecked());
        rotateBtnAnimation.setFillBefore(cbFillBefore.isChecked());
        rotateBtnAnimation.setFillEnabled(cbFillEnabled.isChecked());

        ivLogo.startAnimation(rotateIvAnimation);
        btnStartJava.startAnimation(rotateBtnAnimation);
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
        Animation mIvAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.tween_animation_rotate);
        Animation mBtnAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.tween_animation_rotate);
        ivLogo.startAnimation(mIvAnimation);
        btnStartXML.startAnimation(mBtnAnimation);
    }
}
