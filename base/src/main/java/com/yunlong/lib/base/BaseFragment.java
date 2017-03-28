package com.yunlong.lib.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by shiyunlong on 2017/2/7.
 * 抽象类
 */

public abstract class BaseFragment extends Fragment {
    /**
     * 根布局
     */
    protected View mRootView;
    /**
     * 布局填充器
     */
    protected LayoutInflater mLayoutInflater;
    /**
     * 根布局ID
     */
    protected int mRootLayoutID;
    /**
     * 对应的Activity
     */
    protected Activity mActivity;

    public BaseFragment(int rootLayoutID) {
        super();
        this.mRootLayoutID = rootLayoutID;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mLayoutInflater = inflater;
        mRootView = inflater.inflate(mRootLayoutID, container, false);
        mActivity = getActivity();
        ButterKnife.bind(this, mRootView);
        viewDidLoad();
        return mRootView;
    }

    /**
     * View加载
     */
    protected void viewDidLoad() {
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();
}
