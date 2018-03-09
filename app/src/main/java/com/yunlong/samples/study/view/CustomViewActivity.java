package com.yunlong.samples.study.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ActionMode;
import android.view.WindowManager;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.LogUtils;
import com.yunlong.samples.R;

/**
 * Created by shiyunlong on 2018/2/27.
 */

public class CustomViewActivity extends BaseActivity {

    public static final String TAG = "com.yunlong.samples.study.view.CustomViewActivity";

    public static final String INTENT_ACTION = "com.yunlong.samples.study.view.CustomView";

    @Override
    protected int getResourceId() {
        return R.layout.a_study_custom_view;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_study_custom_view);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.D(TAG," Activity onCreate ");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        LogUtils.D(TAG," Activity onStart ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        LogUtils.D(TAG," Activity onResume ");
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.D(TAG," Activity onPause ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.D(TAG," Activity onStop ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.D(TAG," Activity onDestroy ");
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        LogUtils.D(TAG," Activity onWindowStartingActionMode ");
        return super.onWindowStartingActionMode(callback);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        LogUtils.D(TAG," Activity onWindowStartingActionMode ");
        return super.onWindowStartingActionMode(callback, type);
    }

    @Override
    public boolean hasWindowFocus() {
        LogUtils.D(TAG," Activity hasWindowFocus ");
        return super.hasWindowFocus();
    }

    @Override
    public void onAttachedToWindow() {
        LogUtils.D(TAG," Activity hasWindowFocus ");
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        LogUtils.D(TAG," Activity hasWindowFocus ");
        super.onDetachedFromWindow();
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        LogUtils.D(TAG," Activity hasWindowFocus ");
        super.onWindowAttributesChanged(params);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        LogUtils.D(TAG," Activity hasWindowFocus ");
        super.onWindowFocusChanged(hasFocus);
    }

//    @Override
//    public Window getWindow() {
//        return super.getWindow();
//    }
//
//    @Override
//    public WindowManager getWindowManager() {
//        return super.getWindowManager();
//    }

    @Nullable
    @Override
    public android.support.v7.view.ActionMode onWindowStartingSupportActionMode(@NonNull android.support.v7.view.ActionMode.Callback callback) {
        LogUtils.D(TAG," Activity hasWindowFocus ");
        return super.onWindowStartingSupportActionMode(callback);
    }

    @Override
    public boolean supportRequestWindowFeature(int featureId) {
        LogUtils.D(TAG," Activity hasWindowFocus ");
        return super.supportRequestWindowFeature(featureId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtils.D(TAG," Activity hasWindowFocus ");
        super.onActivityResult(requestCode, resultCode, data);
    }
}
