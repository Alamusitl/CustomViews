package com.owl.customview.PanelView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.owl.customview.R;


/**
 * Created by Alamusi on 2016/10/26.
 */

public class PanelView extends View {

    /**
     * 控件宽度
     */
    private int mWidth;
    /**
     * 宽度高度
     */
    private int mHeight;
    /**
     * 刻度百分比
     */
    private int mPercent;
    /**
     * 刻度宽度
     */
    private int mTikeWidth;
    /**
     * 刻度个数
     */
    private int mTikeCount;
    /**
     * 文字颜色
     */
    private int mTextColor;
    /**
     * 文字大小
     */
    private int mTextSize;
    /**
     * 文字内容
     */
    private String mText;
    /**
     * 文字矩形的宽
     */
    private int mRectWidth;
    /**
     * 文字矩形的高
     */
    private int mRectHeight;
    /**
     * 内部小圆和指针的颜色
     */
    private int mMinCirclrColor;
    /**
     * 内部小圆的半径
     */
    private int mMinCircleRadius;
    /**
     * 第二弧的宽度
     */
    private int mScreenArcWidth;
    /**
     * 弧度的颜色
     */
    private int mArcColor;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 外弧的绘制区域
     */
    private RectF mOutArcBound;

    public PanelView(Context context) {
        this(context, null);
    }

    public PanelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initVariable(context, attrs, defStyleAttr);
    }

    private void initVariable(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PanelView, defStyleAttr, 0);
        mArcColor = a.getColor(R.styleable.PanelView_arcColor, Color.parseColor("#5FB1ED"));
        mMinCirclrColor = a.getColor(R.styleable.PanelView_pointerColor, Color.parseColor("#C9DEEE"));
        mTikeCount = a.getInt(R.styleable.PanelView_tikeCount, 12);
        mTextSize = a.getDimensionPixelSize(R.styleable.PanelView_android_textSize, 24);
        mText = a.getString(R.styleable.PanelView_android_text);
        mScreenArcWidth = 50;
        a.recycle();

        mPaint = new Paint();
        int strokeWidth = 3;
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mArcColor);

        mOutArcBound = new RectF(0, 0, 0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 最外面的圆弧
        mOutArcBound.set(3, 3, mWidth - 3, mHeight - 3);
        canvas.drawArc(mOutArcBound, 145, 250, false, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
        }

        if (heightMode == MeasureSpec.UNSPECIFIED) {
            mHeight = heightSize;
        } else {
            mHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
        }
        setMeasuredDimension(mWidth, mHeight);
    }
}
