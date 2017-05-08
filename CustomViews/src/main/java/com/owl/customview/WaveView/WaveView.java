package com.owl.customview.WaveView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.owl.customview.R;


/**
 * Created by Alamusi on 2016/10/18.
 */

public class WaveView extends View {

    private int mWaveCount;
    private int mWaveWidth;
    private int mWaveHeight;
    private int mMode;
    private int mColor;

    private int mWidth;
    private int mHeight;
    private int mRectWidth;
    private int mRectHeight;

    private Paint mPaint;
    private Path mPath;

    private int MODE_TRIANGLE = -2;
    private int MODE_CIRCLE = -1;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WaveView, defStyleAttr, 0);
        mWaveCount = a.getInt(R.styleable.WaveView_waveCount, 0);
        mWaveWidth = a.getInt(R.styleable.WaveView_waveWidth, 0);
        mMode = a.getInteger(R.styleable.WaveView_mode, -2);
        mColor = a.getColor(R.styleable.WaveView_android_color, Color.parseColor("#2C97DE"));
        a.recycle();
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mWaveHeight = mRectHeight / mWaveCount;
        float padding = (mWidth - mRectWidth) / 2;
        canvas.drawRect(padding, padding, mRectWidth + padding, mRectHeight + padding, mPaint);

        if (mMode == MODE_TRIANGLE) {
            float startX = padding + mRectWidth;
            float startY = (mHeight - mRectHeight) / 2;
            mPath.moveTo(startX, startY);
            for (int i = 0; i < mWaveCount; i++) {
                mPath.lineTo(startX + mWaveWidth, startY + i * mWaveHeight + mWaveHeight / 2);
                mPath.lineTo(startX, startY + mWaveHeight * (i + 1));
            }
            mPath.close();
            canvas.drawPath(mPath, mPaint);
            startX = padding;
            startY = (mHeight - mRectHeight) / 2;
            mPath.moveTo(startX, startY);
            for (int i = 0; i < mWaveCount; i++) {
                mPath.lineTo(startX - mWaveWidth, startY + i * mWaveHeight + mWaveHeight / 2);
                mPath.lineTo(startX, startY + mWaveHeight * (i + 1));
            }
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        } else if (mMode == MODE_CIRCLE) {
            mWaveWidth = mWaveHeight / 2;
            for (int i = 0; i < mWaveCount; i++) {
                canvas.drawCircle(padding + mRectWidth, (mHeight - mRectHeight) / 2 + mWaveHeight / 2 + i * mWaveHeight, mWaveWidth, mPaint);
            }
            for (int i = 0; i < mWaveCount; i++) {
                canvas.drawCircle(padding, (mHeight - mRectHeight) / 2 + mWaveHeight / 2 + i * mWaveHeight, mWaveWidth, mPaint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
            mRectWidth = mWidth - getPaddingLeft() - getPaddingRight();
        } else if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getContext().getResources().getDisplayMetrics());
            mRectWidth = mWidth - getPaddingLeft() - getPaddingRight();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
            mRectHeight = mHeight - getPaddingTop() - getPaddingBottom();
        } else if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getContext().getResources().getDisplayMetrics());
            mRectHeight = mHeight - getPaddingTop() - getPaddingBottom();
        }
        setMeasuredDimension(mWidth, mHeight);
    }
}
