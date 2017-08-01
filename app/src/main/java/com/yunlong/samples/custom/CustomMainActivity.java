package com.yunlong.samples.custom;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.custom.menu.LRMenuMainActivity;
import com.yunlong.samples.custom.stroke.StrokeTextViewActivity;
import com.yunlong.samples.custom.trebling.TreblingViewActivity;
import com.yunlong.samples.custom.wave.WaveViewActivity;
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
    public static final String INTENT_ACTION = "com.yunlong.samples.custom.Main";
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
        return R.layout.a_custom_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_custom_main);
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
        addTreblingView();
        addWaveView();
        addStrokeTextView();
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

    /**
     * 添加复制页面
     */
    protected void addTreblingView() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = TreblingViewActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_custom_trebling_view);
        ylMain.desc = getString(R.string.nav_title_custom_trebling_view_desc);
        dataList.add(ylMain);
    }

    /**
     * 添加波浪View展示页面
     */
    protected void addWaveView() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = WaveViewActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_custom_wave_view);
        ylMain.desc = getString(R.string.nav_title_custom_wave_view_desc);
        dataList.add(ylMain);
    }

    /**
     * 添加描边TextView展示页面
     */
    protected void addStrokeTextView() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = StrokeTextViewActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_custom_stroke_text_view);
        ylMain.desc = getString(R.string.nav_title_custom_stroke_text_view_desc);
        dataList.add(ylMain);
    }

}
