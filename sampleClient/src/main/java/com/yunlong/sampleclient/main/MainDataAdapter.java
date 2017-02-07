package com.yunlong.sampleclient.main;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yunlong.lib.base.BaseAdapter;
import com.yunlong.sampleclient.R;
import com.yunlong.sampleclient.model.YLMain;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shiyunlong on 2016/10/9.
 */
public class MainDataAdapter extends BaseAdapter<YLMain> {

    /**
     * 标题
     */
    @Bind(R.id.tv_title)
    TextView tvTitle;
    /**
     * 描述
     */
    @Bind(R.id.tv_desc)
    TextView tvDesc;

    public MainDataAdapter(Context context,List<YLMain> data) {
        super(context,data);
    }

    @Override
    public OnItemClickListener getOnItemClickListener() {
        return new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                YLMain ylMain = getItem(position);
                if (ylMain == null || TextUtils.isEmpty(ylMain.activityIntent))
                    return;
                Intent intent = new Intent();
                intent.setAction(ylMain.activityIntent);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                view.getContext().startActivity(intent);
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
        YLMain ylMain = getItem(position);
        if (ylMain == null)
            return;

        tvTitle.setText(ylMain.name);
        tvDesc.setText(ylMain.desc);
    }

}