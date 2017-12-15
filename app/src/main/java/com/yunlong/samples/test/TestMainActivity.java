package com.yunlong.samples.test;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.qrcode.CaptureActivity;
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
public class TestMainActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.TestMain";

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

    @Bind(R.id.btn_qr)
    Button btnQr;

    @Override
    protected int getResourceId() {
        return R.layout.a_test_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_test_main);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnQr.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setData();
        mainDataAdapter = new MainDataAdapter(mContext, dataList);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        rvData.setAdapter(mainDataAdapter);
    }


    protected void setData() {
        addCopy();
    }

    /**
     * 添加复制页面
     */
    protected void addCopy() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = TestAActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_test_a);
        ylMain.desc = getString(R.string.nav_title_test_a_desc);
        dataList.add(ylMain);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_qr:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivity(intent);
                break;
        }
    }
}

