package com.yunlong.samples.design.main.utils;

import android.content.Context;
import android.content.Intent;

import com.yunlong.samples.design.main.activity.LoadFileFromAssetsActivity;
import com.yunlong.samples.design.main.config.LoadFileConfig;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;

/**
 * Created by shiyunlong on 2017/8/1.
 * 加载工具类
 */

public class LoadFileUtils {
    /**
     * 加载类图
     *
     * @param context：上下文
     * @param ylDesignPatternModel：信息
     */
    public static void loadAssertFile(Context context, YLDesignPatternModel ylDesignPatternModel) {
        Intent intent = new Intent(context, LoadFileFromAssetsActivity.class);
        intent.putExtra(LoadFileConfig.DESIGN_PATTERN_FILE_INFO, ylDesignPatternModel);
        context.startActivity(intent);
    }
}
