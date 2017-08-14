package com.yunlong.samples.design.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yunlong.lib.base.BaseAdapter;
import com.yunlong.samples.R;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;
import com.yunlong.samples.design.main.utils.LoadFileUtils;
import com.yunlong.samples.model.YLEntity;
import com.yunlong.samples.model.YLMain;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shiyunlong on 2017/7/24.
 * 单例设计模式主页面
 */

public class DesignPatternAdapter extends BaseAdapter<YLEntity> {
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

    public DesignPatternAdapter(Context context, List<YLEntity> data) {
        super(context, data);
    }

    @Override
    public OnItemClickListener getOnItemClickListener() {
        return new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                YLEntity ylEntity = getItem(position);
                if (ylEntity != null && ylEntity instanceof YLDesignPatternModel) {
                    YLDesignPatternModel ylDesignPatternModel = (YLDesignPatternModel) ylEntity;
                    if (TextUtils.isEmpty(ylDesignPatternModel.assertPath)) {
                        return;
                    }
                    LoadFileUtils.loadAssertFile(mContext, ylDesignPatternModel);
                } else if (ylEntity != null && ylEntity instanceof YLMain) {
                    YLMain ylMain = (YLMain) ylEntity;
                    if (TextUtils.isEmpty(ylMain.activityIntent))
                        return;
                    Intent intent = new Intent();
                    intent.setAction(ylMain.activityIntent);
                    if (ylMain.extras != null)
                        intent.putExtras(ylMain.extras);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    view.getContext().startActivity(intent);
                }


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
        YLEntity ylEntity = getItem(position);
        if (ylEntity != null && ylEntity instanceof YLDesignPatternModel) {
            YLDesignPatternModel ylDesignPatternModel = (YLDesignPatternModel) ylEntity;
            tvTitle.setText(ylDesignPatternModel.title);
            tvDesc.setText(ylDesignPatternModel.desc);
        } else if (ylEntity != null && ylEntity instanceof YLMain) {
            YLMain ylMain = (YLMain) ylEntity;
            tvTitle.setText(ylMain.name);
            tvDesc.setText(ylMain.desc);
        }
    }


}
