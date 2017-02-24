package com.yunlong.samples.systemfunction.gps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

import com.yunlong.lib.utils.ToastUtils;

/**
 * Created by shiyunlong on 2017/2/24.
 * 位置临近广播
 */

public class GPSLocationProximityReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isEnter = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, false);
        if (isEnter) {
            ToastUtils.show(context, "已经进入该区域");
        } else {
            ToastUtils.show(context, "已经离开该区域");
        }
    }
}
