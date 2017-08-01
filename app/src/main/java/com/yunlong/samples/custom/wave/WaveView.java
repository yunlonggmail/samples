package com.yunlong.samples.custom.wave;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yunlong.samples.R;

/**
 * Created by shiyunlong on 2017/7/28.
 * 波浪控件
 */

public class WaveView extends View {
    /**
     * 上方的路径
     */
    private Path mAbovePath;
    /**
     * 底部的路径
     */
    private Path mBelowPath;
    /**
     * 顶部的画笔
     */
    private Paint mAbovePaint;
    /**
     * 底部的画笔
     */
    private Paint mBelowPaint;
    /**
     * 画布设置
     */
    private DrawFilter mDrawFilter;
    /**
     * 角度
     */
    private float φ;
    /**
     * 动画的监听
     */
    private OnWaveAnimationListener mOnWaveAnimationListener;
    /**
     * AboveColor
     */
    private int mAboveColor = Color.WHITE;
    /**
     * Above的透明度
     */
    private int mAboveAlpha = 100;
    /**
     * 底部的颜色
     */
    private int mBelowColor = Color.WHITE;
    /**
     * 底部透明度
     */
    private int mBelowAlpha = 100;
    /**
     * 震动幅度
     */
    private float mAmplitude = 1;
    /**
     * X轴Step;
     */
    private float mXOffsetStep = 0.1f;
    /**
     * Y轴偏移量
     */
    private float mYOffset = 0;
    /**
     * 延迟
     */
    private int mPostDelayedTime = 20;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mAbovePath = new Path();
        mBelowPath = new Path();

        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WaveView);

            mAboveColor = array.getColor(R.styleable.WaveView_above_color, Color.WHITE);
            mAboveAlpha = array.getInt(R.styleable.WaveView_above_alpha, 100);

            mBelowColor = array.getColor(R.styleable.WaveView_below_color, Color.WHITE);
            mBelowAlpha = array.getInt(R.styleable.WaveView_below_alpha, 100);

            mAmplitude = array.getFloat(R.styleable.WaveView_amplitude, 1);

            mXOffsetStep = array.getFloat(R.styleable.WaveView_x_offset_step, 0.1f);
            mYOffset = array.getFloat(R.styleable.WaveView_y_offset, 0);

            mPostDelayedTime = array.getInt(R.styleable.WaveView_post_delay, 20);

            array.recycle();
        }


        mAbovePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAbovePaint.setAntiAlias(true);
        mAbovePaint.setStyle(Paint.Style.FILL);
        mAbovePaint.setColor(mAboveColor);
        mAbovePaint.setAlpha(mAboveAlpha);

        mBelowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBelowPaint.setAntiAlias(true);
        mBelowPaint.setStyle(Paint.Style.FILL);
        mBelowPaint.setColor(mBelowColor);
        mBelowPaint.setAlpha(mBelowAlpha);


        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    /**
     * 以该控件的中心线画正余弦曲线
     * 以Path为核心，画布四个顶点的标识
     * 左上角(0,0)
     * 左下角(0,getHeight())
     * 右上角(getWith(),0)
     * 右下角(getWidth(),getHeight())
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(mDrawFilter);

        mAbovePath.reset();
        mBelowPath.reset();

        φ -= mXOffsetStep;

        float aboveY = 0, belowY;

        double ω = 2 * Math.PI / getWidth();

        //移动到左下角
        mAbovePath.moveTo(0, getHeight());
        mBelowPath.moveTo(0, getHeight());
        //防止振幅过大，超出编辑
        if (mAmplitude > (float) (getHeight() / 2) - mYOffset) {
            mAmplitude = (float) (getHeight() / 2) - mYOffset;
        }

        for (float x = 0; x <= getWidth(); x += 1) {
            aboveY = getHeight() / 2 - (float) (mAmplitude * Math.cos(ω * x + φ) + mYOffset);
            belowY = getHeight() / 2 - (float) (mAmplitude * Math.sin(ω * x + φ) + mYOffset);
            mAbovePath.lineTo(x, aboveY);
            mBelowPath.lineTo(x, belowY);
        }
        //此时AboveY表示2π处的位置
        mOnWaveAnimationListener.onWave(aboveY);

        mAbovePath.lineTo(getWidth(), getHeight());
        mAbovePath.lineTo(0, getHeight());
        mBelowPath.lineTo(getWidth(), getHeight());
        mBelowPath.lineTo(0, getHeight());

        canvas.drawPath(mAbovePath, mAbovePaint);
        canvas.drawPath(mBelowPath, mBelowPaint);

        postInvalidateDelayed(mPostDelayedTime);
    }

    /**
     * 点击监听
     *
     * @param onWaveAnimationListener
     */
    public void setOnWaveAnimationListener(OnWaveAnimationListener onWaveAnimationListener) {
        this.mOnWaveAnimationListener = onWaveAnimationListener;
    }

    public interface OnWaveAnimationListener {
        void onWave(float y);
    }
}
