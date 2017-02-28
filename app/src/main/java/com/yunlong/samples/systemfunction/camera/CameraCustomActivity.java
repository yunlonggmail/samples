package com.yunlong.samples.systemfunction.camera;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.FileUtils;
import com.yunlong.lib.utils.HardwareUtils;
import com.yunlong.lib.utils.PermissionUtils;
import com.yunlong.lib.utils.ToastUtils;
import com.yunlong.samples.R;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/2/27.
 * 定制Camera界面
 */

public class CameraCustomActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.CameraCustom";
    /**
     * 权限申请请求码
     */
    public static final int REQUEST_CODE_PERMISSION_CAMERA = 0x1001;
    /**
     * 硬件照相机
     */
    private Camera mCamera;

    /**
     * 初始化
     */
    @Bind(R.id.btn_init)
    Button btnInit;

    /**
     * 拍照
     */
    @Bind(R.id.btn_photo)
    Button btnPhoto;
    /**
     * 图片展示
     */
    @Bind(R.id.iv_image)
    ImageView ivImage;

    /**
     * 照相机展示信息
     */
    @Bind(R.id.cv_preview)
    CameraView cvPreview;
    /**
     * 图片路径
     */
    private String imagePath = "image";

    @Override
    protected int getResourceId() {
        return R.layout.a_system_function_camera_custom;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_camera_custom);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        if (!HardwareUtils.checkCameraHardware(mContext)) {
            finish();
        }
        btnPhoto.setOnClickListener(this);
        btnInit.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_init:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    PermissionUtils.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSION_CAMERA);
                } else {
                    initCamera();
                }
                break;
            case R.id.btn_photo:
                takePhoto();
                break;
        }
    }

    /**
     * 照相功能
     */
    private void takePhoto() {
        String basePath = FileUtils.getExtraStoragePath(imagePath);
        final String filePath = basePath + File.separator + System.currentTimeMillis() + ".jpg";
        final File file = new File(filePath);
        mCamera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                mCamera.startPreview();
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(data);
                    fos.close();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                            ivImage.setImageBitmap(bitmap);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean isGrant = PermissionUtils.isGrant(grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_CAMERA:
                if (isGrant)
                    initCamera();
                else {
                    if (PermissionUtils.showPermissions(this, PermissionUtils.checkPermissions(this, permissions))) {
                        ToastUtils.show(mContext, R.string.a_camera_normal_permission_denied);
                    } else {

                        ToastUtils.show(mContext, R.string.a_camera_normal_permission_setting);
                    }
                }
                break;
        }
    }

    /**
     * 初始化Camera
     */
    private void initCamera() {
        if (mCamera == null)
            mCamera = Camera.open();
        if (mCamera != null)
            cvPreview.init(mCamera);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCamera != null) {
            cvPreview.setRelease(true);
            mCamera.release();
            mCamera = null;
            cvPreview.init(null);
        }
    }
}
