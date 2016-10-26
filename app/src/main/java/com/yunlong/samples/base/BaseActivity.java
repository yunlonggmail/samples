package com.yunlong.samples.base;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yunlong.samples.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shiyunlong on 16/8/3.
 * 基础Activity
 */
public abstract class BaseActivity extends AppCompatActivity {
    /**
     * ToolBar
     */
    protected Toolbar toolbar;

    /**
     * 上下文
     */
    protected Context mContext;

    /**
     * 应用
     */
    protected App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initTitleBar();
        initView();
        initData();
        registerActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeActivity();
    }

    /**
     * 初始化
     */
    protected void init() {
        setContentView(getResourceId());
        checkApp();
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        setSupportActionBar(toolbar);
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
     * 检查APP
     */
    protected void checkApp() {
        if (app == null)
            app = App.getApplication();
        if (app == null)
            app = (App) getApplicationContext();
    }

    /**
     * 注册Activity
     */
    private void registerActivity() {
        if (app != null)
            app.registerActivity(this);
    }

    /**
     * 移除Activity
     */
    private void removeActivity() {
        if (app != null)
            app.registerActivity(this);
    }
}
