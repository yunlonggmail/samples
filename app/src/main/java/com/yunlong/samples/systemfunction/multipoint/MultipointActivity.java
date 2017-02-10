package com.yunlong.samples.systemfunction.multipoint;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.LogUtils;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * 多点触摸
 * Created by shiyunlong on 2017/2/7.
 */

public class MultipointActivity extends BaseActivity implements View.OnTouchListener {
    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.Multipoint";
    /**
     * 根节点
     */
    @Bind(R.id.fl_root)
    public LinearLayout flRoot;
    /**
     * 图片
     */
    @Bind(R.id.iv_logo)
    public ImageView ivLogo;
    /**
     * 上一次距离
     */
    public float lastDistance = -1;
    /**
     * 当前距离
     */
    public float currentDistance = 0;


    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_multipoint);
        super.initTitleBar();
    }

    @Override
    protected int getResourceId() {
        return R.layout.a_system_function_multipoint;
    }

    @Override
    protected void initView() {
        flRoot.setOnTouchListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float firstX = event.getX(0);
                float firstY = event.getY(0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                params.leftMargin = (int) firstX;
//                params.topMargin = (int) firstY;
                if (event.getPointerCount() >= 2) {
                    float secondX = event.getX(1);
                    float disX = Math.abs(firstX - secondX);
                    float secondY = event.getY(1);
                    float disY = Math.abs(firstY - secondY);
                    currentDistance = (float) Math.sqrt(disX * disX + disY * disY);
                    if (lastDistance == -1) {
                        lastDistance = currentDistance;
                    } else {
                        if (Math.abs(currentDistance - lastDistance) > 5) {
                            float b = currentDistance / lastDistance > 1 ? 1.1f : 0.9f;
                            LogUtils.D(MultipointActivity.this.getClass().getName(), "缩放比例：" + b);
                            params.width = (int) (b * ivLogo.getWidth());
                            params.height = (int) (b * ivLogo.getHeight());
                            lastDistance = currentDistance;
                        }
                    }
                }
                ivLogo.setLayoutParams(params);
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        //true 表示后续还需要处理
        return true;
    }

}
