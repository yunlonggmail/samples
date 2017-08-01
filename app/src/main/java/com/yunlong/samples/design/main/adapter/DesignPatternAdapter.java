package com.yunlong.samples.design.main.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yunlong.lib.base.BaseAdapter;
import com.yunlong.samples.R;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;
import com.yunlong.samples.design.main.utils.LoadFileUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shiyunlong on 2017/7/24.
 * 单例设计模式主页面
 */

public class DesignPatternAdapter extends BaseAdapter<YLDesignPatternModel> {
    /**
     * 标题
     */
    @Bind(R.id.tv_title)
    protected TextView tvTitle;
    /**
     * 描述
     */
    @Bind(R.id.tv_desc)
    protected TextView tvDesc;

    public DesignPatternAdapter(Context context, List<YLDesignPatternModel> data) {
        super(context, data);
    }

    @Override
    public OnItemClickListener getOnItemClickListener() {
        return new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                YLDesignPatternModel ylDesignPatternModel = getItem(position);
                if (ylDesignPatternModel == null || TextUtils.isEmpty(ylDesignPatternModel.assertPath)) {
                    return;
                }
                LoadFileUtils.loadAssertFile(mContext, ylDesignPatternModel);
            }
        };
    }

    @Override
    public int getResourceId(int viewType) {
        return R.layout.i_main;
    }

    @Override
    public void bindView(View itemView, int position) {
        ButterKnife.bind(this, itemView);
        YLDesignPatternModel ylDesignPatternModel = getItem(position);
        if (ylDesignPatternModel == null)
            return;

        tvTitle.setText(ylDesignPatternModel.title);
        tvDesc.setText(ylDesignPatternModel.desc);
    }


}
