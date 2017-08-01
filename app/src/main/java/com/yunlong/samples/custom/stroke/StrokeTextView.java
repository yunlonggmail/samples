package com.yunlong.samples.custom.stroke;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by shiyunlong on 2017/7/31.
 */

public class StrokeTextView extends TextView {

    TextView textView;

    public StrokeTextView(Context context) {
        super(context);
    }

    public StrokeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StrokeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init(Context context) {
        textView = new TextView(context);
        Paint paint = textView.getPaint();
        paint.setStrokeWidth(3);
        paint.setTextSize(getTextSize());
        paint.setStyle(Paint.Style.STROKE);
        textView.setTextColor(Color.RED);
        textView.setGravity(getGravity());

    }


    @Override
    protected void onDraw(Canvas canvas) {
        textView.draw(canvas);
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 设置轮廓文字
        CharSequence outlineText = textView.getText();
        if (outlineText == null || !outlineText.equals(this.getText())) {
            textView.setText(getText());
            postInvalidate();
        }
        textView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        textView.layout(left, top, right, bottom);
    }
}
