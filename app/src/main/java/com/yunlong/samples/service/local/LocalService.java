package com.yunlong.samples.service.local;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.yunlong.samples.utils.LogUtils;

import java.io.FileDescriptor;

public class LocalService extends Service {
    /**
     * 计数
     */
    public int count = 0;

    public String mData = "默认数据";
    /**
     * 回调
     */
    public DataChangeListener mDataChangeListener;
    /**
     * 线程运行Tag
     */
    public boolean mTag = true;

    public LocalService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new LocalServiceBinder();
    }

    /**
     * 服务信息
     */
    public class LocalServiceBinder extends Binder {

        public void updateData(String data) {
            count = 0;
            mData = data;
        }

        public void setDataChangeListener(DataChangeListener dataChangeListener) {
            mDataChangeListener = dataChangeListener;
        }

        public String getData() {
            return count + "," + mData;
        }

        public void setTag(boolean tag) {
            mTag = tag;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.D("LocalService", " onCreate " + this);
        startRun();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.D("LocalService", " onStartCommand " + this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.D("LocalService", " onDestroy " + this);
    }

    public void startRun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    String text = " 正在运行数据: " + count + " , " + mData;
                    LogUtils.D("LocalService", text);
                    if (mDataChangeListener != null)
                        mDataChangeListener.dataChange(text);
                    if (!mTag)
                        break;
                }
            }
        }).start();
    }

    /**
     * 数据改变监听
     */
    public interface DataChangeListener {
        void dataChange(String data);
    }
}