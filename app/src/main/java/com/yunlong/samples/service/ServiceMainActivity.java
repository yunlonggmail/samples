package com.yunlong.samples.service;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.main.MainDataAdapter;
import com.yunlong.samples.model.YLMain;
import com.yunlong.samples.service.local.LocalServiceMainActivity;
import com.yunlong.samples.service.remote.RemoteServiceMainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2016/10/27.
 * 服务主界面
 */
public class ServiceMainActivity extends BaseActivity {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.ServiceMain";
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
        return R.layout.a_service_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_service_main);
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
        addLocalService();
        addRemoteService();
    }

    /**
     * 本地服务(当前应用中使用)
     */
    protected void addLocalService() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = LocalServiceMainActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_service_local_service);
        ylMain.desc = getString(R.string.nav_title_service_local_service_desc);
        dataList.add(ylMain);
    }

    /**
     * 设置远端服务(其他应用中使用)
     */
    protected void addRemoteService() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = RemoteServiceMainActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_service_remote_service);
        ylMain.desc = getString(R.string.nav_title_service_remote_service_desc);
        dataList.add(ylMain);
    }

}
