package com.yunlong.samples.custom.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.yunlong.samples.R;

/**
 * 左右菜单
 * Created by shiyunlong on 2017/2/6.
 */

public class LRMenu extends RelativeLayout {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 左菜单ID
     */
    private int leftMenuId = R.id.rl_menu_l_id;
    /**
     * 左侧百分比
     */
    private float leftPercent = 0;
    /**
     * 左菜单
     */
    private FrameLayout leftMenu;
    /**
     * 中菜单ID
     */
    private int middleMenuId = R.id.rl_menu_m_id;
    /**
     * 中菜单
     */
    private FrameLayout middleMenu;
    /**
     * 中菜单ID
     */
    private int rightMenuId = R.id.rl_menu_r_id;
    /**
     *
     */
    private float rightPercent = 0;
    /**
     * 右菜单
     */
    private FrameLayout rightMenu;
    /**
     * 滑动器
     */
    private Scroller mScroller;
    /**
     * 是否测试完毕，测试左右还是上下滑动
     */
    private boolean isTestComplete;
    /**
     * 是否是左右滑动
     */
    private boolean isLeftRightScroll = false;
    /**
     * 蒙层
     */
    private FrameLayout maskedMenu;
    /**
     * 默认
     */
    private static final int DEFAULT_COLOR = 0x88000000;
    /**
     * 蒙层颜色
     */
    private int maskedColor;
    /**
     * 最小滑动距离
     */
    private static final int MIN_MOVE_DISTANCE = 20;
    /**
     * 滑动计算点
     */
    private Point point = new Point();

    public LRMenu(Context context) {
        this(context, null);
    }

