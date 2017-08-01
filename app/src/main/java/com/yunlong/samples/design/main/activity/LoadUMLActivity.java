package com.yunlong.samples.design.main.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.GlideUtils;
import com.yunlong.lib.utils.StringsUtils;
import com.yunlong.lib.utils.ToastUtils;
import com.yunlong.samples.R;
import com.yunlong.samples.design.main.config.LoadFileConfig;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/8/1.
 * 加载UML类图
 */

public class LoadUMLActivity extends BaseActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.LoadUML";

    @Bind(R.id.iv_uml)
    ImageView ivUml;

    /**
     * 图片信息
     */
    private YLDesignPatternModel mYLDesignPatternModel;

    @Override
    protected int getResourceId() {
        return R.layout.a_design_pattern_load_uml;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mYLDesignPatternModel = intent.getParcelableExtra(LoadFileConfig.DESIGN_PATTERN_FILE_INFO);
            if (mYLDesignPatternModel != null) {
                if (!TextUtils.isEmpty(mYLDesignPatternModel.title)) {
                    setTitleBar(mYLDesignPatternModel.title);
                }
                if (StringsUtils.isEmpty(mYLDesignPatternModel.umlPath)) {
                    ToastUtils.show(mContext, R.string.a_design_pattern_uml_image_empty);
                } else {
                    GlideUtils.INSTANCE.load(mYLDesignPatternModel.umlPath, ivUml);
                }
            }
        }
    }

    /**
     * 设置TitleBar
     *
     * @param title
     */
    private void setTitleBar(String title) {
        titleBar.setTitle(title);
        super.initTitleBar();
    }

}
