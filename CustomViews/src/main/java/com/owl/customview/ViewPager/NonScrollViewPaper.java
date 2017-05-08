package com.owl.customview.ViewPager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Alamusi on 2016/11/4.
 */

public class NonScrollViewPaper extends ViewPager {

    private boolean mNonScroll = true;

    public NonScrollViewPaper(Context context) {
        super(context);
    }

    public NonScrollViewPaper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mNonScroll) {
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mNonScroll) {
            return false;
        } else {
            return super.onTouchEvent(ev);
        }
    }
}
