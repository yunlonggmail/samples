package com.yunlong.samples.design.main.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.design.main.config.LoadFileConfig;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;

import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/7/24.
 * 加载文件界面
 */

public class LoadFileFromAssetsActivity extends BaseActivity {

    @Bind(R.id.tv_info)
    protected TextView tvInfo;
    /**
     * 设计模型
     */
    protected YLDesignPatternModel ylDesignPatternModel;

    @Override
    protected int getResourceId() {
        return R.layout.a_design_pattern_load;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            ylDesignPatternModel = intent.getParcelableExtra(LoadFileConfig.DESIGN_PATTERN_FILE_INFO);
            if (ylDesignPatternModel != null) {
                if (!TextUtils.isEmpty(ylDesignPatternModel.title)) {
                    setTitleBar(ylDesignPatternModel.title);
                }
                if (!TextUtils.isEmpty(ylDesignPatternModel.assertPath))
                    loadFileFromAssets(ylDesignPatternModel.assertPath);
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

    /**
     * 读取资源文件
     *
     * @param path：文件路径
     */
    protected void loadFileFromAssets(String path) {
        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try {
            StringBuilder sb = new StringBuilder();
            inputStream = assetManager.open(path);
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer)) > 0) {
                String str = new String(buffer, 0, len);
                sb.append(str);
//                LogUtils.E(this.getClass().getSimpleName(), str);
            }
            tvInfo.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            tvInfo.setText(R.string.a_design_pattern_file_empty);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
