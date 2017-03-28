package com.yunlong.samples.copy;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * Copy界面
 */

public class CopyActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.CopyMain";

    @Bind(R.id.tv_copy)
    TextView tvCopy;

    @Bind(R.id.et_copy)
    EditText etCopy;

    @Bind(R.id.btn_copy)
    Button btnCopy;

    @Override
    protected int getResourceId() {
        return R.layout.a_copy;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_copy);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

        btnCopy.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_copy:
                break;
        }
    }
}
