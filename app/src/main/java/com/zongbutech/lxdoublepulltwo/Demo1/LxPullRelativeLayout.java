package com.zongbutech.lxdoublepulltwo.Demo1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by lixian on 2016/7/14.
 */
public class LxPullRelativeLayout extends RelativeLayout {


    public static final int SHOW = 1000;
    public static final int HIDE = 2000;
    public static final int MOVE = 3000;
    public static final int OPEN_START = 4000;
    public static final int OPEN_FINISH = 5000;

    private Scroller mScroller;

    public LxPullRelativeLayout(Context context) {
        super(context);
        init();
    }

    public LxPullRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LxPullRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mScroller = new Scroller(getContext());
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    int mMoveY;

    public void hide(int time) {
        mMoveY = getMeasuredHeight() + Math.abs(getScrollY());
        smoothScrollTo(0, getScrollY(), 0, -mMoveY, time);
    }

    private void smoothScrollTo(int startX, int startY, int dx, int dy, int duration) {
        mScroller.startScroll(startX, startY, dx, dy, duration);
        invalidate();
    }

    int NORMAL_TIME = 600;
    public void open() {
        smoothScrollTo(0, -mMoveY, 0, mMoveY, NORMAL_TIME);
    }




    private float mLastY;
    int moveY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = (int) (y - mLastY);
                Log.e("lx", "getScrollY:" + getScrollY() + ",moveY:" + moveY + ",offset:" + moveY / 2);
                if (getScrollY() <= 0 && moveY > 0) {
                    int offset = moveY / 2;
                    move(offset);
                }
                mLastY = y;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                changeState();
                break;
        }
        return true;
    }
    private int mMaxOffset=0;
    private void changeState() {
        if (Math.abs(getScrollY()) > mMaxOffset + 50) {
            hide();
        } else {
            show();
        }
    }
    private void show() {
        smoothScrollTo(0, getScrollY(), 0, -getScrollY(), getScrollY());
    }
    private void move(int offset) {
        scrollBy(0, -offset);
    }

    private void hide() {
        mMoveY = getMeasuredHeight() + Math.abs(getScrollY());
        smoothScrollTo(0, getScrollY(), 0, -mMoveY, NORMAL_TIME * 3);
    }
}
