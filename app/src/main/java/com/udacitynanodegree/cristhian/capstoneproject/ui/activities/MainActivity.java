package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.os.Bundle;
import android.view.View;

import com.udacitynanodegree.cristhian.capstoneproject.R;

public class MainActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}
