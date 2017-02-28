package com.yunlong.samples.systemfunction.media;

import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.FileUtils;
import com.yunlong.lib.utils.PermissionUtils;
import com.yunlong.lib.utils.StringsUtils;
import com.yunlong.lib.utils.ToastUtils;
import com.yunlong.samples.R;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/2/28.
 * MediaPlayer界面
 */

public class MediaPlayerActivity extends BaseActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {
    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.MediaPlayer";
    /**
     * 文件请求权限
     */
    private static final int REQUEST_CODE_PERMISSION_STORAGE = 0x1001;
    /**
     * 录音请求权限
     */
    private static final int REQUEST_CODE_PERMISSION_RECORD = 0x1002;
    /**
     * 音频根文件件
     */
    private static final String BASE_MEDIA_FOLDER = "media";
    /**
     * SD卡上的音频文件
     */
    private static final String BASE_MEDIAL_FILE = "jiasudu.mp3";
    /**
     * 录音根文件件
     */
    private static final String BASE_RECORD_FOLDER = "record";
    /**
     * 播放资源文件
     */
    @Bind(R.id.btn_play_raw)
    Button btnPlayRaw;
    /**
     * 是否创建播放资源文件
     */
    private boolean mHasCreateRaw;
    /**
     * 播放资源文件状态
     */
    private boolean mPlayRawStatus;
    /**
     * 播放SD卡文件
     */
    @Bind(R.id.btn_play_sd)
    Button btnPlaySD;
    /**
     * 已经创建了SD卡
     */
    private boolean mHasCreateSD;
    /**
     * 播放SD卡资源文件状态
     */
    private boolean mPlaySDStatus;
    /**
     * 停止播放
     */
    @Bind(R.id.btn_stop)
    Button btnStop;
    /**
     * 能停止播放
     */
    private boolean mCanStop;

    /**
     * 播放器
     */
    private MediaPlayer mMediaPlayer;
    /**
     * 录音
     */
    @Bind(R.id.btn_record)
    Button btnRecord;
    /**
     * 正在录音
     */
    private boolean mIsRecord;
    /**
     * 录音视频路径
     */
    private String mRecordFilePath;
    /**
     * 播放录音视频
     */
    @Bind(R.id.btn_play_record)
    Button btnPlayRecord;
    /**
     * 创建播放录音
     */
    private boolean mHasCreateRecord;
    /**
     * 播放录音
     */
    private boolean mPlayRecordStatus;

    @Override
    protected int getResourceId() {
        return R.layout.a_system_function_mediaplayer;
    }

