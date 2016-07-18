package com.zongbutech.lxdoublepulltwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zongbutech.lxdoublepulltwo.Demo1.MainActivity;
import com.zongbutech.lxdoublepulltwo.Demo2.Main2Activity;
import com.zongbutech.lxdoublepulltwo.Demo3.Main3Activity;

public class AllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
    }


    public void demo1(View v){
        startActivity(new Intent(AllActivity.this, MainActivity.class));
    }

    public void demo2(View v){
        startActivity(new Intent(AllActivity.this, Main2Activity.class));
    }

    public void demo3(View v){
        startActivity(new Intent(AllActivity.this, Main3Activity.class));
    }
}
