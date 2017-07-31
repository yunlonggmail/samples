package com.yunlong.samples.custom.wave;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * Copy界面
 */

public class WaveViewActivity extends BaseActivity {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.custom.WaveView";

    @Bind(R.id.fl_bg)
    FrameLayout flBg;

    @Bind(R.id.wv)
    WaveView wv;

    @Bind(R.id.iv_top)
    ImageView ivTop;


    @Override
    protected int getResourceId() {
        return R.layout.a_custom_wave_view;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_copy);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

        final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.CENTER;
        wv.setOnWaveAnimationListener(new WaveView.OnWaveAnimationListener() {
            @Override
            public void onWave(float y) {
                params.setMargins(0, 0, 0, (int) (y - 0.5) - 15);
                ivTop.setLayoutParams(params);
            }
        });

    }

    @Override
    protected void initData() {

    }


}
