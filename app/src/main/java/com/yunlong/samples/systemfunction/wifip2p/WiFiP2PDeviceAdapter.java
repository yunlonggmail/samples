package com.yunlong.samples.systemfunction.wifip2p;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunlong.lib.base.BaseAdapter;
import com.yunlong.lib.utils.StringsUtils;
import com.yunlong.samples.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shiyunlong on 2017/2/20.
 * WiFi P2P 设备适配器
 */

public class WiFiP2PDeviceAdapter extends BaseAdapter<WifiP2pDevice> {
    /**
     * 父布局
     */
    @Bind(R.id.ll_parent)
    LinearLayout llParent;
    /**
     * 名称
     */
    @Bind(R.id.tv_wifi_p2p_device_name)
    TextView tvWiFiP2PName;
    /**
     * 地址
     */
    @Bind(R.id.tv_wifi_p2p_device_address)
    TextView tvWiFiP2PAddress;

    /**
     * 选中条目
     */
    private int mSelectedItem = -1;
    /**
     * 条目点击时间
     */
    private OnItemClickListener mOnItemClickListener;

    public WiFiP2PDeviceAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    @Override
    public int getResourceId(int viewType) {
        return R.layout.i_system_function_wifi_p2p_device;
    }

    @Override
    public void bindView(View itemView, int position) {
        ButterKnife.bind(this, itemView);

        WifiP2pDevice wifiP2pDevice = getItem(position);

        String name = wifiP2pDevice.deviceName;
        if (StringsUtils.isEmpty(name))
            name = wifiP2pDevice.deviceAddress;
        tvWiFiP2PName.setText(name);
        tvWiFiP2PAddress.setText(wifiP2pDevice.deviceAddress);

        if (mSelectedItem == position)
            llParent.setBackgroundColor(mContext.getResources().getColor(R.color.color_normal_9));
        else
            llParent.setBackgroundColor(mContext.getResources().getColor(R.color.color_normal_f));
    }

    /**
     * 设置条目点击事件
     *
     * @param onItemClickListener：条目点击时间
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 选中条目
     *
     * @param position
     */
    public void setSelectItem(int position) {
        mSelectedItem = position;
    }
}
