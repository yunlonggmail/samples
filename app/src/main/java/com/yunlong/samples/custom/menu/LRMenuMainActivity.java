package com.yunlong.samples.custom.menu;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * LRMenu主界面
 * Created by shiyunlong on 2017/2/6.
 */

public class LRMenuMainActivity extends BaseActivity {
    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.LRMenuMain";
    /**
     * LrMenu
     */
    @Bind(R.id.lr_menu)
    public LRMenu lrMenu;
    /**
     * 左侧Fragment
     */
    public LeftFragment leftFragment;
    /**
     * 中间Fragment
     */
    public MiddleFragment middleFragment;
    /**
     * 右侧Fragment
     */
    public RightFragment rightFragment;

    @Override
    protected int getResourceId() {
        return R.layout.a_custom_lrmenu_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_custom_lrmenu_main);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initFragment() {
        leftFragment = new LeftFragment();
        getSupportFragmentManager().beginTransaction().replace(lrMenu.getLeftMenuId(), leftFragment).commitAllowingStateLoss();

        middleFragment = new MiddleFragment();
        getSupportFragmentManager().beginTransaction().replace(lrMenu.getMiddleMenuId(), middleFragment).commitAllowingStateLoss();

        rightFragment = new RightFragment();
        getSupportFragmentManager().beginTransaction().replace(lrMenu.getRightMenuId(), rightFragment).commitAllowingStateLoss();
    }
}
