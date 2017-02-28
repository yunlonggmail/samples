package com.yunlong.samples.systemfunction.ringtone;

import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/2/28.
 * 铃声设置
 * 其实闹钟的设置只是设置了默认的铃声，并不会修改已有闹钟的铃声
 */

public class RingtoneManagerActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 隐式意图Action
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.RingtoneManager";
    /**
     * 打电话
     */
    public static final int REQUEST_CODE_RINGTONE_CALL = 0x1001;
    /**
     * 闹钟
     */
    public static final int REQUEST_CODE_RINGTONE_ALARM = 0x1002;
    /**
     * 通知
     */
    public static final int REQUEST_CODE_RINGTONE_NOTIFICATION = 0x1003;
    /**
     * 设置来电铃声
     */
    @Bind(R.id.btn_ringtone_call)
    Button btnRingtoneCall;
    /**
     * 设置闹钟铃声
     */
    @Bind(R.id.btn_ringtone_alarm)
    Button btnRingtoneAlarm;
    /**
     * 设置通知铃声
     */
    @Bind(R.id.btn_ringtone_notification)
    Button btnRingtoneNotification;

    @Override
    protected int getResourceId() {
        return R.layout.a_system_function_ringtone;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_ringtone);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnRingtoneCall.setOnClickListener(this);
        btnRingtoneAlarm.setOnClickListener(this);
        btnRingtoneNotification.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ringtone_call:
                selectRingtone(getString(R.string.a_ringtone_call), RingtoneManager.TYPE_RINGTONE, REQUEST_CODE_RINGTONE_CALL);
                break;
            case R.id.btn_ringtone_alarm:
                selectRingtone(getString(R.string.a_ringtone_call), RingtoneManager.TYPE_ALARM, REQUEST_CODE_RINGTONE_ALARM);
                break;
            case R.id.btn_ringtone_notification:
                selectRingtone(getString(R.string.a_ringtone_call), RingtoneManager.TYPE_NOTIFICATION, REQUEST_CODE_RINGTONE_NOTIFICATION);
                break;
        }
    }

    /**
     * 设置铃声
     *
     * @param title：标题
     * @param ringtoneType：类型
     * @param requestCode：请求码
     */
    private void selectRingtone(String title, int ringtoneType, int requestCode) {
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, title);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, ringtoneType);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
        if (uri != null) {
            switch (requestCode) {
                case REQUEST_CODE_RINGTONE_CALL:
                    setRingtone(RingtoneManager.TYPE_RINGTONE, uri);
                    break;
                case REQUEST_CODE_RINGTONE_ALARM:
                    setRingtone(RingtoneManager.TYPE_ALARM, uri);
                    break;
                case REQUEST_CODE_RINGTONE_NOTIFICATION:
                    setRingtone(RingtoneManager.TYPE_NOTIFICATION, uri);
                    break;
            }
        }
    }

    /**
     * 设置铃声
     *
     * @param ringtoneType：类型
     */
    private void setRingtone(int ringtoneType, Uri uri) {
        RingtoneManager.setActualDefaultRingtoneUri(mContext, ringtoneType, uri);
    }
}
