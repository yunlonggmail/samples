package com.yunlong.samples.custom;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.custom.menu.LRMenuMainActivity;
import com.yunlong.samples.main.MainDataAdapter;
import com.yunlong.samples.model.YLMain;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 自定义主界面
 * Created by shiyunlong on 2017/2/6.
 */

public class CustomMainActivity extends BaseActivity {
    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.CustomMain";
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
        addLRMenuMain();
    }

    /**
     * 本地服务(当前应用中使用)
     */
    protected void addLRMenuMain() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = LRMenuMainActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_custom_lrmenu_main);
        ylMain.desc = getString(R.string.nav_title_custom_lrmenu_main_desc);
        dataList.add(ylMain);
    }

}
