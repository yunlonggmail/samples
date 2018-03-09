package com.yunlong.samples.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.yunlong.lib.utils.LogUtils;

/**
 * Created by shiyunlong on 2018/2/27.
 * 自定义View
 */

public class CustomView extends View {
    /**
     * 标识
     */
    public static final String TAG = "com.yunlong.samples.study.view.CustomView";

    public CustomView(Context context) {
        super(context);
        LogUtils.D(TAG, " CustomView( Context ) ");
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LogUtils.D(TAG, " CustomView( Context ， AttributeSet ) ");
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtils.D(TAG, " CustomView( Context , AttributeSet , int ) ");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LogUtils.D(TAG, " onFinishInflate ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.D(TAG, " onMeasure ");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtils.D(TAG, " onLayout ");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogUtils.D(TAG, " onSizeChanged ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtils.D(TAG, " onDraw ");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtils.D(TAG, " onKeyDown ");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        LogUtils.D(TAG, " onKeyUp ");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.D(TAG, " onTouchEvent ");
        return super.onTouchEvent(event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        LogUtils.D(TAG, " onWindowFocusChanged ");
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        LogUtils.D(TAG, " onFocusChanged ");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtils.D(TAG, " onAttachedToWindow ");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogUtils.D(TAG, " onDetachedFromWindow ");
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        LogUtils.D(TAG, " onVisibilityChanged ");
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        LogUtils.D(TAG, " onWindowVisibilityChanged ");
    }


    @Override
    public boolean hasFocus() {
        LogUtils.D(TAG, " hasFocus ");
        return super.hasFocus();
    }


    @Override
    public boolean isFocused() {
        LogUtils.D(TAG, " isFocused ");
        return super.isFocused();
    }


    @Override
    public boolean isShown() {
        LogUtils.D(TAG, " isShown ");
        return super.isShown();
    }


    @Override
    public int getVisibility() {
        LogUtils.D(TAG, " getVisibility ");
        return super.getVisibility();
    }


    @Override
    public int getLayoutDirection() {
        LogUtils.D(TAG, " getLayoutDirection ");
        return super.getLayoutDirection();
    }

    @Override
    public boolean hasTransientState() {
        LogUtils.D(TAG, " hasTransientState ");
        return super.hasTransientState();
    }


    @Override
    public boolean isPressed() {
        LogUtils.D(TAG, " isPressed ");
        return super.isPressed();
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        LogUtils.D(TAG, " requestFocus ");
        return super.requestFocus(direction, previouslyFocusedRect);
    }

    @Override
    public void dispatchWindowFocusChanged(boolean hasFocus) {
        LogUtils.D(TAG, " dispatchWindowFocusChanged ");
        super.dispatchWindowFocusChanged(hasFocus);
    }

    @Override
    public boolean hasWindowFocus() {
        LogUtils.D(TAG, " hasWindowFocus ");
        return super.hasWindowFocus();
    }

    @Override
    public void dispatchWindowVisibilityChanged(int visibility) {
        LogUtils.D(TAG, " dispatchWindowVisibilityChanged ");
        super.dispatchWindowVisibilityChanged(visibility);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        LogUtils.D(TAG, " dispatchDraw ");
        super.dispatchDraw(canvas);
    }


    @Override
    public Matrix getMatrix() {
        LogUtils.D(TAG, " getMatrix ");
        return super.getMatrix();
    }


    @Override
    public boolean hasOverlappingRendering() {
        LogUtils.D(TAG, " hasOverlappingRendering ");
        return super.hasOverlappingRendering();
    }


    @Override
    public float getZ() {
        LogUtils.D(TAG, " getZ ");
        return super.getZ();
    }


    @Override
    public float getElevation() {
        LogUtils.D(TAG, " getElevation ");
        return super.getElevation();
    }


    @Override
    public float getTranslationZ() {
        LogUtils.D(TAG, " getTranslationZ ");
        return super.getTranslationZ();
    }


    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        LogUtils.D(TAG, " getLayoutParams ");
        return super.getLayoutParams();
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        LogUtils.D(TAG, " setLayoutParams ");
        super.setLayoutParams(params);
    }

    @Override
    public boolean isOpaque() {
        LogUtils.D(TAG, " isOpaque ");
        return super.isOpaque();
    }

    @Override
    public void computeScroll() {
        LogUtils.D(TAG, " computeScroll ");
        super.computeScroll();
    }


    @Override
    public void onRtlPropertiesChanged(int layoutDirection) {
        LogUtils.D(TAG, " onRtlPropertiesChanged ");
        super.onRtlPropertiesChanged(layoutDirection);
    }

    @Override
    public boolean canResolveLayoutDirection() {
        LogUtils.D(TAG, " canResolveLayoutDirection ");
        return super.canResolveLayoutDirection();
    }

    @Override
    public boolean isLayoutDirectionResolved() {
        LogUtils.D(TAG, " isLayoutDirectionResolved ");
        return super.isLayoutDirectionResolved();
    }

    @Override
    public int getLayerType() {
        LogUtils.D(TAG, " getLayerType ");
        return super.getLayerType();
    }

    @Override
    public void destroyDrawingCache() {
        LogUtils.D(TAG, " 340 ");
        super.destroyDrawingCache();
    }

    @Override
    public boolean isHardwareAccelerated() {
        LogUtils.D(TAG, " isHardwareAccelerated ");
        return super.isHardwareAccelerated();
    }

    @Override
    public void draw(Canvas canvas) {
        LogUtils.D(TAG, " draw ");
        super.draw(canvas);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        LogUtils.D(TAG, " layout ");
        super.layout(l, t, r, b);
    }

    @Override
    protected void drawableStateChanged() {
        LogUtils.D(TAG, " drawableStateChanged ");
        super.drawableStateChanged();
    }

    @Override
    public void refreshDrawableState() {
        LogUtils.D(TAG, " refreshDrawableState ");
        super.refreshDrawableState();
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        LogUtils.D(TAG, " onCreateDrawableState ");
        return super.onCreateDrawableState(extraSpace);
    }

    @Override
    public void jumpDrawablesToCurrentState() {
        LogUtils.D(TAG, " jumpDrawablesToCurrentState ");
        super.jumpDrawablesToCurrentState();
    }

    @Override
    public Drawable getBackground() {
        LogUtils.D(TAG, " getBackground ");
        return super.getBackground();
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        LogUtils.D(TAG, " onDrawForeground ");
        super.onDrawForeground(canvas);
    }

    @Override
    public void forceLayout() {
        LogUtils.D(TAG, " forceLayout ");
        super.forceLayout();
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        LogUtils.D(TAG, " getSuggestedMinimumHeight ");
        return super.getSuggestedMinimumHeight();
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        LogUtils.D(TAG, " getSuggestedMinimumWidth ");
        return super.getSuggestedMinimumWidth();
    }


    @Override
    public Animation getAnimation() {
        LogUtils.D(TAG, " getAnimation ");
        return super.getAnimation();
    }


    @Override
    public void setOverScrollMode(int overScrollMode) {
        super.setOverScrollMode(overScrollMode);
        LogUtils.D(TAG, " setOverScrollMode ");
    }

    @Override
    public void stopNestedScroll() {
        super.stopNestedScroll();
        LogUtils.D(TAG, " stopNestedScroll ");
    }

    @Override
    public boolean canResolveTextDirection() {
        LogUtils.D(TAG, " canResolveTextDirection ");
        return super.canResolveTextDirection();
    }

    @Override
    public boolean isTextDirectionResolved() {
        LogUtils.D(TAG, " isTextDirectionResolved ");
        return super.isTextDirectionResolved();
    }

    @Override
    public boolean isTextAlignmentResolved() {
        LogUtils.D(TAG, " isTextAlignmentResolved ");
        return super.isTextAlignmentResolved();
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        LogUtils.D(TAG, " requestLayout ");
    }

}

