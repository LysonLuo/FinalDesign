package me.lyson.design.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import me.lyson.design.R;
import me.lyson.design.ui.common.BaseActivity;

/**
 * Created by Lyson on 15/4/7.
 */
public class LaunchActivity extends BaseActivity {
    private boolean isActivityShowing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowToolbar(false);
        setContentView(R.layout.activity_launch);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isActivityShowing){
                    finish();
                    return;
                }
                Intent intent=new Intent(LaunchActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActivityShowing=true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActivityShowing=false;
    }
}
