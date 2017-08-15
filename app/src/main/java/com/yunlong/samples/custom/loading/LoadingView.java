package com.yunlong.samples.custom.loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.yunlong.lib.utils.DisplayUtils;
import com.yunlong.lib.utils.StringsUtils;
import com.yunlong.samples.R;

/**
 * Created by shiyunlong on 2017/8/14.
 * 加载控件
 */

public class LoadingView extends View {
    /**
     * 文字，画笔截取方式
     */
    private PorterDuffXfermode mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
    /**
     * 文字
     */
    private CharSequence mText = "云龙";
    /**
     * 文字大小
     */
    private float mTextSize = DisplayUtils.dp2px(1);
    /**
     * 底部文字颜色
     */
    private int mBottomTextFillColor = Color.BLACK;
    /**
     * 底部文字描边颜色
     */
    private int mBottomTextStrokeColor = Color.BLACK;
    /**
     * 底部文字颜色
     */
    private int mTopTextFillColor = Color.BLACK;
    /**
     * 底部文字描边颜色
     */
    private int mTopTextStrokeColor = Color.BLACK;
    /**
     * 文字描边的画笔
     */
    private Paint mTextStrokePaint;
    /**
     * 文字的画笔
     */
    private Paint mTextPaint;
    /**
     * 文字边线的宽度
     */
    private float mTextStrokeWidth = DisplayUtils.dp2px(1);
    /**
     * 路径
     */
    private Path mPath;
    /**
     * 宽度，控件的宽度
     */
    private int mWidth = DisplayUtils.dp2px(1);
    /**
     * 高度，控件的高度
     */
    private int mHeight = DisplayUtils.dp2px(1);
    /**
     * 中间部分画笔
     */
    private Paint mMiddlePaint;
    /**
     * 填充颜色
     */
    private int mStrokeColor = Color.TRANSPARENT;
    /**
     * 中间填充颜色
     */
    private int mFillColor = Color.BLACK;
    /**
     * 中间创建图形
     */
    private Bitmap mBitMap;
    /**
     * 画布，自定义的画布
     */
    private Canvas mCanvas;
    /**
     * 当前的进度
     */
    private float mCurrentPercent = 0.0f;
    /**
     * 动画时长
     */
    private long mDuration = 1000;
    /**
     * 边线的宽度
     */
    private float mStrokeWidth = DisplayUtils.dp2px(1);
    /**
     * 动画
     */
    private ValueAnimator mValueAnimator;
    /**
     * 边线弧度
     */
    private float mStrokeRadius = 0;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
            mBottomTextFillColor = array.getColor(R.styleable.LoadingView_lv_bottom_text_fill_color, Color.BLACK);
            mBottomTextStrokeColor = array.getColor(R.styleable.LoadingView_lv_bottom_text_stroke_color, Color.BLACK);
            mTopTextFillColor = array.getColor(R.styleable.LoadingView_lv_top_text_fill_color, Color.BLACK);
            mTopTextStrokeColor = array.getColor(R.styleable.LoadingView_lv_top_text_stroke_color, Color.BLACK);
            mText = array.getText(R.styleable.LoadingView_lv_text);
            mTextSize = array.getDimension(R.styleable.LoadingView_lv_text_size, mTextSize);
            mTextStrokeWidth = array.getDimension(R.styleable.LoadingView_lv_text_width, mTextStrokeWidth);
            mStrokeColor = array.getColor(R.styleable.LoadingView_lv_stroke_color, Color.TRANSPARENT);
            mStrokeWidth = array.getDimension(R.styleable.LoadingView_lv_stroke_width, mStrokeWidth);
            mFillColor = array.getColor(R.styleable.LoadingView_lv_fill_color, Color.BLACK);
            mStrokeRadius = array.getDimension(R.styleable.LoadingView_lv_stroke_radius, mStrokeRadius);
            array.recycle();
        }


        //图形及路径填充画笔（抗锯齿、填充、防抖动）
        mTextStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextStrokePaint.setColor(mBottomTextStrokeColor);
        mTextStrokePaint.setStyle(Paint.Style.STROKE);
        mTextStrokePaint.setDither(true);
        mTextStrokePaint.setXfermode(mPorterDuffXfermode);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mBottomTextFillColor);
        mTextPaint.setDither(true);
        mTextPaint.setXfermode(mPorterDuffXfermode);

        mPath = new Path();

        //中部画笔
        mMiddlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMiddlePaint.setStyle(Paint.Style.FILL);
        mMiddlePaint.setColor(mFillColor);
        mMiddlePaint.setDither(true);

        mValueAnimator = ValueAnimator.ofFloat(0, 1);
        mValueAnimator.setDuration(mDuration);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentPercent = animation.getAnimatedFraction();
                invalidate();
            }
        });
        mValueAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = width;
        mHeight = height;
        setMeasuredDimension(mWidth, mHeight);
        mBitMap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitMap);

        if (!StringsUtils.isEmpty(mText)) {
            int textMaxSize = mWidth / mText.length() * 2;
            if (mTextSize > textMaxSize)
                mTextSize = textMaxSize;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (StringsUtils.isEmpty(mText))
            return;
        drawTopText(canvas);
        drawMiddleGraphics(canvas);
    }

    /**
     * 画上部文字
     */
    private void drawTopText(Canvas canvas) {
        if (mTextStrokeWidth > 0 && mTopTextStrokeColor != Color.TRANSPARENT) {
            mTextStrokePaint.setTextSize(mTextSize);
            mTextStrokePaint.setColor(mTopTextStrokeColor);
            mTextStrokePaint.setStrokeWidth(mTextStrokeWidth);
            drawTextAtCenter(canvas, mTextStrokePaint, mText);
        }

        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTopTextFillColor);
        drawTextAtCenter(canvas, mTextPaint, mText);
    }


    /**
     * 画下部文字
     */
    private void drawBottomText(Canvas canvas) {
        if (mTextStrokeWidth > 0 && mBottomTextStrokeColor != Color.TRANSPARENT) {
            mTextStrokePaint.setTextSize(mTextSize);
            mTextStrokePaint.setColor(mBottomTextStrokeColor);
            mTextStrokePaint.setStrokeWidth(mTextStrokeWidth);
            drawTextAtCenter(canvas, mTextStrokePaint, mText);
        }
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mBottomTextFillColor);
        drawTextAtCenter(canvas, mTextPaint, mText);
    }

    /**
     * 画中间步骤的图形，也就是下半部
     *
     * @param canvas
     */
    private void drawMiddleGraphics(Canvas canvas) {
        drawMiddleStroke(canvas);
        drawMiddleFill(canvas);
    }

    /**
     * 画边框
     */
    private void drawMiddleStroke(Canvas canvas) {
        if (mStrokeColor == Color.TRANSPARENT || mStrokeWidth <= 0)
            return;
        int r = Math.min(mWidth, mHeight) / 2;
        mMiddlePaint.setColor(mStrokeColor);
        mMiddlePaint.setStyle(Paint.Style.STROKE);
        mMiddlePaint.setStrokeWidth(mStrokeWidth);
        mMiddlePaint.setXfermode(mPorterDuffXfermode);
        if (mWidth == mHeight) {
            canvas.drawCircle(mWidth / 2, mHeight / 2, r - mStrokeWidth, mMiddlePaint);
        } else {
            canvas.drawRoundRect(new RectF(mStrokeWidth / 2, mStrokeWidth / 2, getWidth() - mStrokeWidth / 2, getHeight() - mStrokeWidth / 2), mStrokeRadius, mStrokeRadius, mMiddlePaint);
        }
        mMiddlePaint.setXfermode(null);
    }

    /**
     * 画中间填充
     *
     * @param canvas：画布
     */
    private void drawMiddleFill(Canvas canvas) {
        int r = Math.min(mWidth, mHeight) / 2;
        mMiddlePaint.setColor(mFillColor);
        mMiddlePaint.setStyle(Paint.Style.FILL);
        mMiddlePaint.setShader(getBitmapShader());
        //圆向内缩的距离
        float dx = mStrokeWidth * 3 / 2;

        if (mWidth == mHeight) {
            canvas.drawCircle(mWidth / 2, mHeight / 2, r - dx, mMiddlePaint);
        } else {
            canvas.drawRoundRect(new RectF(mStrokeWidth / 2, mStrokeWidth / 2, getWidth() - mStrokeWidth / 2, getHeight() - mStrokeWidth / 2), mStrokeRadius, mStrokeRadius, mMiddlePaint);
        }

        mMiddlePaint.setShader(null);
    }

    /**
     * 获取Bitmap的图形
     */
    private BitmapShader getBitmapShader() {
        mBitMap.eraseColor(Color.TRANSPARENT);//将bitMap填充成透明色
        mPath = getActionPath(mCurrentPercent);
        mCanvas.drawPath(mPath, mMiddlePaint);
        drawBottomText(mCanvas);
        return new BitmapShader(mBitMap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    /**
     * 获取ActionPath
     */
    private Path getActionPath(float percent) {
        Path path = new Path();
        int x = -mWidth;
        //当前x点坐标（根据动画进度水平推移，一个动画周期推移的距离为一个mWidth）
        x += percent * mWidth;
        //波形的起点
        path.moveTo(x, mHeight / 2);
        //控制点的相对宽度
        int quadWidth = mWidth / 4;
        //控制点的相对高度
        int quadHeight = mHeight / 20 * 3;
        //第一个周期
        path.rQuadTo(quadWidth, quadHeight, quadWidth * 2, 0);
        path.rQuadTo(quadWidth, -quadHeight, quadWidth * 2, 0);
        //第二个周期
        path.rQuadTo(quadWidth, quadHeight, quadWidth * 2, 0);
        path.rQuadTo(quadWidth, -quadHeight, quadWidth * 2, 0);
        //右侧的直线
        path.lineTo(x + mWidth * 2, mHeight);
        //下边的直线
        path.lineTo(x, mHeight);
        //自动闭合补出左边的直线
        path.close();
        return path;
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
        Rect rect = new Rect(0, 0, mWidth, mHeight);
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;

        int centerY = rect.centerY() - (int) (top / 2 + bottom / 2);

        canvas.drawText(text.toString(), rect.centerX(), centerY, textPaint);
    }


    /**
     * 设置底部文字边框颜色
     *
     * @param bottomTextStrokeColor：底部边框颜色
     */
    public void setBottomTextStrokeColor(int bottomTextStrokeColor) {
        this.mBottomTextStrokeColor = bottomTextStrokeColor;
    }

    /**
     * 设置底部文字填充颜色
     *
     * @param bottomTextFillColor：底部文字填充颜色
     */
    public void setBottomTextFillColor(int bottomTextFillColor) {
        this.mBottomTextFillColor = bottomTextFillColor;
    }

    /**
     * 设置顶部文字颜色
     *
     * @param topTextFillColor：顶部文字填充颜色
     */
    public void setTopTextFillColor(int topTextFillColor) {
        this.mTopTextFillColor = topTextFillColor;
    }

    /**
     * 设置顶部文字边框颜色
     *
     * @param topTextStrokeColor：顶部边框颜色
     */
    public void setTopTextStrokeColor(int topTextStrokeColor) {
        this.mTopTextStrokeColor = topTextStrokeColor;
    }

    /**
     * 设置文字
     *
     * @param text：文字
     */
    public void setText(CharSequence text) {
        if (StringsUtils.isEmpty(text))
            return;
        this.mText = text;
    }

    /**
     * 设置文字大小
     *
     * @param mTextSize：文字大小
     */
    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
    }

    /**
     * 设置文字边框宽度
     *
     * @param mTextStrokeWidth：边框宽度
     */
    public void setTextStrokeWidth(float mTextStrokeWidth) {
        this.mTextStrokeWidth = mTextStrokeWidth;
    }

    /**
     * 设置边框颜色
     *
     * @param mStrokeColor：圆边框颜色
     */
    public void setStrokeColor(int mStrokeColor) {
        this.mStrokeColor = mStrokeColor;
    }

    /**
     * 设置填充颜色
     *
     * @param mFillColor：填充颜色
     */
    public void setFillColor(int mFillColor) {
        this.mFillColor = mFillColor;
    }

    /**
     * 设置时长
     *
     * @param mDuration:时长
     */
    public void setDuration(long mDuration) {
        this.mDuration = mDuration;
        mValueAnimator.setDuration(mDuration);
        mValueAnimator.cancel();
        mValueAnimator.start();
    }

    /**
     * 设置边框宽度
     *
     * @param mStrokeWidth：边框宽度
     */
    public void setStrokeWidth(float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
    }

    /**
     * 设置边框宽度
     *
     * @param radius：边框弧度
     */
    public void setStrokeRadius(float radius) {
        this.mStrokeRadius = radius;
    }


}
