package com.yunlong.samples.intent;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.model.YLIntentMain;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by shiyunlong on 16/9/13.
 * 意图打开主页面
 */
public class IntentMainActivity extends BaseActivity {

    @Bind(R.id.rv_list)
    RecyclerView rvData;

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.IntentMain";

    public List<YLIntentMain> dataList = new ArrayList<>();

    public IntentMainAdapter intentMainAdapter;


    @Override
    protected int getResourceId() {
        return R.layout.a_intent_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_intent_main);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setData();
        intentMainAdapter = new IntentMainAdapter(mContext,dataList);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        rvData.setAdapter(intentMainAdapter);
    }

    /**
     * 设置数据
     */
    protected void setData() {
        addIntentWithBundle();
        addIntentWithoutBundle();
    }

    /**
     * 携带数据
     */
    protected void addIntentWithBundle() {
        YLIntentMain ylIntentMain = new YLIntentMain();
        ylIntentMain.activityIntent = IntentLoginActivity.INTENT_ACTION;
        ylIntentMain.name = getString(R.string.nav_title_intent_with_bundle);
        ylIntentMain.desc = getString(R.string.nav_title_intent_with_bundle_desc);
        ylIntentMain.bundle = new Bundle();
        ylIntentMain.bundle.putString(BundleConfig.BUNDLE_ACTION_KEY, IntentWithBundleActivity.INTENT_ACTION);
        Bundle bundle = new Bundle();
        bundle.putString(IntentWithBundleActivity.DATA_1, "数据1");
        bundle.putString(IntentWithBundleActivity.DATA_2, "数据2");
        ylIntentMain.bundle.putBundle(BundleConfig.BUNDLE_KEY, bundle);
        dataList.add(ylIntentMain);
    }

    /**
     * 不携带数据
     */
    protected void addIntentWithoutBundle() {
        YLIntentMain ylIntentMain = new YLIntentMain();
        ylIntentMain.activityIntent = IntentLoginActivity.INTENT_ACTION;
        ylIntentMain.name = getString(R.string.nav_title_intent_without_bundle);
        ylIntentMain.desc = getString(R.string.nav_title_intent_without_bundle);
        ylIntentMain.bundle = new Bundle();
        ylIntentMain.bundle.putString(BundleConfig.BUNDLE_ACTION_KEY, IntentWithoutBundleActivity.INTENT_ACTION);
        dataList.add(ylIntentMain);
    }
}
