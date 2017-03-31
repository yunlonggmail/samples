package com.yunlong.samples.animation.property.fragment;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yunlong.lib.base.BaseFragment;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * 属性集合和关键帧
 */

public class ExtraAnimatorFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.btn_view_animator)
    Button btnViewAnimator;

    boolean isFirst;

    @Bind(R.id.btn_add)
    Button btnAdd;

    @Bind(R.id.btn_remove)
    Button btnRemove;

    @Bind(R.id.ll_data)
    LinearLayout llData;

    private int i;

    LayoutTransition layoutTransition = new LayoutTransition();

    public ExtraAnimatorFragment() {
        super(R.layout.f_animator_extra);
    }

    @Override
    protected void initView() {
        btnViewAnimator.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnRemove.setOnClickListener(this);

        layoutTransition.setAnimator(LayoutTransition.APPEARING, ObjectAnimator.ofFloat(null, "rotationX", 0f, 360f));
        llData.setLayoutTransition(layoutTransition);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_view_animator:
                if (isFirst)
                    startFirstViewAnimator();
                else
                    startViewAnimator();
                break;
            case R.id.btn_add:
                addAnimator();
                break;
            case R.id.btn_remove:
                removeAnimator();
                break;
        }
    }

    /**
     * 开始ViewAnimator
     */
    private void startViewAnimator() {
        ViewPropertyAnimator viewPropertyAnimator = btnViewAnimator.animate();
        viewPropertyAnimator.xBy(200f);
        viewPropertyAnimator.setDuration(3000);
        viewPropertyAnimator.start();
    }

    /**
     * 开始ViewAnimator
     */
    private void startFirstViewAnimator() {
        isFirst = false;
        ViewPropertyAnimator viewPropertyAnimator = btnViewAnimator.animate();
        viewPropertyAnimator.x(200f);
        viewPropertyAnimator.setDuration(3000);
        viewPropertyAnimator.start();
    }

    /**
     * 添加子布局
     */
    private void addAnimator() {
        Button btn = new Button(mActivity);
        btn.setText(String.valueOf(i));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llData.addView(btn, Math.min(1, i), params);
        i++;
    }

    /**
     * 移除子布局
     */
    private void removeAnimator() {
        i--;
        if (i >= 0)
            llData.removeViewAt(i);
    }

}
