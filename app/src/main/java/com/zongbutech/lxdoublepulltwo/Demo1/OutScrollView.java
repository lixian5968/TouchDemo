package com.zongbutech.lxdoublepulltwo.Demo1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by hrz on 2016/7/8.
 */
public class OutScrollView extends ScrollView {

    private int mPullRelativeLayoutState = LxPullRelativeLayout.SHOW;
    private int mDownY;
    private int mMoveY;


    public OutScrollView(Context context) {
        super(context);
    }

    public OutScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OutScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = (int) ev.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                mMoveY = (int) ev.getRawY();
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mDownY = 0;
                mMoveY = 0;
                break;
        }

        Log.e("lxOut", "mMoveY:" + mMoveY + ",mDownY:" + mDownY + ",getScrollY():" + getScrollY()) ;
        Log.e("LxOut","super.onInterceptTouchEvent(ev):"+super.onInterceptTouchEvent(ev));
        //上移 mMoveY《 mDownY  向下滑动 不用过滤了
        if (mMoveY - mDownY < 0) {
            return super.onInterceptTouchEvent(ev);
        }

        //下移 mMoveY〉 mDownY  如果没有移动就消费掉
        //返回 true 消费掉了
        if (getScrollY() == 0) {
            if (mPullRelativeLayoutState == LxPullRelativeLayout.SHOW) {
                return false;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }


}
