package com.zongbutech.lxdoublepulltwo.Demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zongbutech.lxdoublepulltwo.R;

public class MainActivity extends AppCompatActivity {
    LxFragment main_header;
    LxPullRelativeLayout main_body;
    ImageView mIconIv;
    View mContentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_header = (LxFragment) findViewById(R.id.main_header);
        main_body = (LxPullRelativeLayout) findViewById(R.id.main_body);
        mIconIv = (ImageView) findViewById(R.id.main_haibao);
        mContentView = findViewById(R.id.main_content);

//        OnGlobalLayoutListener 是ViewTreeObserver的内部类，当一个视图树的布局发生改变时，
//        可以被ViewTreeObserver监听到，这是一个注册监听视图树的观察者(observer)，
//        在视图树的全局事件改变时得到通知。ViewTreeObserver不能直接实例化，而是通过getViewTreeObserver()获得。
        main_body.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        main_header.open();
                        main_body.hide(100);
                        main_body.open();
                        int iconHeight = mIconIv.getMeasuredHeight();
                        setContentViewMarTop(iconHeight / 2);
                    }
                });
    }


    private void setContentViewMarTop(int top) {
        RelativeLayout.LayoutParams mContentViewParams =
                (RelativeLayout.LayoutParams) mContentView.getLayoutParams();
        mContentViewParams.setMargins(0, top, 0, 0);
    }

}
