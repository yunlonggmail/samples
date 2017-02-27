package com.yunlong.samples.systemfunction.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

/**
 * Created by shiyunlong on 2017/2/27.
 * 摄像机View
 */

public class CameraView extends ViewGroup implements SurfaceHolder.Callback {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * surfaceView
     */
    private SurfaceView mSurfaceView;
    /**
     * SurfaceHolder
     */
    private SurfaceHolder surfaceHolder;

    /**
     * Camera类
     */
    private Camera mCamera;
    /**
     * 预览的大小
     */
    private Camera.Size mPreviewSize;
    /**
     * 支持的尺度大小集合
     */
    private List<Camera.Size> mSupportedPictureSizes;
    /**
     * 是否初始化过
     */
    private boolean mHasInit;
    /**
     * Camera是否释放过
     */
    private boolean mHasRelease;

    public CameraView(Context context) {
        this(context, null);
    }

    public CameraView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    /**
     * 图片存储路径
     *
     * @param camera：照相机
     */
    public void init(Camera camera) {
        if (mHasInit)
            return;
        mHasInit = true;
        this.mCamera = camera;
        if (mCamera != null) {
            mSupportedPictureSizes = mCamera.getParameters().getSupportedPictureSizes();
            requestLayout();

            mSurfaceView = new SurfaceView(mContext);
            removeAllViews();
            addView(mSurfaceView);
            surfaceHolder = mSurfaceView.getHolder();
            surfaceHolder.addCallback(this);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() > 0) {
            final View child = getChildAt(0);

            //ViewGroup的宽高
            int width = r - l;
            int height = b - t;

            //预览宽高默认为根布局宽高
            int previewWidth = width;
            int previewHeight = height;

            //如果有预览图，就将预览的宽高设置为预览宽高
            if (mPreviewSize != null) {
                previewWidth = mPreviewSize.width;
                previewHeight = mPreviewSize.height;
            }

            //ViewGroup的宽高比
            double scaleVP = (double) width / height;
            //预览图片宽高比
            double scalePreview = (double) previewWidth / previewHeight;

            if (scaleVP > scalePreview) {//此时表示，预览宽度不合适
                //将预览宽度变化，变化至ViewGroup等比，高度为ViewGroup高度
                int scaledChildWith = previewWidth * height / previewHeight;
                child.layout((width - scaledChildWith) / 2, t, (width + scaledChildWith) / 2, b);
            } else {//此时表示预览高度不合适
                //将预览高度变化，变化值ViewGroup等比，宽度为ViewGroup宽度
                int scaledChildHeight = previewHeight * width / previewWidth;
                child.layout(l, (height - scaledChildHeight) / 2, r, (height + scaledChildHeight) / 2);
            }

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获得尽可能小的宽高
        int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
        if (width == 0 || height == 0)
            return;
        if (mSupportedPictureSizes != null) {
            mPreviewSize = getOptimalPreviewSize(mSupportedPictureSizes, width, height);
        }
    }

    /**
     * 获取接近的Size
     *
     * @param sizes：提供的各种Size集合
     * @param w：展示窗口宽度，child    ,surfaceview
     * @param h：展示窗口高度，child    ,surfaceview
     */
    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        if (sizes == null)
            return null;
        //目标的宽高比
        double targetRatio = (double) (w / h);
        Camera.Size optimalSize = null;
        int targetHeight = h;

        optimalSize = checkSize(sizes, targetHeight, targetRatio);
        if (optimalSize == null)
            optimalSize = checkSize(sizes, targetHeight, 0);
        return optimalSize;
    }

    /**
     * 检查Size
     *
     * @param sizes             ：Camera提供的Size集合
     * @param targetRatio:目标宽高比
     */
    private Camera.Size checkSize(List<Camera.Size> sizes, int targetHeight, double targetRatio) {
        Camera.Size optimalSize = null;
        //方面公差，使得宽高比不至于差距过大
        double ASPECT_TOLERANCE = 0.1;
        double minDiff = Double.MAX_VALUE;
        for (Camera.Size size : sizes) {
            if (targetRatio > 0) {
                double ratio = (double) size.width / size.height;
                if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) {
                    continue;
                }
            }
            //在宽高比相近的情况下，高度相近，宽度肯定相近
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        return optimalSize;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mCamera != null) {
            try {
                mCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mCamera == null)
            return;
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null && !mHasRelease) {
            mCamera.stopPreview();
        }
    }

    public void setRelease(boolean release) {
        mHasRelease = release;
    }
}
