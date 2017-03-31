package com.yunlong.samples.animation.property;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.yunlong.lib.adapter.ViewPagerFragmentAdapter;
import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.widget.PagerSlidingTabStrip;
import com.yunlong.samples.R;
import com.yunlong.samples.animation.property.fragment.AnimatorFragment;
import com.yunlong.samples.animation.property.fragment.ExtraAnimatorFragment;
import com.yunlong.samples.animation.property.fragment.PKFragment;
import com.yunlong.samples.animation.property.fragment.ValueAnimatorFragment;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * Copy界面
 */

public class PropertyAnimatorAllActivity extends BaseActivity {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.animation.PropertyAnimator";

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
        return R.layout.a_animator_property_all;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_animator_all);
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
        mTabsAdapter.addTab("Animator", "Animator", AnimatorFragment.class, new Bundle());
        mTabsAdapter.addTab("ValueAnimator", "ValueAnimator", ValueAnimatorFragment.class, new Bundle());
        mTabsAdapter.addTab("PKAnimator", "PKAnimator", PKFragment.class, new Bundle());
        mTabsAdapter.addTab("ViewAnimator", "ViewAnimator", ExtraAnimatorFragment.class, new Bundle());
        mTabsAdapter.setPagerAdapter();
        mTabsAdapter.notifyDataSetChanged();
    }

}
