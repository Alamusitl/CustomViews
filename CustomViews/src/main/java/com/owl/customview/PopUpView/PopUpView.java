package com.owl.customview.PopUpView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Alamusi on 2016/10/17.
 */

public class PopUpView extends View {

    private int mRectWidth;
    private int mRectHeight;
    private Paint mPaint;
    private RectF mBound;

    private Path mPath;

    public PopUpView(Context context) {
        this(context, null);
    }

    public PopUpView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PopUpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#2c97de"));
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();
        mBound = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mBound.set(0, 0, mRectWidth, mRectHeight);
        canvas.drawRoundRect(mBound, 30, 30, mPaint);

        mPath.moveTo(mRectWidth / 2 - 30, mRectHeight);
        mPath.lineTo(mRectWidth / 2, mRectHeight + 20);
        mPath.lineTo(mRectWidth / 2 + 30, mRectHeight);
        canvas.drawPath(mPath, mPaint);

        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int mWidth = widthMeasureSpec;
        int mHeight = heightMeasureSpec;

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
            mRectWidth = mWidth - getPaddingLeft() - getPaddingRight();
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
            mRectHeight = mHeight - getPaddingTop() - getPaddingBottom();
        }
        setMeasuredDimension(mWidth, mHeight);
    }
}
