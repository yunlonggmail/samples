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

public class MiddleFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.btn_middle_menu)
    Button btnMiddleMenu;


    public MiddleFragment() {
        super(R.layout.a_custom_lrmenu_middle);
    }

    @Override
    protected void viewDidLoad() {
        btnMiddleMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_middle_menu:
                ToastUtils.show(mActivity, R.string.a_custom_lrmenu_middle_btn);
                break;
        }
    }
}
