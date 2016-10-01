package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.udacitynanodegree.cristhian.capstoneproject.R;

public class SplashActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        buildView();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void buildView() {
        startTimer();
    }

    private void goToAccount() {
        setResult(RESULT_OK);
        finish();
    }

    private void startTimer() {
        new CountDownTimer(5000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                goToAccount();
            }
        }.start();
    }
}
