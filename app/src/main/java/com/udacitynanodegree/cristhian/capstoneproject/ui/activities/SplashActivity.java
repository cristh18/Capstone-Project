package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.udacitynanodegree.cristhian.capstoneproject.R;

import rx.Observable;

public class SplashActivity extends BaseFragmentActivity {

    private final String TAG = SplashActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splash);
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
        loadPreferences();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(-1, R.anim.slide_out_activities);
    }

    private void goToAccount() {
        setResult(RESULT_OK);
        finish();
    }

    private void loadPreferences() {
        Observable.create(subscriber -> {
            subscriber.onNext(subscriber);    // Pass on the data to subscriber
            subscriber.onCompleted();
        }).subscribe(
                o -> Log.e(TAG, o.toString()),
                e -> Log.e(TAG, e.getMessage()),
                this::goToAccount);
    }


}