    public LRMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LRMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LRMenu);
            leftPercent = typedArray.getFloat(R.styleable.LRMenu_left_menu_width_percent, 0);
            rightPercent = typedArray.getFloat(R.styleable.LRMenu_right_menu_width_percent, 0);
            maskedColor = typedArray.getColor(R.styleable.LRMenu_masked_menu_color, DEFAULT_COLOR);
            typedArray.recycle();
        }
        initView();
    }

    private void initView() {
        mScroller = new Scroller(mContext, new DecelerateInterpolator());

        leftMenu = new FrameLayout(mContext);
        if (leftMenuId > 0)
            leftMenu.setId(leftMenuId);

        middleMenu = new FrameLayout(mContext);
        if (middleMenuId > 0)
            middleMenu.setId(middleMenuId);

        rightMenu = new FrameLayout(mContext);
        if (rightMenuId > 0)
            rightMenu.setId(rightMenuId);

        maskedMenu = new FrameLayout(mContext);
        maskedMenu.setBackgroundColor(maskedColor);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(leftMenu, params);
        addView(middleMenu, params);
        addView(rightMenu, params);
        addView(maskedMenu, params);
        maskedMenu.setAlpha(0);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        int curScrollX = getScrollX();
        float alpha = 0;
        if (curScrollX > 0) {
            alpha = (float) Math.abs(curScrollX) / (float) rightMenu.getMeasuredWidth();
        } else {
            alpha = (float) Math.abs(curScrollX) / (float) leftMenu.getMeasuredWidth();
        }
        maskedMenu.setAlpha(alpha);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        middleMenu.measure(widthMeasureSpec, heightMeasureSpec);
        maskedMenu.measure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        if (leftPercent > 0) {
            if (leftPercent > 1) {
                while (leftPercent > 1) {
                    leftPercent--;
                }
            }
            int leftMeasureSpec = MeasureSpec.makeMeasureSpec((int) (widthMeasure * leftPercent), MeasureSpec.EXACTLY);
            leftMenu.measure(leftMeasureSpec, heightMeasureSpec);
        } else {
            leftMenu.setVisibility(GONE);
        }
        if (rightPercent > 0) {
            if (rightPercent > 1) {
                while (rightPercent > 1) {
                    rightPercent--;
                }
            }
            int rightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (widthMeasure * rightPercent), MeasureSpec.EXACTLY);
            rightMenu.measure(rightMeasureSpec, heightMeasureSpec);
        } else {
            rightMenu.setVisibility(GONE);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int height = middleMenu.getMeasuredHeight();
        int leftWidth = leftMenu.getMeasuredWidth();
        int middleWidth = middleMenu.getMeasuredWidth();
        int rightWidth = rightMenu.getMeasuredWidth();

        //将top设为0是为了避免在当前控件父布局中使用toolbar，进行了位移。而三个子类布局不需要重新设置位置，基于当前布局0即可。
        middleMenu.layout(l, 0, r, height);
        maskedMenu.layout(l, 0, r, height);
        leftMenu.layout(l - leftWidth, 0, l, height);
        rightMenu.layout(l + middleWidth, 0, l + middleWidth + rightWidth, height);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isTestComplete) {
            getEventType(ev);
            return true;
        }
        if (isLeftRightScroll) {
            switch (ev.getActionMasked()) {
                case MotionEvent.ACTION_MOVE:
                    //滚动距离,表示整个控件的滚动信息，滚动距离大于0表示向→滚动，图像展示左移，出现右菜单
                    int curScrollX = getScrollX();
                    //滑动距离，手指的滑动距离，滑动距离大于0表示向→滑动
                    int dis_x = (int) (ev.getX() - point.x);

                    //目标滑动距离。向右滑动表示图像展示右移，向左滑动。取负获得滑动距离一致性。
                    int expectScrollX = -dis_x + curScrollX;
                    int finalX = 0;

                    if (expectScrollX < 0) {
                        finalX = Math.max(expectScrollX, -leftMenu.getMeasuredWidth());
                    } else {
                        finalX = Math.min(expectScrollX, rightMenu.getMeasuredWidth());
                    }
                    //滚动到目标滑动距离
                    scrollTo(finalX, 0);
                    point.x = (int) ev.getX();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    curScrollX = getScrollX();
                    //滚动距离大于0
                    if (curScrollX > 0) {
                        if (Math.abs(curScrollX) > rightMenu.getMeasuredWidth() >> 1) {
                            mScroller.startScroll(curScrollX, 0, rightMenu.getMeasuredWidth() - curScrollX, 0, 200);
                        } else {
                            mScroller.startScroll(curScrollX, 0, -curScrollX, 0, 200);
                        }
                    } else {
                        if (Math.abs(curScrollX) > leftMenu.getMeasuredWidth() >> 1) {
                            mScroller.startScroll(curScrollX, 0, -leftMenu.getMeasuredWidth() - curScrollX, 0, 200);
                        } else {
                            mScroller.startScroll(curScrollX, 0, -curScrollX, 0, 200);
                        }
                    }
                    //保证动画执行
                    invalidate();
                    isTestComplete = false;
                    isLeftRightScroll = false;
                    break;
            }
        } else {
            switch (ev.getActionMasked()) {
                case MotionEvent.ACTION_UP:
                    isTestComplete = false;
                    isLeftRightScroll = false;
                    break;

                default:
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 执行滚动
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        //动画是否执行完成
        if (!mScroller.computeScrollOffset()) {
            return;
        }
        //动画完成后立即滚动到相应的滚动位置
        int tempX = mScroller.getCurrX();
        scrollTo(tempX, 0);
    }

    private void getEventType(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                point.x = (int) ev.getX();
                point.y = (int) ev.getY();
                super.dispatchTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs((int) ev.getX() - point.x);
                int dy = Math.abs((int) ev.getY() - point.y);
                if (dx >= MIN_MOVE_DISTANCE && dx > dy) {
                    isLeftRightScroll = true;
                    isTestComplete = true;
                    point.x = (int) ev.getX();
                    point.y = (int) ev.getY();
                } else if (dy >= MIN_MOVE_DISTANCE && dy > dx) {
                    isLeftRightScroll = false;
                    isTestComplete = true;
                    point.x = (int) ev.getX();
                    point.y = (int) ev.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //上下事件不拦截
                super.dispatchTouchEvent(ev);
                isLeftRightScroll = false;
                isTestComplete = false;
                break;

        }
    }

    /**
     * 获得左侧区域
     *
     * @return
     */
    public int getLeftMenuId() {
        return leftMenuId;
    }

    /**
     * 获得中间区域
     *
     * @return
     */
    public int getMiddleMenuId() {
        return middleMenuId;
    }

    /**
     * 获得右侧区域
     *
     * @return
     */
    public int getRightMenuId() {
        return rightMenuId;
    }
}
