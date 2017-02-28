package com.yunlong.samples.systemfunction.camera;

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
 * Created by shiyunlong on 2017/2/27.
 * 拍照主页面
 */

public class CameraMainActivity extends BaseActivity {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.CameraMain";

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
        return R.layout.a_system_function_camera_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_camera_main);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        addData();
        mainDataAdapter = new MainDataAdapter(mContext, dataList);
        rvData.setLayoutManager(new LinearLayoutManager(mContext));
        rvData.setAdapter(mainDataAdapter);

    }

    /**
     * 添加数据
     */
    private void addData() {
        addCameraNormal();
        addCameraCustom();
    }

    /**
     * 普通拍照页面
     */
    private void addCameraNormal() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = CameraNormalActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_system_function_camera_normal);
        ylMain.desc = getString(R.string.nav_title_system_function_camera_normal_desc);
        dataList.add(ylMain);
    }

    /**
     * 定制拍照页面
     */
    private void addCameraCustom() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = CameraCustomActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_system_function_camera_custom);
        ylMain.desc = getString(R.string.nav_title_system_function_camera_custom_desc);
        dataList.add(ylMain);
    }
}
