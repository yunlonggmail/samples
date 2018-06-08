package com.yunlong.samples.jnitest;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.main.MainDataAdapter;
import com.yunlong.samples.model.YLMain;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/6/19.
 * 测试主页面
 */
public class JNIMainActivity extends BaseActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.jni.Main";

    /**
     * 数据
     */
    @Bind(R.id.rv_list)
    RecyclerView rvData;
    /**
     * 适配器
     */
    MainDataAdapter mainDataAdapter;
    /**
     * 数据集合
     */
    private List<YLMain> dataList = new ArrayList<>();

    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_jni_main_title);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        setData();
        mainDataAdapter = new MainDataAdapter(mContext, dataList);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        rvData.setAdapter(mainDataAdapter);
    }


    protected void setData() {
        addJNI();
    }

    /**
     * 添加复制页面
     */
    protected void addJNI() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = JNITestActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_jni_test_title);
        ylMain.desc = getString(R.string.nav_title_jni_test_title_desc);
        dataList.add(ylMain);
    }
}

