package com.yunlong.samples.service.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.yunlong.lib.utils.LogUtils;
import com.yunlong.samplelibrary.IRemoteService;
import com.yunlong.samplelibrary.ISamplesRemoteDataChangeListener;

public class RemoteService extends Service {
    /**
     * 计数
     */
    public int count = 0;

    public String mData = "Remote默认数据";
    /**
     * 回调
     */
    public ISamplesRemoteDataChangeListener mDataChangeListener;
    /**
     * 线程运行Tag
     */
    public boolean mRunning;

    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new RemoteServiceBinder();
    }

    /**
     * 服务信息
     */
    public class RemoteServiceBinder extends IRemoteService.Stub {

        public void updateData(String data) {
            count = 0;
            mData = data;
        }

        public void setDataChangeListener(ISamplesRemoteDataChangeListener dataChangeListener) {
            mDataChangeListener = dataChangeListener;
        }

        public String getData() {
            return count + "," + mData;
        }

        public void setRunning(boolean running) {
            mRunning = running;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.D("RemoteService", " onCreate " + this);
        startRun();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.D("RemoteService", " onStartCommand " + this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRunning = false;
        LogUtils.D("RemoteService", " onDestroy " + this);
    }

    public void startRun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mRunning = true;
                while (mRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    String text = " 正在运行数据: " + count + " , " + mData;
                    LogUtils.D("RemoteService", text);
                    try {
                        if (mDataChangeListener != null)
                            mDataChangeListener.dataChange(text);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}