package com.yunlong.samples.main;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.samples.R;
import com.yunlong.samples.base.BaseActivity;
import com.yunlong.samples.incremental.IncrementalUpdateActivity;
import com.yunlong.samples.intent.IntentMainActivity;
import com.yunlong.samples.model.YLMain;
import com.yunlong.samples.service.ServiceMainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

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
        titleBar.setTitle(R.string.nav_title_main);
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
        addIntentMain();
        addIncrementUpdateMain();
        addServiceMain();
    }

    /**
     * 添加Intent主界面
     */
    protected void addIntentMain() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = IntentMainActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_intent_main);
        ylMain.desc = getString(R.string.nav_title_intent_main_desc);
        dataList.add(ylMain);
    }

    /**
     * 添加增量更新界面
     */
    protected void addIncrementUpdateMain() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = IncrementalUpdateActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_increment_update);
        ylMain.desc = getString(R.string.nav_title_increment_update_desc);
        dataList.add(ylMain);
    }

    /**
     * 添加服务主界面
     */
    protected void addServiceMain() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = ServiceMainActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_service_main);
        ylMain.desc = getString(R.string.nav_title_service_main_desc);
        dataList.add(ylMain);
    }

}
