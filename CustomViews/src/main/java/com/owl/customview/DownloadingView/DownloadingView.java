package com.owl.customview.DownloadingView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Alamusi on 2016/10/18.
 */

public class DownloadingView extends View {

    private Paint mPaint;
    private float mCircleStrokeWidth;
    private float mArrowStrokeWidth;
    private int mWidth;
    private int mHeight;
    private Point mTop;
    private Point mBottom;
    private Point mLeft;
    private Point mRight;
    private Thread mAnim = new Thread() {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                setPoints(i);
                postInvalidate();
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i = i == 3 ? 0 : i;
            }
        }
    };

    public DownloadingView(Context context) {
        this(context, null);
    }

    public DownloadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DownloadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mCircleStrokeWidth = 3;
        mArrowStrokeWidth = 5;
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(mCircleStrokeWidth);

        mTop = new Point();
        mBottom = new Point();
        mLeft = new Point();
        mRight = new Point();

        setBackgroundColor(Color.parseColor("#424446"));
        mAnim.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw) {
            mWidth = w;
        }
        if (h != oldh) {
            mHeight = h;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleStrokeWidth);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2 - 10, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mArrowStrokeWidth);
        canvas.drawLine(mTop.x, mTop.y, mBottom.x, mBottom.y, mPaint);
        canvas.drawLine(mLeft.x, mLeft.y, mBottom.x, mBottom.y, mPaint);
        canvas.drawLine(mRight.x, mRight.y, mBottom.x, mBottom.y, mPaint);

        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        mWidth = widthMeasureSpec;
        mHeight = heightMeasureSpec;
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    private synchronized void setPoints(int i) {
        int topX = 75;
        int topY = 47;
        int bottomY = topY + 18 * i;
        int leftX = 53;
        int leftY = bottomY - 18;
        int rightX = 97;
        mTop.set(topX, topY);
        mBottom.set(topX, bottomY);
        mLeft.set(leftX, leftY);
        mRight.set(rightX, leftY);
    }
}
