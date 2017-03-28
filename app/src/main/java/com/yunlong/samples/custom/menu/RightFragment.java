package com.yunlong.samples.custom.menu;

import android.view.View;
import android.widget.Button;

import com.yunlong.lib.base.BaseFragment;
import com.yunlong.lib.utils.ToastUtils;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * 左侧Fragment
 * Created by shiyunlong on 2017/2/7.
 */

public class RightFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.btn_right_menu)
    Button btnRightMenu;


    public RightFragment() {
        super(R.layout.a_custom_lrmenu_right);
    }

    @Override
    protected void initView() {
        btnRightMenu.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_right_menu:
                ToastUtils.show(mActivity, R.string.a_custom_lrmenu_right_btn);
                break;
        }
    }
}
