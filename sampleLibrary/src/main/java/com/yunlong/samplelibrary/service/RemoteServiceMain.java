package com.yunlong.samplelibrary.service;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.yunlong.samplelibrary.IRemoteService;
import com.yunlong.samplelibrary.ISamplesRemoteDataChangeListener;


/**
 * Created by shiyunlong on 2016/11/4.
 * 服务主函数
 */
public class RemoteServiceMain implements ServiceConnection {
    /**
     * 上下文
     */
    private Activity mActivity;
    /**
     * 数据改变监听
     */
    private DataChangeListener mDataChangeListener;

    /**
     * Local服务Service
     * 在一个应用中, 一个服务只有一个对象
     * 限定于服务的开启和关闭,在其他界面可以对服务进行关闭
     */
    private Intent localServiceIntent;

    /**
     * Binder
     */
    private IRemoteService serviceBinder;
    /**
     * 是否绑定
     */
    private boolean mIsBind;

    public RemoteServiceMain(Activity activity, DataChangeListener dataChangeListener) {
        this.mActivity = activity;
        this.mDataChangeListener = dataChangeListener;
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        localServiceIntent = new Intent();
        localServiceIntent.setClassName("com.yunlong.samples", "com.yunlong.samples.service.remote.RemoteService");
    }


    /**
     * 开启服务
     */
    public void startService() {
        mActivity.startService(localServiceIntent);
    }

    /**
     * 关闭服务
     */
    public void stopService() {
        mActivity.stopService(localServiceIntent);
    }

    /**
     * 开启服务
     */
    public void bindService() {
        mActivity.bindService(localServiceIntent, this, Context.BIND_AUTO_CREATE);
    }

    /**
     * 关闭服务
     */
    public void unBindService() {
        if (mIsBind)
            mActivity.unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        serviceBinder = IRemoteService.Stub.asInterface(iBinder);
        setDataChangeListener();
        mIsBind = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        serviceBinder = null;
        mIsBind = false;
    }

    /**
     * 同步数据
     */
    public void sync(String data) {
        if (serviceBinder != null) {
            try {
                serviceBinder.updateData(data);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        setDataChangeListener();
    }

    /**
     * 设置数据改变监听
     */
    private void setDataChangeListener() {
        if (serviceBinder != null && mDataChangeListener != null) {
            try {
                serviceBinder.setDataChangeListener(new ISamplesRemoteDataChangeListener.Stub() {

                    @Override
                    public void dataChange(String data) throws RemoteException {
                        mDataChangeListener.dataChange(data);
                    }

                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}
