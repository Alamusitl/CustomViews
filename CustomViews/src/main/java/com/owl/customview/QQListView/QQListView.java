package com.owl.customview.QQListView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.owl.customview.R;


/**
 * Created by Alamusi on 2016/9/21.
 */
public class QQListView extends ListView {

    /**
     * 用户滑动的最小距离
     */
    private int mTouchSlop;
    /**
     * 是否响应滑动
     */
    private boolean mIsSliding;
    /**
     * 手指按下时的x坐标
     */
    private int mActionDownX;
    /**
     * 手指按下时的y坐标
     */
    private int mActionDownY;
    /**
     * 手指移动时的x坐标
     */
    private int mActionMoveX;
    /**
     * 手指移动时的y坐标
     */
    private int mActionMoveY;

    private LayoutInflater mInflater;

    private PopupWindow mPopupWindow;
    private int mPopupWindowWidth;
    private int mPopupWindowHeight;

    /**
     * 删除按钮
     */
    private Button mBtnDelete;
    /**
     * 为删除按钮提供一个回调接口
     */
    private DelButtonClickListener mListener;
    /**
     * 当前手指触摸的View
     */
    private View mCurrentTouchView;
    /**
     * 当前手指触摸的位置
     */
    private int mCurrentTouchPos;

    public QQListView(Context context) {
        this(context, null);
    }

    public QQListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mInflater = LayoutInflater.from(getContext());
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        View view = mInflater.inflate(R.layout.listview_delete_btn, null);
        mBtnDelete = (Button) view.findViewById(R.id.id_item_delete_btn);
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        /**
         * 先调用measure，否则拿不到width 和 height
         */
        mPopupWindow.getContentView().measure(0, 0);
        mPopupWindowWidth = mPopupWindow.getContentView().getMeasuredWidth();
        mPopupWindowHeight = mPopupWindow.getContentView().getMeasuredHeight();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mActionDownX = x;
                mActionDownY = y;
                /**
                 * 如果当前popupWindow显示，则直接隐藏，然后屏蔽ListView的touch事件的下传
                 */
                if (mPopupWindow.isShowing()) {
                    dismissPopupWindow();
                    return false;
                }
                // 获得当前手指按下时的item的位置
                mCurrentTouchPos = pointToPosition(mActionDownX, mActionDownY);
                // 获得当前手指按下时的item
                mCurrentTouchView = getChildAt(mCurrentTouchPos - getFirstVisiblePosition());
                break;
            case MotionEvent.ACTION_MOVE:
                mActionMoveX = x;
                mActionMoveY = y;
                int passX = mActionMoveX - mActionDownX;
                int passY = mActionMoveY - mActionDownY;
                /**
                 * 判断是否是从右到左的滑动
                 */
                if (mActionMoveX < mActionDownY && Math.abs(passX) > mTouchSlop && Math.abs(passY) < mTouchSlop) {
                    mIsSliding = true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        /**
         * 如果是从右到左的滑动才相应
         */
        if (mIsSliding) {
            switch (action) {
                case MotionEvent.ACTION_MOVE:
                    int[] location = new int[2];
                    // 获得当前item的位置x与y
                    mCurrentTouchView.getLocationOnScreen(location);
                    mPopupWindow.setAnimationStyle(R.style.popwindow_delete_anim_style);
                    mPopupWindow.update();
                    mPopupWindow.showAtLocation(mCurrentTouchView, Gravity.LEFT | Gravity.TOP, location[0] + mCurrentTouchView.getWidth(), location[1] + mCurrentTouchView.getHeight() / 2 - mPopupWindowHeight / 2);
                    mBtnDelete.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mListener != null) {
                                mListener.onClick(mCurrentTouchPos);
                                mPopupWindow.dismiss();
                            }
                        }
                    });
                    break;
                case MotionEvent.ACTION_UP:
                    mIsSliding = false;
                    break;
            }
            // 相应滑动期间屏幕itemClick事件，避免发生冲突
            return true;
        }
        return super.onTouchEvent(ev);
    }

    private void dismissPopupWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public void setDelButtonClickListener(DelButtonClickListener listener) {
        mListener = listener;
    }

    public interface DelButtonClickListener {
        void onClick(int position);
    }
}
