package com.yunlong.samples.animation.tween.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by shiyunlong on 2017/3/27.
 * 3D翻转动画
 */

public class Rotate3DAnimation extends Animation {
    /**
     * 不是相机，使用该硬件主要原因是用于翻转
     */
    private Camera mCamera;

    private float mFromDegree;

    private float mToDegree;
    /**
     * 旋转中心点
     */
    private float mPivotX;
    /**
     * 旋转Y点
     */
    private float mPivotY;
    /**
     * Z轴的深度
     */
    private float mDepZ;

    public Rotate3DAnimation(float fromDegree, float toDegree) {
        mFromDegree = fromDegree;
        mToDegree = toDegree;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
        mPivotX = width / 2;
        mPivotY = height / 2;
        mDepZ = width;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        Matrix matrix = t.getMatrix();
        float degree = mFromDegree + (mToDegree - mFromDegree) * interpolatedTime;
        float degreePI = (float) (2 * Math.PI * (degree / 360));
        mCamera.save();
        float x = (float) (mDepZ * Math.sin(degreePI)) / 2;
        float y = 0.0f;
        float z = (float) (mDepZ * (1 - Math.cos(degreePI))) / 2;
        mCamera.translate(x, y, z);
        mCamera.rotateY(degree);
        mCamera.getMatrix(matrix);
        mCamera.restore();
        matrix.preTranslate(-mPivotX, -mPivotY);
        matrix.postTranslate(mPivotX, mPivotY);
    }


}
