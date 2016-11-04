package com.yunlong.samples.service.local;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by shiyunlong on 2016/11/2.
 * 本地服务连接
 */
public class LocalServiceConnection implements ServiceConnection {

    public static LocalServiceConnection localServiceConnection;

    public LocalServiceConnectionListener mLocalServiceConnectionListener;

    public LocalServiceConnection(LocalServiceConnectionListener localServiceConnectionListener) {
        mLocalServiceConnectionListener = localServiceConnectionListener;
    }

    public static LocalServiceConnection newInstance(LocalServiceConnectionListener localServiceConnectionListener) {
        if (localServiceConnection == null)
            localServiceConnection = new LocalServiceConnection(localServiceConnectionListener);
        return localServiceConnection;
//        return new LocalServiceConnection(localServiceConnectionListener);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (mLocalServiceConnectionListener != null)
            mLocalServiceConnectionListener.onServiceConnected(componentName, iBinder);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        if (mLocalServiceConnectionListener != null)
            mLocalServiceConnectionListener.onServiceDisconnected(componentName);

    }

    /**
     * 连接监听
     */
    public interface LocalServiceConnectionListener {
        void onServiceConnected(ComponentName componentName, IBinder iBinder);

        void onServiceDisconnected(ComponentName componentName);
    }

}
