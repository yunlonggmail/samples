package com.yunlong.samples.custom.trebling;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
     * 宽度
     */
    private float mStrokeWidth = 1;
    /**
     * 边框的颜色
     */
    private int mStrokeColor = Color.BLACK;
    /**
     * 边框的弧度
     */
    private float mStrokeRadius = 0;
    /**
     * StrokeRadius的数组
     */
    private float[] mMiddleFillRadiusArray = new float[8];
    /**
     * 顶层文字
     */
    private CharSequence mTopText;
    /**
     * 顶层文字颜色
     */
    private int mTopTextColor = Color.BLACK;
    /**
     * 中间的填充色，默认透明色
     */
    private int mMiddleFillColor = Color.TRANSPARENT;
    /**
     * 中间填充百分比
     */
    private int mMiddleFillPercent = 0;
    /**
     * 底层文字
     */
    private CharSequence mBottomText;
    /**
     * 底层文字颜色
     */
    private int mBottomTextColor = Color.BLACK;
    /**
     * 默认画笔
     */
    private Paint mPaint;
    /**
     * 文字画笔
     */
    private Paint mTextPaint;
    /**
     * 立马重绘
     */
    private boolean mMustInvalidate = true;

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
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TreblingView);
            mStrokeWidth = array.getDimension(R.styleable.TreblingView_stroke_width, 0);
            mStrokeColor = array.getColor(R.styleable.TreblingView_stroke_color, Color.BLACK);
            mStrokeRadius = array.getDimension(R.styleable.TreblingView_stroke_radius, 0);
            mTopTextColor = array.getColor(R.styleable.TreblingView_top_text_color, Color.BLACK);
            mMiddleFillColor = array.getColor(R.styleable.TreblingView_middle_fill_color, Color.TRANSPARENT);
            mMiddleFillPercent = array.getInt(R.styleable.TreblingView_middle_fill_percent, 0);
            mMiddleFillPercent = mMiddleFillPercent > 100 ? 100 : mMiddleFillPercent;
            mBottomTextColor = array.getColor(R.styleable.TreblingView_bottom_text_color, Color.BLACK);
            fillMiddleRadiusArray(mStrokeRadius);
            array.recycle();
        }

        //图形及路径填充画笔（抗锯齿、填充、防抖动）
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mStrokeColor);
        mPaint.setDither(true);

        //文字及路径填充画笔（抗锯齿、填充、防抖动）
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTopTextColor);
        mTextPaint.setDither(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        initText();
        drawBottomText(canvas);
        drawStroke(canvas);
        drawMiddleFill(canvas);
        drawTopText(canvas);
    }

    /**
     * 初始化Text
     */
    private void initText() {
        mTopText = getText();
        mBottomText = getText();
    }

    /**
     * 画底层文字
     */
    private void drawBottomText(Canvas canvas) {
        mTextPaint.setTextSize(getTextSize());
        mTextPaint.setColor(mBottomTextColor);
        drawTextAtCenter(canvas, mTextPaint, mBottomText);
    }

    /**
     * 画顶层文字
     *
     * @param canvas：画布
     */
    private void drawTopText(Canvas canvas) {
        mTextPaint.setTextSize(getTextSize());
        mTextPaint.setColor(mTopTextColor);
        drawTextAtCenter(canvas, mTextPaint, mTopText);
    }

    /**
     * 在画板中间画APP
     *
     * @param canvas：画布
     * @param textPaint：画笔
     * @param text：文字
     */
    private void drawTextAtCenter(Canvas canvas, Paint textPaint, CharSequence text) {
        if (StringsUtils.isEmpty(text)) {
            return;
        }
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;

        int centerY = rect.centerY() - (int) (top / 2 + bottom / 2);

        canvas.drawText(text.toString(), rect.centerX(), centerY, textPaint);
    }

    /**
     * 画Stroke
     *
     * @param canvas：画布
     */
    private void drawStroke(Canvas canvas) {
        if (mStrokeWidth <= 0) {
            return;
        }
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mStrokeColor);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        //画圆角矩形的时候如果线有宽度，那么圆角矩形的定点向中间移动画笔线的宽的一半
        //可以使用粗笔头感受一下
        canvas.drawRoundRect(new RectF(mStrokeWidth / 2, mStrokeWidth / 2, getWidth() - mStrokeWidth / 2, getHeight() - mStrokeWidth / 2), mStrokeRadius, mStrokeRadius, mPaint);
        mPaint.setXfermode(null);
    }

    /**
     * 获取中间填充Path
     *
     * @return：路径信息
     */
    private Path getMiddleFillPath() {
        Path path = new Path();
        //填充中间块的时候避免压住Stroke，向中间移动
        float offset = mStrokeColor == mMiddleFillColor ? mStrokeWidth / 2 : mStrokeWidth;
        RectF rectF = new RectF(offset, offset, getWidth() * mMiddleFillPercent / 100 - (mMiddleFillPercent == 100 ? offset : 0), getHeight() - offset);
        path.addRoundRect(rectF, mMiddleFillRadiusArray, Path.Direction.CW);
        return path;
    }

    /**
     * 画中间填充
     *
     * @param canvas：画布
     */
    private void drawMiddleFill(Canvas canvas) {
        if (mMiddleFillPercent <= 0 || mMiddleFillColor == Color.TRANSPARENT) {
            return;
        }
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mMiddleFillColor);
        Path path = getMiddleFillPath();
        canvas.clipPath(path);
        canvas.drawPath(path, mPaint);
        //图形画完之后将画笔方式设为Stroke
        mPaint.setXfermode(null);
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        if (StringsUtils.isEmpty(text)) {
            text = "";
            mTopText = "";
            mBottomText = "";
        } else {
            mTopText = text;
            mBottomText = text;
        }
        super.setText(text, type);
    }

    /**
     * 设置
     *
     * @param mustInvalidate ：立即重绘，重新设置参数后会立即重绘
     */
    public void setMustInvalidate(boolean mustInvalidate) {
        mMustInvalidate = mustInvalidate;
    }

    /**
     * 重绘
     */
    public void doInvalidate() {
        if (!mMustInvalidate)
            invalidate();
    }

    /**
     * 设置顶层文字颜色
     *
     * @param topTextColor：顶层文字颜色
     */
    public void setTopTextColor(int topTextColor) {
        this.mTopTextColor = topTextColor;
        if (this.mMustInvalidate)
            invalidate();
    }

    /**
     * 设置中间填充百分比
     *
     * @param middleFilePercent：[0,100]之间的数字
     */
    public void setMiddleFillPercent(int middleFilePercent) {
        this.mMiddleFillPercent = middleFilePercent;
        if (this.mMustInvalidate)
            invalidate();
    }

    /**
     * 设置中间填充区域颜色
     *
     * @param middleFillColor：中间填充区颜色
     */
    public void setMiddleFillColor(int middleFillColor) {
        this.mMiddleFillColor = middleFillColor;
        if (this.mMustInvalidate)
            invalidate();
    }

    /**
     * 设置边框宽度
     *
     * @param strokeWidth：边框宽度
     */
    public void setStrokeWidth(float strokeWidth) {
        this.mStrokeWidth = strokeWidth;
        if (this.mMustInvalidate)
            invalidate();
    }

    /**
     * 设置边框颜色
     *
     * @param strokeColor：边框颜色
     */
    public void setStrokeColor(int strokeColor) {
        this.mStrokeColor = strokeColor;
        if (this.mMustInvalidate)
            invalidate();
    }

    /**
     * 设置边框颜色
     *
     * @param strokeRadius：边框弧度
     */
    public void setStrokeRadius(float strokeRadius) {
        this.mStrokeRadius = strokeRadius;
        fillMiddleRadiusArray(strokeRadius);
        if (this.mMustInvalidate)
            invalidate();
    }

    /**
     * 中间填充器的弧度
     *
     * @param radius：弧度
     */
    private void fillMiddleRadiusArray(float radius) {
        for (int i = 0; i < mMiddleFillRadiusArray.length; i++) {
            switch (i) {
                case 0:
                case 1:
                case 6:
                case 7:
                    mMiddleFillRadiusArray[i] = radius;
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                    if (mMiddleFillPercent == 100) {
                        mMiddleFillRadiusArray[i] = radius;
                    } else
                        mMiddleFillRadiusArray[i] = 0;
                    break;
            }
        }
    }

    /**
     * 设置顶层文字颜色
     *
     * @param bottomTextColor：底层文字颜色
     */
    public void setBottomTextColor(int bottomTextColor) {
        this.mBottomTextColor = bottomTextColor;
        if (this.mMustInvalidate)
            invalidate();
    }


}
