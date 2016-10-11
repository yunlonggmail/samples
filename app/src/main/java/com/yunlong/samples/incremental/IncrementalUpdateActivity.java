package com.yunlong.samples.incremental;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.yunlong.samples.R;
import com.yunlong.samples.base.BaseActivity;
import com.yunlong.samples.utils.PermissionUtils;

import java.io.File;
import java.io.IOException;


/**
 * Created by shiyunlong on 2016/10/11.
 * 增量更新界面
 */
public class IncrementalUpdateActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 请求摄像头权限requestCode
     */
    private static final int PERMISSION_CAMERA_REQUEST_CODE = 101;
    /**
     * 意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.IncrementalUpdate";
    /**
     * 文件夹
     */
    public static final String FILE_PACKAGE = "samples" + File.separator + "increment";
    /**
     * 新的apk
     */
    public static final String NEW_APK_FILE_NAME = "new_apk.apk";
    /**
     * 分支
     */
    public static final String PATCH_FILE_NAME = "patch.patch";

    @Override
    protected int getResourceId() {
        return R.layout.a_increment_update;
    }

    @Override
    protected void initTitleBar() {
        toolbar.setTitle(R.string.nav_title_increment_update);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        Button btnGenerate = (Button) findViewById(R.id.btn_generate);
        Button btnInstall = (Button) findViewById(R.id.btn_install);
        if (btnGenerate != null)
            btnGenerate.setOnClickListener(this);
        if (btnInstall != null)
            btnInstall.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_generate:
                checkStoragePermission();
                break;
            case R.id.btn_install:
                String apkPath = Environment.getExternalStorageDirectory() + File.separator + FILE_PACKAGE + File.separator + NEW_APK_FILE_NAME;
                install(apkPath);
                break;
        }
    }

    public void checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionUtils.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_CAMERA_REQUEST_CODE);
        } else {
            bsPatch();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean isGrant = PermissionUtils.isGrant(grantResults);
        switch (requestCode) {
            case PERMISSION_CAMERA_REQUEST_CODE:
                if (isGrant) {
                    bsPatch();
                } else {
                    if (!PermissionUtils.showPermissions(this, PermissionUtils.checkPermissions(this, permissions)))
                        PermissionUtils.showNeverAskDialog(this, permissions);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 开始合成新的apk包
     */
    public void bsPatch() {
        if (checkFile()) {
            String newAPKFilePath = Environment.getExternalStorageDirectory() + File.separator + FILE_PACKAGE + File.separator + NEW_APK_FILE_NAME;
            String patchFilePath = Environment.getExternalStorageDirectory() + File.separator + FILE_PACKAGE + File.separator + PATCH_FILE_NAME;
            BsPatch.bspatch(ApkExtract.extract(this), newAPKFilePath, patchFilePath);
        }
    }

    /**
     * 检查文件是否存在
     */
    public boolean checkFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File filePackage = new File(FILE_PACKAGE);
            if (!filePackage.exists())
                filePackage.mkdirs();
            File oldAPKFile = new File(ApkExtract.extract(this));
            File newAPKFile = new File(Environment.getExternalStorageDirectory() + File.separator + FILE_PACKAGE + File.separator + NEW_APK_FILE_NAME);
            if (!newAPKFile.exists()) {
                try {
                    newAPKFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            File patchFile = new File(Environment.getExternalStorageDirectory() + File.separator + FILE_PACKAGE + File.separator + PATCH_FILE_NAME);
            return oldAPKFile.exists() && newAPKFile.exists() && patchFile.exists();
        }
        return false;
    }

    /**
     * 安装
     *
     * @param apkPath
     */
    public void install(String apkPath) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.fromFile(new File(apkPath)),
                "application/vnd.android.package-archive");
        startActivity(i);
    }
}
