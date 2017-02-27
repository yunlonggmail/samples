package com.yunlong.samples.systemfunction.camera;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.FileUtils;
import com.yunlong.lib.utils.PermissionUtils;
import com.yunlong.lib.utils.ToastUtils;
import com.yunlong.samples.R;

import java.io.File;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/2/27.
 * 正常Activity
 */

public class CameraNormalActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.CameraNormal";
    /**
     * 权限申请请求码
     */
    public static final int REQUEST_CODE_PERMISSION_CAMERA = 0x1001;
    /**
     * 录像权限申请码
     */
    public static final int REQUEST_CODE_PERMISSION_VIDEO = 0x1002;
    /**
     * 照相请求码
     */
    public static final int REQUEST_CODE_CAPTURE = 0x1003;
    /**
     * 录像请求码
     */
    public static final int REQUEST_CODE_RECORD = 0x1004;
    /**
     * 存储的图片路径Uri
     */
    private Uri mImageUri;
    /**
     * 存储的图片路径Uri
     */
    private Uri mCameraUri;
    /**
     * 图片路径
     */
    private String imagePath = "image";
    /**
     * 录像路径
     */
    private String videoPath = "video";

    /**
     * 拍照功能
     */
    @Bind(R.id.btn_photo)
    Button btnPhoto;
    /**
     * 拍照后显示
     */
    @Bind(R.id.iv_image)
    ImageView ivImage;
    /**
     * 录像功能
     */
    @Bind(R.id.btn_video)
    Button btnVideo;
    /**
     * 录像显示
     */
    @Bind(R.id.vv_video)
    VideoView videoView;

    @Override
    protected int getResourceId() {
        return R.layout.a_system_function_camera_normal;
    }

    @Override
    protected void initView() {
        btnPhoto.setOnClickListener(this);
        btnVideo.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_photo:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    PermissionUtils.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSION_CAMERA);
                } else {
                    takePhoto();
                }
                break;
            case R.id.btn_video:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    PermissionUtils.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSION_VIDEO);
                } else {
                    recordVideo();
                }
                break;
        }
    }

    /**
     * 照相功能
     */
    private void takePhoto() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String basePath = FileUtils.getExtraStoragePath(imagePath);
        String filePath = basePath + File.separator + System.currentTimeMillis() + ".jpg";
        File file = new File(filePath);
        mImageUri = Uri.fromFile(file);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        startActivityForResult(cameraIntent, REQUEST_CODE_CAPTURE);
    }

    /**
     * 录像功能
     */
    private void recordVideo() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        String basePath = FileUtils.getExtraStoragePath(videoPath);
        String filePath = basePath + File.separator + System.currentTimeMillis() + ".mp4";
        File file = new File(filePath);
        mCameraUri = Uri.fromFile(file);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCameraUri);
        startActivityForResult(cameraIntent, REQUEST_CODE_RECORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    if (bitmap != null)
                        ivImage.setImageBitmap(bitmap);
                    ToastUtils.show(mContext, "拍照成功");
                }
                break;
            case REQUEST_CODE_RECORD:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        Uri uri = data.getData();
                        if (uri != null) {
                            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                            if (cursor != null && cursor.moveToFirst()) {
                                String videoPath = cursor.getString(cursor.getColumnIndex("_data"));
                                videoView.setVideoPath(videoPath);
                                videoView.setMediaController(new MediaController(mContext));
                            }
                        }
                    } else if (mCameraUri != null) {
                        //ToastUtils.show(mContext, "如果设置outputUri，则界面返回时不携带数据");
                        videoView.setVideoPath(mCameraUri.getPath());
                        videoView.setMediaController(new MediaController(mContext));
                    } else {
                        ToastUtils.show(mContext, "无数据返回");
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean isGrant = PermissionUtils.isGrant(grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_CAMERA:
                if (isGrant)
                    takePhoto();
                else {
                    if (PermissionUtils.showPermissions(this, PermissionUtils.checkPermissions(this, permissions))) {
                        ToastUtils.show(mContext, R.string.a_camera_normal_permission_denied);
                    } else {

                        ToastUtils.show(mContext, R.string.a_camera_normal_permission_setting);
                    }
                }
                break;
            case REQUEST_CODE_PERMISSION_VIDEO:
                if (isGrant)
                    recordVideo();
                else {
                    if (PermissionUtils.showPermissions(this, PermissionUtils.checkPermissions(this, permissions))) {
                        ToastUtils.show(mContext, R.string.a_camera_normal_permission_denied);
                    } else {

                        ToastUtils.show(mContext, R.string.a_camera_normal_permission_setting);
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
