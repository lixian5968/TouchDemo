package com.zongbutech.lxdoublepulltwo.Demo2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by lixian on 2016/7/15.
 */
public class LxPull extends RelativeLayout {

    private Scroller mScroller;

    public LxPull(Context context) {
        super(context);
        init();
    }

    public LxPull(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LxPull(Context context, AttributeSet attrs) {
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

    int moveY;
    private int mMaxOffset=0;
    private float mLastY;
    private int mMoveY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = (int) (y - mLastY);
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


    int NORMAL_TIME = 600;
    private void move(int offset) {
        scrollBy(0, -offset);
    }
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
    private void hide() {
        mMoveY = getMeasuredHeight() + Math.abs(getScrollY());
        smoothScrollTo(0, getScrollY(), 0, -mMoveY, NORMAL_TIME * 3);
    }


    private void smoothScrollTo(int startX, int startY, int dx, int dy, int duration) {
        mScroller.startScroll(startX, startY, dx, dy, duration);
        invalidate();
    }



}
