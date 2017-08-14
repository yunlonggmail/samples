package com.yunlong.samples.design.main.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.design.main.adapter.DesignPatternAdapter;
import com.yunlong.samples.model.YLEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/7/25.
 * 基础加载文件适配器
 */

public abstract class BaseLoadFileListActivity extends BaseActivity {
    /**
     * 数据
     */
    @Bind(R.id.rv_list)
    protected RecyclerView rvData;
    /**
     * 适配器
     */
    private DesignPatternAdapter designPatternAdapter;
    /**
     * 数据集合
     */
    private List<YLEntity> dataList = new ArrayList<>();

    @Override
    protected int getResourceId() {
        return R.layout.a_design_pattern_base_load_file_list_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setData();
        designPatternAdapter = new DesignPatternAdapter(mContext, dataList);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        rvData.setAdapter(designPatternAdapter);
    }

    /**
     * 设置数据
     */
    protected abstract void setData();

    protected void addData(YLEntity ylEntity) {
        dataList.add(ylEntity);
    }
}
