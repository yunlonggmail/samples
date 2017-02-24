package com.yunlong.samples.systemfunction.gps;

import android.location.Criteria;
import android.location.LocationManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

import java.util.List;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/2/24.
 * GPSLocationProvider
 */

public class GPSLocationProviderActivity extends BaseActivity {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.GPSLocationProvider";
    /**
     * 位置管理器
     */
    private LocationManager mLocationManager;
    /**
     * 所有的LocationProviders
     */
    private List<String> mLocationProviders;
    /**
     * 所有的GPSLocationProvder
     */
    private GPSLocationProviderAdapter mGPSLocationProviderAdapter;

    @Bind(R.id.rv_list)
    RecyclerView rvData;

    @Override
    protected int getResourceId() {
        return R.layout.a_system_function_gps_location_provider;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_gps_location_provider);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //获取LocationManager对象
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //过滤条件，标识
        Criteria criteria = new Criteria();
        //是否有消耗
        criteria.setCostAllowed(false);
        //是否要求高度
        criteria.setAltitudeRequired(true);
        //是否要求方向
        //criteria.setBearingAccuracy(Criteria.ACCURACY_MEDIUM);
        criteria.setBearingRequired(true);

        mLocationProviders = mLocationManager.getProviders(criteria, true);
//        mLocationProviders = new ArrayList<>();
//        mLocationProviders.add(mLocationManager.getBestProvider(criteria, true));
        //mLocationProviders = mLocationManager.getAllProviders();


        mGPSLocationProviderAdapter = new GPSLocationProviderAdapter(mContext, mLocationProviders);
        rvData.setAdapter(mGPSLocationProviderAdapter);
        rvData.setLayoutManager(new LinearLayoutManager(mContext));
    }
}
