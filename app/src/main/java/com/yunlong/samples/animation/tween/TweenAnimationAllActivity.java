package com.yunlong.samples.animation.tween;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.yunlong.lib.adapter.ViewPagerFragmentAdapter;
import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.widget.PagerSlidingTabStrip;
import com.yunlong.samples.R;
import com.yunlong.samples.animation.tween.fragment.AlphaAnimationFragment;
import com.yunlong.samples.animation.tween.fragment.AnimationFragment;
import com.yunlong.samples.animation.tween.fragment.AnimationSetFragment;
import com.yunlong.samples.animation.tween.fragment.InterpolatorSetFragment;
import com.yunlong.samples.animation.tween.fragment.RotateAnimationFragment;
import com.yunlong.samples.animation.tween.fragment.ScaleAnimationFragment;
import com.yunlong.samples.animation.tween.fragment.TranslateAnimationFragment;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * Copy界面
 */

public class TweenAnimationAllActivity extends BaseActivity {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.animation.TweenAnimationAll";

    @Bind(R.id.psts_tabs)
    PagerSlidingTabStrip pstsTabs;

    @Bind(R.id.vp_fragment)
    ViewPager vpFragment;

    /**
     * 适配器
     */
    ViewPagerFragmentAdapter mTabsAdapter;

    @Override
    protected int getResourceId() {
        return R.layout.a_animation_tween_all;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_animation_tween_animation_all);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        mTabsAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), pstsTabs, vpFragment);
    }

    @Override
    protected void initData() {
        addData();
    }

    /**
     * 添加数据
     */
    private void addData() {
        mTabsAdapter.addTab("Interpolator", "Interpolator", InterpolatorSetFragment.class, new Bundle());
        mTabsAdapter.addTab("Alpha", "Alpha", AlphaAnimationFragment.class, new Bundle());
        mTabsAdapter.addTab("Rotate", "Rotate", RotateAnimationFragment.class, new Bundle());
        mTabsAdapter.addTab("Translate", "Translate", TranslateAnimationFragment.class, new Bundle());
        mTabsAdapter.addTab("Scale", "Scale", ScaleAnimationFragment.class, new Bundle());
        mTabsAdapter.addTab("Set", "Set", AnimationSetFragment.class, new Bundle());
        mTabsAdapter.addTab("Animation", "Animation", AnimationFragment.class, new Bundle());
        mTabsAdapter.setPagerAdapter();
        mTabsAdapter.notifyDataSetChanged();
    }

}
