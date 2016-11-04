package com.yunlong.samples.intent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.yunlong.samples.R;
import com.yunlong.samples.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2016/10/19.
 * 携带参数界面
 */
public class IntentWithBundleActivity extends BaseActivity {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.IntentWithBundle";
    /**
     * 参数1
     */
    public static final String DATA_1 = "data_1";
    /**
     * 参数2
     */
    public static final String DATA_2 = "data_2";
    /**
     * 数据
     */
    @Bind(R.id.tv_data)
    TextView tvData;

    @Override
    protected int getResourceId() {
        return R.layout.a_intent_with_bundle;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_intent_with_bundle);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                setData(bundle);
            }
        }
    }

    /**
     * 设置数据
     *
     * @param bundle
     */
    protected void setData(Bundle bundle) {
        String data1 = bundle.getString(DATA_1);
        String data2 = bundle.getString(DATA_2);
        String text = DATA_1 + ": " + data1 + " \n" + DATA_2 + ": " + data2;
        tvData.setText(text);
    }
}
