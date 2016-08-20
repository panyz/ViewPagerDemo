package com.pyz.viewpagerdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class WelcomeActivity extends Activity {
    private boolean isFirst = false;//是否第一次打开App

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        initData();
    }

    private void initData() {
        SharedPreferences sp = getSharedPreferences("WelcomeActivity", 0);
        isFirst = sp.getBoolean("isFirst",true);
        //判断是否第一次打开App,是的话跳转到引导页，否则跳转到主页
        if (isFirst) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gotoStartActivity();
                }
            }, 2000);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isFirst",false);
            editor.commit();
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gotoMainActivity();
                }
            }, 2000);
        }
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoStartActivity() {
        Intent intent = new Intent(WelcomeActivity.this, StartActivity.class);
        startActivity(intent);
        finish();
    }
}
