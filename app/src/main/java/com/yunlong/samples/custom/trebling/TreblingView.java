package com.yunlong.samples.custom.trebling;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.yunlong.lib.utils.StringsUtils;
import com.yunlong.samples.R;

/**
 * Created by shiyunlong on 2017/7/19.
 * 三重颜色的View
 */

public class TreblingView extends TextView {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 边框的颜色
     */
    private int strokeColor;
    /**
     * 默认文字大小
     */
    private int textSize = 15;
    /**
     * 边框的弧度
     */
    private float strokeRadius;
    /**
     * StrokeRadius的数组
     */
    private float[] strokeRadiusArray = new float[8];
    /**
     * 底层文字
     */
    private String bottomText;
    /**
     * 底层文字颜色
     */
    private int bottomTextColor;
    /**
     * 默认画笔
     */
    private Paint mPaint;
    /**
     * 文字画笔
     */
    private Paint mTextPaint;


    public TreblingView(Context context) {
        this(context, null);
    }

    public TreblingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TreblingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TreblingView);
            strokeColor = array.getColor(R.styleable.TreblingView_stroke_color, Color.TRANSPARENT);
            strokeRadius = array.getDimension(R.styleable.TreblingView_stroke_radius, 0.0f);
            for (int i = 0; i < strokeRadiusArray.length; i++) {
                strokeRadiusArray[i] = strokeRadius;
            }

            textSize = array.getDimensionPixelSize(R.styleable.TreblingView_text_size, textSize);

            bottomText = array.getString(R.styleable.TreblingView_bottom_text);
            bottomTextColor = array.getColor(R.styleable.TreblingView_bottom_text_color, Color.BLACK);
            array.recycle();
        }

        //图形及路径填充画笔（抗锯齿、填充、防抖动）
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(strokeColor);
        mPaint.setDither(true);

        //图形及路径填充画笔（抗锯齿、填充、防抖动）
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setDither(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        drawStroke(canvas);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(bottomTextColor);
        drawCenterText(canvas, mTextPaint, bottomText);
        canvas.restore();

    }

    /**
     * 画Stroke
     *
     * @param canvas
     */
    private void drawStroke(Canvas canvas) {
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), strokeRadius, strokeRadius, mPaint);
        mPaint.setXfermode(null);
    }

    private void drawCenterText(Canvas canvas, Paint textPaint, String text) {
        if (StringsUtils.isEmpty(text)) {
            return;
        }
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;

        int centerY = rect.centerY() - (int) (top / 2 + bottom / 2);

        canvas.drawText(text, rect.centerX(), centerY, textPaint);
    }


}
