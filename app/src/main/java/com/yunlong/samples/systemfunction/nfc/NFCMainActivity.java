package com.yunlong.samples.systemfunction.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

/**
 * Created by shiyunlong on 2017/2/16.
 * NFC主界面
 */

public class NFCMainActivity extends BaseActivity {
    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.NFCMain";
    /**
     * 适配器
     */
    private NfcAdapter mNfcAdapter;

    private PendingIntent mPendingNFCIntent;

    @Override
    protected int getResourceId() {
        return R.layout.a_system_function_nfc_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_nfc_main);
        super.initTitleBar();
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(mContext);
        mPendingNFCIntent = PendingIntent.getActivity(mContext, 0, new Intent(mContext, getClass()), 0);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        try {
            Ndef ndef = Ndef.get(detectedTag);

            if (ndef != null) {
                ndef.connect();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null)
            mNfcAdapter.enableForegroundDispatch(this, mPendingNFCIntent, null, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null)
            mNfcAdapter.disableForegroundDispatch(this);
    }
}
