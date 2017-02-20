package com.yunlong.samples.systemfunction.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
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
 * 蓝牙设备适配器
 */

public class BluetoothDeviceAdapter extends BaseAdapter<BluetoothDevice> {
    /**
     * 父布局
     */
    @Bind(R.id.ll_parent)
    LinearLayout llParent;

    /**
     * 蓝牙名称
     */
    @Bind(R.id.tv_bluetooth_device_name)
    TextView tvBluetoothName;

    /**
     * 蓝牙地址
     */
    @Bind(R.id.tv_bluetooth_device_address)
    TextView tvBluetoothAddress;
    /**
     * 选中条目
     */
    private int mSelectedItem;

    private OnItemClickListener mOnItemClickListener;

    public BluetoothDeviceAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    @Override
    public int getResourceId(int viewType) {
        return R.layout.i_system_function_bluetooth_device;
    }

    @Override
    public void bindView(View itemView, int position) {
        ButterKnife.bind(this, itemView);

        BluetoothDevice bluetoothDevice = getItem(position);

        String name = bluetoothDevice.getName();
        if (StringsUtils.isEmpty(name))
            name = bluetoothDevice.getAddress();
        tvBluetoothName.setText(name);
        tvBluetoothAddress.setText(bluetoothDevice.getAddress());

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
