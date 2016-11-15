package com.yunlong.sampleclient.main;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.sampleclient.R;
import com.yunlong.sampleclient.model.YLMain;
import com.yunlong.sampleclient.service.RemoteServiceMainActivity;

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
        addRemoteService();
    }

    /**
     * 添加远程服务主界面
     */
    protected void addRemoteService() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = RemoteServiceMainActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_service_remote_service);
        ylMain.desc = getString(R.string.nav_title_service_remote_service_desc);
        dataList.add(ylMain);
    }

}
