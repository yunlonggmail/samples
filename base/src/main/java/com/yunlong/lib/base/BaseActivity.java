package com.yunlong.lib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yunlong.base.R;
import com.yunlong.lib.utils.LogUtils;

import butterknife.ButterKnife;

/**
 * Created by shiyunlong on 16/8/3.
 * 基础Activity
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final String TAG = "com.yunlong.lib.base.BaseActivity";
    /**
     * ToolBar
     */
    protected Toolbar titleBar;

    /**
     * 上下文
     */
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.D(TAG," BaseActivity onCreate");
        init();
        initTitleBar();
        initView();
        initData();
        initFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化
     */
    protected void init() {
        setContentView(getResourceId());
        ButterKnife.bind(this);
        titleBar = (Toolbar) findViewById(R.id.toolbar);
        mContext = this;
    }

    /**
     * 获取资源ID
     *
     * @return
     */
    protected abstract int getResourceId();

    /**
     * 初始化TitleBar
     */
    protected void initTitleBar() {
        setSupportActionBar(titleBar);
    }

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化Fragment
     */
    protected void initFragment() {
    }

}
