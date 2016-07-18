package com.zongbutech.lxdoublepulltwo.Demo1;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by lixian on 2016/7/14.
 */
public class LxFragment extends FrameLayout {

    public LxFragment(Context context) {
        super(context);
        init();
    }

    public LxFragment(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LxFragment(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private Scroller mScroller;

    private void init() {
        mScroller = new Scroller(getContext());
    }

    @Override
    public void computeScroll() {
        //   computeScrollOffset()
//        当startScroll执行过程中即在duration时间内，
//        computeScrollOffset 方法会一直返回false
//        ，但当动画执行完成后会返回返加true.
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //如果没有再次 刷新 可能图片就不显示了
            postInvalidate();
        }
    }

    int mHeight;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getMeasuredHeight();
    }

    public void open() {
        smoothScrollTo(0, mHeight, 0, -mHeight, 800);
    }

    private void smoothScrollTo(int startX, int startY,
                                int dx, int dy, int duration) {
//        void Android.widget.Scroller.startScroll(int startX, int startY, int dx, int dy, int duration)
        //第一个参数是起始移动的x坐标值，第二个是起始移动的y坐标值，第三个第四个参数都是移到某点的坐标值，
        //  而duration 当然就是执行移动的时间。这个有什么用呢。要知道有什么用还得再看一个方法
        mScroller.startScroll(startX, startY, dx, dy, duration);
        invalidate();
    }




}
