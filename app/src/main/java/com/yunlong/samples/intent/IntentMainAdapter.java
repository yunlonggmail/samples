package com.yunlong.samples.intent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yunlong.lib.base.BaseAdapter;
import com.yunlong.samples.R;
import com.yunlong.samples.model.YLIntentMain;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shiyunlong on 2016/10/19.
 * 适配器
 */
public class IntentMainAdapter extends BaseAdapter<YLIntentMain> {

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

    public IntentMainAdapter(Context context, List<YLIntentMain> data) {
        super(context, data);
    }

    @Override
    public OnItemClickListener getOnItemClickListener() {
        return new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                YLIntentMain ylIntentMain = getItem(position);
                if (ylIntentMain == null || TextUtils.isEmpty(ylIntentMain.activityIntent))
                    return;
                Intent intent = new Intent();
                intent.setAction(ylIntentMain.activityIntent);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                if (ylIntentMain.bundle != null) {
                    intent.putExtra(BundleConfig.BUNDLE_ACTION_KEY, ylIntentMain.bundle.getString(BundleConfig.BUNDLE_ACTION_KEY));
                    Bundle extraBundle = ylIntentMain.bundle.getBundle(BundleConfig.BUNDLE_KEY);
                    if (extraBundle != null)
                        intent.putExtras(extraBundle);
                }
                mContext.startActivity(intent);
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
        YLIntentMain ylIntentMain = getItem(position);
        if (ylIntentMain == null)
            return;

        tvTitle.setText(ylIntentMain.name);
        tvDesc.setText(ylIntentMain.desc);
    }
}
