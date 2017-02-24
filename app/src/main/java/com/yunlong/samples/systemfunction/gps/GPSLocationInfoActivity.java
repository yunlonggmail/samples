package com.yunlong.samples.systemfunction.gps;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.ToastUtils;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/2/24.
 * LocationInfo
 * 关于临近位置未实际验证
 */

public class GPSLocationInfoActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.GPSLocationInfo";

    @Bind(R.id.tv_gps_location_info)
    TextView tvGPSLocationInfo;

    /**
     * LocationManager
     */
    private LocationManager mLocationManager;
    /**
     * 位置信息
     */
    private Location mLocation;
    /**
     * 纬度
     */
    @Bind(R.id.et_location_latitude)
    EditText etLatitude;
    /**
     * 经度
     */
    @Bind(R.id.et_location_longitude)
    EditText etLongitude;
    /**
     * 计算
     */
    @Bind(R.id.btn_calc)
    Button btnCalc;
    /**
     * 位置距离
     */
    @Bind(R.id.tv_gps_location_distance)
    TextView tvGPSDistance;

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            checkLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            if (!checkPermission())
                return;
            checkLocation(mLocationManager.getLastKnownLocation(provider));
        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected int getResourceId() {
        return R.layout.a_system_function_gps_location_info;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_gps_location_info);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnCalc.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (!checkPermission())
            return;
        Location mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        checkLocation(mLocation);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 8.0f, locationListener);

        checkProximity();
    }

    /**
     * 检查位置信息
     */
    private void checkLocation(Location location) {
        mLocation = location;
        String str = "";
        str += "手机的位置信息\n";
        str += "经度：" + location.getLongitude() + "\n";
        str += "纬度：" + location.getLatitude() + "\n";
        str += "高度：" + location.getAltitude() + "\n";
        str += "速度：" + location.getSpeed() + "\n";
        str += "方向：" + location.getBearing() + "\n";
        tvGPSLocationInfo.setText(str);
    }

    /**
     * isPermissionGranted
     *
     * @return
     */
    private boolean checkPermission() {
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        boolean isOK = true;
        for (String permission : permissions) {
            isOK &= (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED);
        }
        return isOK;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_calc:
                calcDistance();
                break;
        }
    }

    /**
     * 计算距离
     */
    private void calcDistance() {
        String latitudeStr = etLatitude.getText().toString();
        String longitudeStr = etLongitude.getText().toString();

        try {
            float latitude = Float.parseFloat(latitudeStr);
            float longitude = Float.parseFloat(longitudeStr);

            float[] results = new float[1];
            Location.distanceBetween(latitude, longitude, mLocation.getLatitude(), mLocation.getLongitude(), results);
            String distance = results[0] + "米";
            tvGPSDistance.setText(distance);

        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.show(mContext, "错误的经度或错误的纬度");
        }
    }

    /**
     * 意图主要KEY
     */
    private final String PROXIMITY_ALERT = "location.PROXIMITY_ALERT";
    /**
     * 意图
     */
    private PendingIntent mPendingIntent;
    /**
     * 广播接收者
     */
    GPSLocationProximityReceiver mGPSLocationProximityReceiver;

    /**
     * 检查临近
     */
    private void checkProximity() {

        double latitude = 39.9777;
        double longitude = 116.4756;
        Intent intent = new Intent(this, GPSLocationProximityReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 1, intent, 0);
        if (!checkPermission()) {
            return;
        }
        mLocationManager.addProximityAlert(latitude, longitude, 100, -1, pendingIntent);

        GPSLocationProximityReceiver gpsLocationProximityReceiver = new GPSLocationProximityReceiver();
        IntentFilter intentFilter = new IntentFilter(PROXIMITY_ALERT);
        intentFilter.addDataScheme("geo");
        registerReceiver(gpsLocationProximityReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        if (!checkPermission())
            mLocationManager.removeProximityAlert(mPendingIntent);
        super.onPause();
    }
}
