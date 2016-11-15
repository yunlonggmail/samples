package com.yunlong.samples.receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2016/11/11.
 * 广播主界面
 */

public class ReceiverMainActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 广播主页面
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.ReceiverMain";
    /**
     * 第一个广播信息
     */
    @Bind(R.id.tv_first_receiver)
    public TextView tvFirstReceiver;
    /**
     * 第二个广播信息
     */
    @Bind(R.id.tv_second_receiver)
    public TextView tvSecondReceiver;
    /**
     * 第一个广播优先级
     */
    @Bind(R.id.et_first_receiver_priority)
    public EditText etFirstReceiverPriority;
    /**
     * 第二个广播优先级
     */
    @Bind(R.id.et_second_receiver_priority)
    public EditText etSecondReceiverPriority;
    /**
     * 注册第一个广播
     */
    @Bind(R.id.btn_register_first_receiver)
    public Button btnRegisterFirstReceiver;
    /**
     * 解除注册第一个广播
     */
    @Bind(R.id.btn_unregister_first_receiver)
    public Button btnUnRegisterSecondReceiver;
    /**
     * 注册第二个广播
     */
    @Bind(R.id.btn_register_second_receiver)
    public Button btnRegisterSecondReceiver;
    /**
     * 解除注册第二个广播
     */
    @Bind(R.id.btn_unregister_second_receiver)
    public Button btnUnRegisterFirstReceiver;
    /**
     * 发送广播
     */
    @Bind(R.id.btn_send_broadcast)
    public Button btnSendBroadcast;
    /**
     * 发送有序广播
     */
    @Bind(R.id.btn_send_order_broadcast)
    public Button btnSendOrderBroadcast;
    /**
     * 发送可拦截的有序广播
     */
    @Bind(R.id.btn_send_abort_order_broadcast)
    public Button btnSendAbortOrderBroadcast;
    /**
     * 第一个广播
     */
    public FirstBroadcastReceiver firstBroadcastReceiver;
    /**
     * 第二个广播
     */
    public SecondBroadcastReceiver secondBroadcastReceiver;


    @Override
    protected int getResourceId() {
        return R.layout.a_receiver_main;
    }

    @Override
    protected void initView() {
        btnRegisterFirstReceiver.setOnClickListener(this);
        btnUnRegisterFirstReceiver.setOnClickListener(this);
        btnRegisterSecondReceiver.setOnClickListener(this);
        btnUnRegisterSecondReceiver.setOnClickListener(this);
        btnSendBroadcast.setOnClickListener(this);
        btnSendOrderBroadcast.setOnClickListener(this);
        btnSendAbortOrderBroadcast.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register_first_receiver:
                registerFirstReceiver();
                break;
            case R.id.btn_unregister_first_receiver:
                unRegisterFirstReceiver();
                break;
            case R.id.btn_register_second_receiver:
                registerSecondReceiver();
                break;
            case R.id.btn_unregister_second_receiver:
                unRegisterSecondReceiver();
                break;
            case R.id.btn_send_broadcast:
                sendBroadcast();
                break;
            case R.id.btn_send_order_broadcast:
                sendOrderBroadcast();
                break;
            case R.id.btn_send_abort_order_broadcast:
                sendAbortOrderBroadcast();
                break;
        }
    }

    /**
     * 注册第一个广播
     */
    public void registerFirstReceiver() {
        if (firstBroadcastReceiver == null) {
            firstBroadcastReceiver = new FirstBroadcastReceiver() {
                @Override
                public void receiverData(boolean abort) {
                    String text = System.currentTimeMillis() + "；" + abort + " first receiver";
                    tvFirstReceiver.setText(text);
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            String priorityText = etFirstReceiverPriority.getText().toString();
            int priority = -1;
            if (!TextUtils.isEmpty(priorityText)) {
                priority = Integer.parseInt(priorityText);
            }
            if (priority > -1)
                intentFilter.setPriority(priority);
            intentFilter.addAction(ReceiverConfig.INTENT_ACTION);
            registerReceiver(firstBroadcastReceiver, intentFilter);
        }
    }

    /**
     * 解除第一个广播的注册
     */
    public void unRegisterFirstReceiver() {
        if (firstBroadcastReceiver != null) {
            unregisterReceiver(firstBroadcastReceiver);
            firstBroadcastReceiver = null;
        }
    }

    /**
     * 注册第二个广播
     */
    public void registerSecondReceiver() {
        if (secondBroadcastReceiver == null) {
            secondBroadcastReceiver = new SecondBroadcastReceiver() {
                @Override
                public void receiverData(boolean abort) {
                    String text = System.currentTimeMillis() + "；" + abort + " second receiver";
                    tvSecondReceiver.setText(text);
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            String priorityText = etSecondReceiverPriority.getText().toString();
            int priority = -1;
            if (!TextUtils.isEmpty(priorityText)) {
                priority = Integer.parseInt(priorityText);
            }
            if (priority > -1)
                intentFilter.setPriority(priority);
            intentFilter.addAction(ReceiverConfig.INTENT_ACTION);
            registerReceiver(secondBroadcastReceiver, intentFilter);
        }
    }

    /**
     * 解除第二个广播的注册
     */
    public void unRegisterSecondReceiver() {
        if (secondBroadcastReceiver != null) {
            unregisterReceiver(secondBroadcastReceiver);
            secondBroadcastReceiver = null;
        }
    }

    /**
     * 发送广播
     */
    public void sendBroadcast() {
        clearData();
        Intent intent = new Intent();
        intent.setAction(ReceiverConfig.INTENT_ACTION);
        intent.putExtra(ReceiverConfig.ABORT_KEY, false);
        sendBroadcast(intent);
    }

    /**
     * 发送有序广播
     */
    public void sendOrderBroadcast() {
        clearData();
        Intent intent = new Intent();
        intent.setAction(ReceiverConfig.INTENT_ACTION);
        intent.putExtra(ReceiverConfig.ABORT_KEY, false);
        sendOrderedBroadcast(intent, null);
    }

    /**
     * 发送可拦截有序广播
     */
    public void sendAbortOrderBroadcast() {
        clearData();
        Intent intent = new Intent();
        intent.setAction(ReceiverConfig.INTENT_ACTION);
        intent.putExtra(ReceiverConfig.ABORT_KEY, true);
        sendOrderedBroadcast(intent, null);
    }

    /**
     * 清除数据
     */
    public void clearData() {
        tvFirstReceiver.setText("");
        tvSecondReceiver.setText("");
    }

    @Override
    protected void onDestroy() {
        unRegisterFirstReceiver();
        unRegisterSecondReceiver();
        super.onDestroy();
    }
}
