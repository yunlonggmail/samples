package com.yunlong.samples.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by shiyunlong on 2016/11/14.
 * 第一个广播
 */

public abstract class FirstBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ReceiverConfig.INTENT_ACTION)) {
            boolean abort = intent.getBooleanExtra(ReceiverConfig.ABORT_KEY, false);
            if (abort)
                abortBroadcast();
            receiverData(abort);
        }
    }


    public abstract void receiverData(boolean abort);
}