    @Override
    protected void initView() {
        btnPlayRaw.setOnClickListener(this);
        btnPlaySD.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnRecord.setOnClickListener(this);
        btnPlayRecord.setOnClickListener(this);

        refreshMediaStatus();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_media_player);
        super.initTitleBar();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play_raw:
                checkRawMediaPlayer(false);
                break;
            case R.id.btn_play_sd:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    PermissionUtils.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION_STORAGE);
                } else {
                    checkSDMediaPlayer(false);
                }
                break;
            case R.id.btn_stop:
                stopMediaPlayer();
                break;
            case R.id.btn_record:
                if (mIsRecord) {
                    stopRecord();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        PermissionUtils.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_PERMISSION_STORAGE);
                    } else {
                        startRecord();
                    }
                }
                break;
            case R.id.btn_play_record:
                if (StringsUtils.isEmpty(mRecordFilePath)) {
                    ToastUtils.show(mContext, "还没有开始录音...");
                    return;
                } else if (mIsRecord) {
                    ToastUtils.show(mContext, "正在录音，请先停止录音...");
                    return;
                }
                checkRecordMediaPlayer(false);
                break;
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopMediaPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        checkMediaPlayer();
        if (mIsRecord) {
            stopRecord();
        }
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();
        mHasCreateRaw = false;
        mPlayRawStatus = false;
        mHasCreateSD = false;
        mPlaySDStatus = false;
        mHasCreateRecord = false;
        mPlayRecordStatus = false;
        refreshMediaStatus();
    }

    /**
     * 检查播放器
     */
    private void checkMediaPlayer() {
        if (mIsRecord) {
            stopMediaPlayer();
        } else if (mHasCreateRaw && !mHasCreateSD && !mHasCreateRecord) {
            checkRawMediaPlayer(true);
        } else if (mHasCreateSD && !mHasCreateRaw && !mHasCreateRecord) {
            checkSDMediaPlayer(true);
        } else if (mHasCreateRecord && !mHasCreateRaw && !mHasCreateSD) {
            checkRecordMediaPlayer(true);
        } else {
            stopMediaPlayer();
        }
    }

    /**
     * 检查资源播放
     *
     * @param flag :状态，用于检查是否需要播放
     */
    private void checkRawMediaPlayer(boolean flag) {
        if (mIsRecord) {
            ToastUtils.show(mContext, "正在录音....");
            return;
        }
        if (mHasCreateSD || mHasCreateRecord) {
            stopMediaPlayer();
        }
        if (!mHasCreateRaw || mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(mContext, R.raw.see_you_again);
            mMediaPlayer.setOnCompletionListener(this);
            mMediaPlayer.start();
            mHasCreateRaw = true;
            mPlayRawStatus = true;
            mCanStop = true;
        } else {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
                mPlayRawStatus = false;
            } else if (!flag) {
                mMediaPlayer.start();
                mPlayRawStatus = true;
            }
        }
        refreshMediaStatus();
    }

    /**
     * 检查SD卡播放
     *
     * @param flag
     */
    private void checkSDMediaPlayer(boolean flag) {
        if (mIsRecord) {
            ToastUtils.show(mContext, "正在录音....");
            return;
        }
        String baseMediaFolder = FileUtils.getExtraStoragePath(BASE_MEDIA_FOLDER);
        String mediaFilePath = baseMediaFolder + File.separator + BASE_MEDIAL_FILE;
        File file = new File(mediaFilePath);
        if (!file.exists()) {
            return;
        }
        checkSDMediaPlayer(flag, false, file);
    }

    /**
     * 检查录音播放
     */
    private void checkRecordMediaPlayer(boolean flag) {
        File file = new File(mRecordFilePath);
        if (file.exists()) {
            checkSDMediaPlayer(flag, true, file);
        }
    }

    /**
     * 检查MediaPlayer
     *
     * @param flag
     */
    private void checkSDMediaPlayer(boolean flag, boolean record, File file) {
        if (mHasCreateRaw || (record && mHasCreateSD) || (!record && mHasCreateRecord)) {
            stopMediaPlayer();
        }
        boolean createState = (!record && !mHasCreateSD) || (record && !mHasCreateRecord);

        if (file != null && (createState || mMediaPlayer == null)) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setOnCompletionListener(this);
            try {
                mMediaPlayer.setDataSource(file.getAbsolutePath());
                mMediaPlayer.prepare();
                mMediaPlayer.start();

                if (record) {
                    mHasCreateSD = false;
                    mPlaySDStatus = false;

                    mHasCreateRecord = true;
                    mPlayRecordStatus = true;

                } else {
                    mHasCreateSD = true;
                    mPlaySDStatus = true;

                    mHasCreateRecord = false;
                    mPlayRecordStatus = false;
                }
                mCanStop = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
                if (record) {
                    mPlayRecordStatus = false;
                } else {
                    mPlaySDStatus = false;
                }
            } else if (!flag) {
                mMediaPlayer.start();
                if (record) {
                    mPlayRecordStatus = true;
                } else {
                    mPlaySDStatus = true;
                }
            }
        }
        refreshMediaStatus();
    }

    /**
     * 刷新播放状态
     */
    private void stopMediaPlayer() {
        if (mMediaPlayer != null && mCanStop) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                mCanStop = false;

                mHasCreateRaw = false;
                mPlayRawStatus = false;

                mHasCreateSD = false;
                mPlaySDStatus = false;

                mHasCreateRecord = false;
                mPlayRecordStatus = false;

                refreshMediaStatus();
            }
        }
    }

    /**
     * 刷新播放状态
     */
    private void refreshMediaStatus() {
        refreshRawStatus();
        refreshSDStatus();
        refreshStopStatus();
        refreshRecordStatus();
    }

    /**
     * 刷新资源文件状态
     */
    private void refreshRawStatus() {
        String raw = getString(R.string.a_media_player_raw);
        if (mPlayRawStatus) {
            btnPlayRaw.setText(getString(R.string.a_media_player_pause, raw));
        } else {
            btnPlayRaw.setText(getString(R.string.a_media_player_play, raw));
        }
    }

    /**
     * 刷新SD卡状态
     */
    private void refreshSDStatus() {
        String sd = getString(R.string.a_media_player_sd);
        if (mPlaySDStatus) {
            btnPlaySD.setText(getString(R.string.a_media_player_pause, sd));
        } else {
            btnPlaySD.setText(getString(R.string.a_media_player_play, sd));
        }
    }

    /**
     * 刷新播放录音状态
     */
    private void refreshRecordStatus() {
        String record = getString(R.string.a_media_player_record_file);
        if (mPlayRecordStatus) {
            btnPlayRecord.setText(getString(R.string.a_media_player_pause, record));
        } else {
            btnPlayRecord.setText(getString(R.string.a_media_player_play, record));
        }
    }

    /**
     * 刷新SD卡状态
     */
    private void refreshStopStatus() {
        String stop = "";
        if (mHasCreateRaw)
            stop = getString(R.string.a_media_player_raw);
        else if (mHasCreateSD)
            stop = getString(R.string.a_media_player_sd);
        else if (mHasCreateRecord)
            stop = getString(R.string.a_media_player_record_file);

        btnStop.setText(getString(R.string.a_media_player_stop, stop));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean isGrant = PermissionUtils.isGrant(grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_STORAGE:
                if (isGrant)
                    checkSDMediaPlayer(false);
                else {
                    if (PermissionUtils.showPermissions(this, PermissionUtils.checkPermissions(this, permissions))) {
                        ToastUtils.show(mContext, R.string.a_camera_normal_permission_denied);
                    } else {

                        ToastUtils.show(mContext, R.string.a_camera_normal_permission_setting);
                    }
                }
                break;
            case REQUEST_CODE_PERMISSION_RECORD:
                if (isGrant)
                    startRecord();
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


    MediaRecorder mediaRecorder;

    /**
     * 开始录音
     */
    private void startRecord() {
        mIsRecord = true;

        checkMediaPlayer();
        //录音文件信息
        mRecordFilePath = FileUtils.getExtraStoragePath(BASE_RECORD_FOLDER) + File.separator + System.currentTimeMillis() + ".amr";
        mediaRecorder = new MediaRecorder();
        //录音来源，麦克风
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //设置输出格式
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        //设置编码格式，默认
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        //设置输出文件
        mediaRecorder.setOutputFile(mRecordFilePath);
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        refreshRecord();
    }

    /**
     * 停止录音
     */
    private void stopRecord() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            mIsRecord = false;
        }
        refreshRecord();
    }

    /**
     * 刷新录音
     */
    private void refreshRecord() {
        btnRecord.setText(getString(mIsRecord ? R.string.a_media_player_stop_record : R.string.a_media_player_record));
    }
}
