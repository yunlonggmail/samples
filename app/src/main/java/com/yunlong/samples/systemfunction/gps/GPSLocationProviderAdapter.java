package com.yunlong.samples.systemfunction.gps;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yunlong.lib.base.BaseAdapter;
import com.yunlong.samples.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shiyunlong on 2017/2/24.
 * GPSLocationProvider
 */

public class GPSLocationProviderAdapter extends BaseAdapter<String> {

    @Bind(R.id.tv_gps_location_provider)
    TextView tvGPSLocationProvider;

    public GPSLocationProviderAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    public OnItemClickListener getOnItemClickListener() {
        return null;
    }

    @Override
    public int getResourceId(int viewType) {
        return R.layout.i_system_function_gps_location_provider;
    }

    @Override
    public void bindView(View itemView, int position) {
        ButterKnife.bind(this, itemView);
        String locationProvider = getItem(position);
        tvGPSLocationProvider.setText(locationProvider);
    }

}
