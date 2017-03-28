package com.yunlong.lib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunlong.base.R;

import java.util.Locale;


public class PagerSlidingTabStrip extends HorizontalScrollView {

    public interface IconTabProvider {
        int getPageIconResId(int position);
    }

    public interface NoticeTabProvider {
        int getPageNoticeIconResId(int position);
    }

    public interface SpecialTabProvider {
        /**
         * 获取View，每一个选择区域的View
         *
         * @param position
         * @return
         */
        View getView(int position);

        /**
         * 获取正常背景
         *
         * @param position
         * @return
         */
        int getNormalBackGroundResource(int position);

        /**
         * 获取特殊背景
         *
         * @param position
         * @return
         */
        int getSelectBackGroundResource(int position);
    }

    // @formatter:off
    private static final int[] ATTRS = new int[]{
            android.R.attr.textSize,
            android.R.attr.textColor
    };
    // @formatter:on

    private LinearLayout.LayoutParams defaultTabLayoutParams;
    private LinearLayout.LayoutParams expandedTabLayoutParams;

    private final PageListener pageListener = new PageListener();
    public OnPageChangeListener delegatePageListener;

    private LinearLayout tabsContainer;
    private ViewPager pager;

    private int tabCount;

    private int currentPosition = 0;
    private int selectedPosition = 0;
    private float currentPositionOffset = 0f;

    private Paint rectPaint;
    private Paint dividerPaint;

    private int indicatorColor = 0xFF666666;
    private int underlineColor = 0x1A000000;
    private int dividerColor = 0x1A000000;

    private boolean shouldExpand = false;
    private boolean textAllCaps = true;

    private int scrollOffset = 52;
    private int indicatorHeight = 8;
    private int underlineHeight = 2;
    private int dividerPadding = 12;
    private int tabPaddingRightLeft = 0;
    private int tabPaddingTopBottom = 0;
    private int dividerWidth = 1;

    private int tabTextSize = 15;
    private int tabTextColor = 0xFF666666;
    private int selectedTabTextSize = 15;
    private int selectedTabTextColor = 0xFF666666;
    private Typeface tabTypeface = null;
    //private int tabTypefaceStyle = Typeface.BOLD;
    private int tabTypefaceStyle = Typeface.NORMAL;

    private int lastScrollX = 0;

    private int tabBackgroundResId = R.drawable.psts_tab_bg;

    private int backgroundResId = R.drawable.psts_tab_bg;

    private Locale locale;

    private OnTabClickListener onTabClickListener;

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setFillViewport(true);
        setWillNotDraw(false);

        tabsContainer = new LinearLayout(context);
        tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabsContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(tabsContainer);

        DisplayMetrics dm = getResources().getDisplayMetrics();

        scrollOffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scrollOffset, dm);
        indicatorHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorHeight, dm);
        underlineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, underlineHeight, dm);
        dividerPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerPadding, dm);
        tabPaddingRightLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tabPaddingRightLeft, dm);
        tabPaddingTopBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tabPaddingTopBottom, dm);
        dividerWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerWidth, dm);
        tabTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, tabTextSize, dm);
        selectedTabTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, selectedTabTextSize, dm);

        // get system attrs (android:textSize and android:textColor)

        TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);

        //tabTextSize = a.getDimensionPixelSize(0, tabTextSize);
        //tabTextColor = a.getColor(1, tabTextColor);

        a.recycle();

        // get custom attrs

        a = context.obtainStyledAttributes(attrs, R.styleable.PagerSlidingTabStrip);

        tabTextSize = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_zmsTabTextSize, tabTextSize);
        tabTextColor = a.getColor(R.styleable.PagerSlidingTabStrip_zmsTabTextColor, tabTextColor);

        selectedTabTextSize = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_zmsSelectedTabTextSize, selectedTabTextSize);
        selectedTabTextColor = a.getColor(R.styleable.PagerSlidingTabStrip_zmsSelectedTabTextColor, selectedTabTextColor);

        backgroundResId = a.getResourceId(R.styleable.PagerSlidingTabStrip_pstsBackground, backgroundResId);
        indicatorColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsIndicatorColor, indicatorColor);
        underlineColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsUnderlineColor, underlineColor);
        dividerColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsDividerColor, dividerColor);
        indicatorHeight = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsIndicatorHeight, indicatorHeight);
        underlineHeight = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsUnderlineHeight, underlineHeight);
        dividerPadding = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsDividerPadding, dividerPadding);
        tabPaddingRightLeft = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsTabPaddingLeftRight, tabPaddingRightLeft);
        tabPaddingTopBottom = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsTabPaddingTopBottom, tabPaddingTopBottom);
        tabBackgroundResId = a.getResourceId(R.styleable.PagerSlidingTabStrip_pstsTabBackground, tabBackgroundResId);
        shouldExpand = a.getBoolean(R.styleable.PagerSlidingTabStrip_pstsShouldExpand, shouldExpand);
        scrollOffset = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsScrollOffset, scrollOffset);
        textAllCaps = a.getBoolean(R.styleable.PagerSlidingTabStrip_pstsTextAllCaps, textAllCaps);

        a.recycle();

        rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStyle(Style.FILL);

        dividerPaint = new Paint();
        dividerPaint.setAntiAlias(true);
        dividerPaint.setStrokeWidth(dividerWidth);

        defaultTabLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        expandedTabLayoutParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);

        if (locale == null) {
            locale = getResources().getConfiguration().locale;
        }
    }

    public void setViewPager(ViewPager pager) {
        this.pager = pager;

        if (pager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }

        pager.setOnPageChangeListener(pageListener);

        notifyDataSetChanged();
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.delegatePageListener = listener;
    }

    public void notifyDataSetChanged() {

        tabsContainer.removeAllViews();

        if (backgroundResId > 0) {
            tabsContainer.setBackgroundResource(backgroundResId);
        }

        tabCount = pager.getAdapter().getCount();

        for (int i = 0; i < tabCount; i++) {

            if (pager.getAdapter() instanceof IconTabProvider) {
                addIconTab(i, ((IconTabProvider) pager.getAdapter()).getPageIconResId(i));
            } else if (pager.getAdapter() instanceof NoticeTabProvider) {
                PagerAdapter adapter = pager.getAdapter();
                addNoticeLayoutTab(i, adapter.getPageTitle(i).toString(), ((NoticeTabProvider) adapter).getPageNoticeIconResId(i));
            } else if (pager.getAdapter() instanceof SpecialTabProvider) {
                PagerAdapter adapter = pager.getAdapter();
                addSpecialNoticeLayoutTab(i, adapter.getPageTitle(i).toString(), (SpecialTabProvider) adapter);
            } else {
                addTextTab(i, pager.getAdapter().getPageTitle(i).toString());
            }

        }

        updateTabStyles();

        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @SuppressLint("NewApi")
            @Override
            public void onGlobalLayout() {

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                currentPosition = pager.getCurrentItem();
                scrollToChild(currentPosition, 0);
            }
        });

    }

    private void addTextTab(final int position, String title) {

        TextView tab = new TextView(getContext());
        tab.setText(title);
        tab.setGravity(Gravity.CENTER);
        tab.setSingleLine();

        addTab(position, tab);
    }

    /**
     * @param position：位置
     * @param title：标题
     * @param NoticeIconId:图标id
     */
    private void addNoticeLayoutTab(final int position, String title, int NoticeIconId) {

        LinearLayout tab = new LinearLayout(getContext());
        tab.setGravity(Gravity.CENTER);

        LinearLayout tabChild = new LinearLayout(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tabChild.setGravity(Gravity.CENTER);

        tab.addView(tabChild, params);

        TextView tabText = new TextView(getContext());
        tabText.setText(title);
        tabText.setGravity(Gravity.CENTER);
        tabText.setSingleLine();
        tabChild.addView(tabText);

        if (NoticeIconId > 0) {
            LinearLayout llNotice = new LinearLayout(getContext());
            LinearLayout.LayoutParams childParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            llNotice.setGravity(Gravity.TOP | Gravity.RIGHT);
            tabChild.addView(llNotice, childParams);

            ImageView ivNotice = new ImageView(getContext());
            ivNotice.setImageResource(NoticeIconId);
            llNotice.addView(ivNotice);
        }
        addTab(position, tab);
    }

    /**
     * 获取特殊的位置
     *
     * @param position
     * @param title
     * @param tabProvider
     */
    private void addSpecialNoticeLayoutTab(final int position, String title, SpecialTabProvider tabProvider) {
        addTab(position, tabProvider.getView(position));
    }

    private void addIconTab(final int position, int resId) {

        ImageButton tab = new ImageButton(getContext());
        tab.setImageResource(resId);

        addTab(position, tab);

    }

    private void addTab(final int position, View tab) {
        tab.setFocusable(true);
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = true;
                if (onTabClickListener != null) {
                    flag = onTabClickListener.canTableClick(position);
                }
                if (flag)
                    tabClick(position);
            }
        });

        tab.setPadding(tabPaddingRightLeft, tabPaddingTopBottom, tabPaddingRightLeft, tabPaddingTopBottom);
        tabsContainer.addView(tab, position, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
    }

    /**
     * 当前点击条目
     *
     * @param position
     */
    protected void tabClick(int position) {
        pager.setCurrentItem(position);
    }

    private void updateTabStyles() {
        PagerAdapter adapter = pager.getAdapter();
        if (adapter != null && adapter instanceof SpecialTabProvider) {
            updateSpecialTabStyles();
        } else {
            updateNormalTabStyles();
        }
    }

    /**
     * 更新normalTabStyle
     */
    private void updateNormalTabStyles() {
        for (int i = 0; i < tabCount; i++) {

            View v = tabsContainer.getChildAt(i);

            v.setBackgroundResource(tabBackgroundResId);

            if (v instanceof TextView) {

                TextView tab = (TextView) v;
                tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
                tab.setTypeface(tabTypeface, tabTypefaceStyle);
                tab.setTextColor(tabTextColor);

                // setAllCaps() is only available from API 14, so the upper case is made manually if we are on a
                // pre-ICS-build
                if (textAllCaps) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                        tab.setAllCaps(true);
                    } else {
                        tab.setText(tab.getText().toString().toUpperCase(locale));
                    }
                }
                if (i == selectedPosition) {
                    tab.setTextColor(selectedTabTextColor);
                    tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, selectedTabTextSize);
                }
            } else if (v instanceof LinearLayout) {
                LinearLayout tab = (LinearLayout) v;
                if (tab.getChildCount() > 0 && tab.getChildAt(0) instanceof LinearLayout) {
                    LinearLayout llView = (LinearLayout) tab.getChildAt(0);
                    if (llView.getChildCount() > 0 && llView.getChildAt(0) instanceof TextView) {
                        TextView tabText = (TextView) llView.getChildAt(0);
                        tabText.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
                        tabText.setTypeface(tabTypeface, tabTypefaceStyle);
                        tabText.setTextColor(tabTextColor);

                        // setAllCaps() is only available from API 14, so the upper case is made manually if we are on a
                        // pre-ICS-build
                        if (textAllCaps) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                                tabText.setAllCaps(true);
                            } else {
                                tabText.setText(tabText.getText().toString().toUpperCase(locale));
                            }
                        }
                        if (i == selectedPosition) {
                            tabText.setTextColor(selectedTabTextColor);
                            tabText.setTextSize(TypedValue.COMPLEX_UNIT_PX, selectedTabTextSize);
                        }
                    }

                }
            }
        }
    }

    /**
     * 更新speicalTabStyle
     */
    private void updateSpecialTabStyles() {
        PagerAdapter pagerAdapter = pager.getAdapter();
        SpecialTabProvider tabProvider = null;
        if (pagerAdapter instanceof SpecialTabProvider) {
            tabProvider = (SpecialTabProvider) pagerAdapter;
        }
        for (int i = 0; i < tabCount; i++) {

            View v = tabsContainer.getChildAt(i);
            v.setBackgroundResource(tabBackgroundResId);
            if (v instanceof TextView) {
                TextView tab = (TextView) v;
                tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
                tab.setTypeface(tabTypeface, tabTypefaceStyle);
                tab.setTextColor(tabTextColor);

                // setAllCaps() is only available from API 14, so the upper case is made manually if we are on a
                // pre-ICS-build
                if (textAllCaps) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                        tab.setAllCaps(true);
                    } else {
                        tab.setText(tab.getText().toString().toUpperCase(locale));
                    }
                }
                if (i == selectedPosition) {
                    tab.setTextColor(selectedTabTextColor);
                    tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, selectedTabTextSize);
                    if (tabProvider != null) {
                        tab.setBackgroundResource(tabProvider.getSelectBackGroundResource(i));
                    }
                } else {
                    if (tabProvider != null) {
                        tab.setBackgroundResource(tabProvider.getNormalBackGroundResource(i));
                    }
                }
                tab.setPadding(tabPaddingRightLeft, tabPaddingTopBottom, tabPaddingRightLeft, tabPaddingTopBottom);
            } else if (v instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v;
                TextView tab = null;
                for (int j = 0; j < viewGroup.getChildCount(); i++) {
                    if (viewGroup.getChildAt(j) instanceof TextView) {
                        tab = (TextView) viewGroup.getChildAt(j);
                        break;
                    }
                }
                if (tab != null) {
                    tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
                    tab.setTypeface(tabTypeface, tabTypefaceStyle);
                    tab.setTextColor(tabTextColor);

                    // setAllCaps() is only available from API 14, so the upper case is made manually if we are on a
                    // pre-ICS-build
                    if (textAllCaps) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                            tab.setAllCaps(true);
                        } else {
                            tab.setText(tab.getText().toString().toUpperCase(locale));
                        }
                    }
                    if (i == selectedPosition) {
                        tab.setTextColor(selectedTabTextColor);
                        tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, selectedTabTextSize);
                        if (tabProvider != null) {
                            v.setBackgroundResource(tabProvider.getSelectBackGroundResource(i));
                        }
                    } else {
                        if (tabProvider != null) {
                            v.setBackgroundResource(tabProvider.getNormalBackGroundResource(i));
                        }
                    }
                }

                viewGroup.setPadding(tabPaddingRightLeft, tabPaddingTopBottom, tabPaddingRightLeft, tabPaddingTopBottom);
            }
        }
    }

    private void scrollToChild(int position, int offset) {

        if (tabCount == 0) {
            return;
        }

        int newScrollX = tabsContainer.getChildAt(position).getLeft() + offset;

        if (position > 0 || offset > 0) {
            newScrollX -= scrollOffset;
        }

        if (newScrollX != lastScrollX) {
            lastScrollX = newScrollX;
            scrollTo(newScrollX, 0);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isInEditMode() || tabCount == 0) {
            return;
        }

        final int height = getHeight();

        // draw indicator line

        rectPaint.setColor(indicatorColor);

        // default: line below current tab
        View currentTab = tabsContainer.getChildAt(currentPosition);
        float lineLeft = currentTab.getLeft();
        float lineRight = currentTab.getRight();

        // if there is an offset, start interpolating left and right coordinates between current and next tab
        if (currentPositionOffset > 0f && currentPosition < tabCount - 1) {

            View nextTab = tabsContainer.getChildAt(currentPosition + 1);
            final float nextTabLeft = nextTab.getLeft();
            final float nextTabRight = nextTab.getRight();

            lineLeft = (currentPositionOffset * nextTabLeft + (1f - currentPositionOffset) * lineLeft);
            lineRight = (currentPositionOffset * nextTabRight + (1f - currentPositionOffset) * lineRight);
        }

        canvas.drawRect(lineLeft, height - indicatorHeight, lineRight, height, rectPaint);

        // draw underline

        rectPaint.setColor(underlineColor);
        canvas.drawRect(0, height - underlineHeight, tabsContainer.getWidth(), height, rectPaint);

        // draw divider

        dividerPaint.setColor(dividerColor);
        for (int i = 0; i < tabCount - 1; i++) {
            View tab = tabsContainer.getChildAt(i);
            canvas.drawLine(tab.getRight(), dividerPadding, tab.getRight(), height - dividerPadding, dividerPaint);
        }
    }

    private class PageListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            currentPosition = position;
            currentPositionOffset = positionOffset;

            scrollToChild(position, (int) (positionOffset * tabsContainer.getChildAt(position).getWidth()));

            invalidate();

            if (delegatePageListener != null) {
                delegatePageListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                scrollToChild(pager.getCurrentItem(), 0);
            }

            if (delegatePageListener != null) {
                delegatePageListener.onPageScrollStateChanged(state);
            }
        }

        @Override
        public void onPageSelected(int position) {
            selectedPosition = position;
            updateTabStyles();
            if (delegatePageListener != null) {
                delegatePageListener.onPageSelected(position);
            }
        }

    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        invalidate();
    }

    public void setIndicatorColorResource(int resId) {
        this.indicatorColor = getResources().getColor(resId);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.indicatorColor;
    }

    public void setIndicatorHeight(int indicatorLineHeightPx) {
        this.indicatorHeight = indicatorLineHeightPx;
        invalidate();
    }

    public int getIndicatorHeight() {
        return indicatorHeight;
    }

    public void setUnderlineColor(int underlineColor) {
        this.underlineColor = underlineColor;
        invalidate();
    }

    public void setUnderlineColorResource(int resId) {
        this.underlineColor = getResources().getColor(resId);
        invalidate();
    }

    public int getUnderlineColor() {
        return underlineColor;
    }

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        invalidate();
    }

    public void setDividerColorResource(int resId) {
        this.dividerColor = getResources().getColor(resId);
        invalidate();
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public void setUnderlineHeight(int underlineHeightPx) {
        this.underlineHeight = underlineHeightPx;
        invalidate();
    }

    public int getUnderlineHeight() {
        return underlineHeight;
    }

    public void setDividerPadding(int dividerPaddingPx) {
        this.dividerPadding = dividerPaddingPx;
        invalidate();
    }

    public int getDividerPadding() {
        return dividerPadding;
    }

    public void setScrollOffset(int scrollOffsetPx) {
        this.scrollOffset = scrollOffsetPx;
        invalidate();
    }

    public int getScrollOffset() {
        return scrollOffset;
    }

    public void setShouldExpand(boolean shouldExpand) {
        this.shouldExpand = shouldExpand;
        requestLayout();
    }

    public boolean getShouldExpand() {
        return shouldExpand;
    }

    public boolean isTextAllCaps() {
        return textAllCaps;
    }

    public void setAllCaps(boolean textAllCaps) {
        this.textAllCaps = textAllCaps;
    }

    public void setTextSize(int textSizePx) {
        this.tabTextSize = textSizePx;
        updateTabStyles();
    }

    public int getTextSize() {
        return tabTextSize;
    }

    public void setTextColor(int textColor) {
        this.tabTextColor = textColor;
        updateTabStyles();
    }

    public void setTextColorResource(int resId) {
        this.tabTextColor = getResources().getColor(resId);
        updateTabStyles();
    }

    public int getTextColor() {
        return tabTextColor;
    }

    public void setSelectedTextColor(int textColor) {
        this.selectedTabTextColor = textColor;
        updateTabStyles();
    }

    public void setSelectedTextColorResource(int resId) {
        this.selectedTabTextColor = getResources().getColor(resId);
        updateTabStyles();
    }

    public int getSelectedTextColor() {
        return selectedTabTextColor;
    }

    public void setTypeface(Typeface typeface, int style) {
        this.tabTypeface = typeface;
        this.tabTypefaceStyle = style;
        updateTabStyles();
    }

    public void setTabBackground(int resId) {
        this.tabBackgroundResId = resId;
    }

    public int getTabBackground() {
        return tabBackgroundResId;
    }

    public void setTabPaddingLeftRight(int paddingPx) {
        this.tabPaddingRightLeft = paddingPx;
        updateTabStyles();
    }

    public int getTabPaddingLeftRight() {
        return tabPaddingRightLeft;
    }

    public void setTabPaddingTopBottom(int paddingPx) {
        this.tabPaddingTopBottom = paddingPx;
        updateTabStyles();
    }

    public int getTabPaddingTopBottom() {
        return tabPaddingTopBottom;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        currentPosition = savedState.currentPosition;
        requestLayout();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.currentPosition = currentPosition;
        return savedState;
    }

    /**
     * 隐藏提示信息
     *
     * @param position
     */
    public void hideNotice(int position) {
        if (position < 0 || position > tabCount || position >= tabsContainer.getChildCount()) {
            return;
        }
        View v = tabsContainer.getChildAt(position);
        LinearLayout tab = (LinearLayout) v;
        if (tab.getChildCount() > 0 && tab.getChildAt(0) instanceof LinearLayout) {
            LinearLayout llView = (LinearLayout) tab.getChildAt(0);
            if (llView.getChildCount() > 0 && llView.getChildAt(1) instanceof LinearLayout) {
                LinearLayout llNotice = (LinearLayout) llView.getChildAt(1);
                llNotice.setVisibility(GONE);
            }
        }

    }

    /**
     * 展示提示信息
     *
     * @param position
     */
    public void showNotice(int position) {
        if (position < 0 || position > tabCount || position >= tabsContainer.getChildCount()) {
            return;
        }
        View v = tabsContainer.getChildAt(position);
        LinearLayout tab = (LinearLayout) v;
        if (tab.getChildCount() > 0 && tab.getChildAt(0) instanceof LinearLayout) {
            LinearLayout llView = (LinearLayout) tab.getChildAt(0);
            if (llView.getChildCount() > 0 && llView.getChildAt(1) instanceof LinearLayout) {
                LinearLayout llNotice = (LinearLayout) llView.getChildAt(1);
                llNotice.setVisibility(VISIBLE);
            }
        }
    }

    static class SavedState extends BaseSavedState {
        int currentPosition;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            currentPosition = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(currentPosition);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    public interface OnTabClickListener {
        /**
         * Tab是否被点击
         *
         * @param position
         * @return
         */
        boolean canTableClick(int position);
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.onTabClickListener = onTabClickListener;
    }

    /**
     * 切换到某个Position
     */
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        pager.setCurrentItem(currentPosition);
        setSelectedPosition(currentPosition);
    }

    /**
     * 设置选中的Position
     *
     * @param position
     */
    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        updateTabStyles();
    }
}
