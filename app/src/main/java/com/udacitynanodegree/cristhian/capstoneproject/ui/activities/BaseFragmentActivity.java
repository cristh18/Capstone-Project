package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.androidadvance.topsnackbar.TSnackbar;
import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.app.config.Config;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.DialogFragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentListener;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.ColoredSnackbar;
import com.udacitynanodegree.cristhian.capstoneproject.utils.FontCache;
import com.udacitynanodegree.cristhian.capstoneproject.utils.OrientationUtil;

import java.util.ArrayList;

public class BaseFragmentActivity extends FragmentActivity implements FragmentListener, FragmentManager.OnBackStackChangedListener {

    public static final int ACTION_REQUEST_GALLERY = 101;

    public static final int CAPTURE_IMAGE_REQUEST_CODE = 102;

    protected final String ERROR_MESSAGE = "type_message";

    private ArrayList<FragmentView> pendingForClose = new ArrayList<>();

    private ArrayList<FragmentView> pendingForOpen = new ArrayList<>();

    private ArrayList<DialogFragmentView> pendingDialogs = new ArrayList<>();

    private FragmentManager fragmentManager;

    private ProgressDialog progressDialog;

    private boolean pause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFonts();

        OrientationUtil.adjustScreenOrientation(this);

        setBackgroundActivity();

        fragmentManager = getSupportFragmentManager();

        progressDialog = new ProgressDialog(this);

        progressDialog.setIndeterminate(true);

        progressDialog.setCancelable(false);

        if (!Config.DEBUG) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }

        showMessage();
    }

    private void showMessage() {

        int IS_ERROR_MESSAGE = 1;
        String SNACK_MESSAGE = "snack_message";

        Bundle extraData = getIntent().getExtras();

        if (extraData != null) {

            String message = extraData.getString(SNACK_MESSAGE);

            if (!TextUtils.isEmpty(message)) {

                boolean isError = extraData.getInt(ERROR_MESSAGE, 0) == IS_ERROR_MESSAGE;

                View view = findViewById(android.R.id.content);

                TSnackbar snackbar = TSnackbar.make(view, message, TSnackbar.LENGTH_SHORT);

                if (isError) {
                    ColoredSnackbar.error(snackbar).show();
                } else {
                    ColoredSnackbar.success(snackbar).show();
                }
            }
        }
    }

    private void initFonts() {
        FontCache.add(FontCache.BOLD, "fonts/SourceSansPro-Bold.ttf", getApplicationContext());
        FontCache.add(FontCache.REGULAR, "fonts/SourceSansPro-Regular.ttf", getApplicationContext());
        FontCache.add(FontCache.LIGHT, "fonts/SourceSansPro-Light.ttf", getApplicationContext());
    }

    protected void setBackgroundActivity() {
        getWindow().setBackgroundDrawable(null);
    }

    protected void showProgressDialog(String message) {
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    protected void dismissProgressDialog() {
        if (isProgressVisible()) {
            progressDialog.dismiss();
        }
    }

    protected boolean isProgressVisible() {
        return progressDialog != null && progressDialog.isShowing();
    }

    public boolean isPause() {
        return pause;
    }

    protected void replaceFragment(FragmentView fragmentView) {

        if (isPause()) {
            pendingForOpen.add(fragmentView);
        } else {

            fragmentView.setListener(this);

            FragmentTransaction ft = fragmentManager.beginTransaction();

            if (fragmentView.isAddOnStack()) {
                ft.addToBackStack(fragmentView.getName());
            }
            if (fragmentView.isAnimate()) {
                ft.setCustomAnimations(fragmentView.getEnter(), fragmentView.getExit(), fragmentView.getPopEnter(), fragmentView.getPopExit());
            }

            ft.replace(fragmentView.getContainer(), fragmentView, fragmentView.getName());

            ft.commit();
        }
    }

    protected void addFragment(FragmentView fragmentView) {

        try {

            if (isPause()) {

                pendingForOpen.add(fragmentView);

            } else {

                fragmentView.setListener(this);

                FragmentTransaction ft = fragmentManager.beginTransaction();

                if (fragmentManager.getBackStackEntryCount() >= 0) {
                    ft.setCustomAnimations(fragmentView.getEnter(), fragmentView.getExit(), fragmentView.getPopEnter(), fragmentView.getPopExit());
                }

                ft.add(fragmentView.getContainer(), fragmentView, fragmentView.getName());

                ft.addToBackStack(fragmentView.getName());

                ft.commit();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void addOrReplaceFragment(FragmentView fragmentView) {

        int count = fragmentManager.getBackStackEntryCount();

        if (count == 0) {
            replaceFragment(fragmentView);
        } else {
            addFragment(fragmentView);
        }
    }

    public FragmentView getActiveFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }
        String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        return (FragmentView) getSupportFragmentManager().findFragmentByTag(tag);
    }

    protected IronHideApplication getApp() {
        return IronHideApplication.getApp();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        pause = false;
        checkPendingView();
    }

    @Override
    public void finish() {
        if (fragmentManager != null) {
            fragmentManager.removeOnBackStackChangedListener(this);
        }
        super.finish();
    }

    @Override
    public void onClose(FragmentView view) {

        if (!isPause()) {

            if (fragmentManager.getBackStackEntryCount() < 1) {
                finish();
            }
            fragmentManager.popBackStack(view.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        } else {
            pendingForClose.add(view);
        }
    }

    @Override
    public void onBackPressed() {

        int countF = fragmentManager != null ? fragmentManager.getBackStackEntryCount() : 0;

        if (countF != 0) {

            String fragmentTag = fragmentManager.getBackStackEntryAt(countF - 1).getName();

            Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);

            if (fragment instanceof FragmentView) {

                FragmentView currentFragment = (FragmentView) fragment;

                if (currentFragment.canBack()) {
                    super.onBackPressed();
                } else {
                    currentFragment.backPressed();
                }
            }
        } else {
            onBackPressFromEmptyStack();
        }
    }

    public void enableBackStackListener() {
        fragmentManager.addOnBackStackChangedListener(this);
    }

    @Override
    public void onBackStackChanged() {

        if (fragmentManager != null) {

            int countF = fragmentManager.getBackStackEntryCount();

            if (countF == 0) {
                onBackStackEmpty();
            }

        }
    }

    public void startActivityWithImageView(Intent intent, View view, String transitionName, boolean transition) {
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void startActivityWithImageView(Intent intent, View view, String transitionName) {
        startActivityWithImageView(intent, view, transitionName, true);
    }

    protected void onBackStackEmpty() {
    }

    protected void onBackPressFromEmptyStack() {
        super.onBackPressed();
    }

    private void checkPendingView() {

        pendingForOpen.clear();

        pendingForOpen = new ArrayList<>();

        for (FragmentView fragmentView : pendingForClose) {
            onClose(fragmentView);
        }

        pendingForClose.clear();

        pendingForClose = new ArrayList<>();

    }

    public void showFragmentDialog(DialogFragmentView fragment) {
        if (!isPause()) {
            FragmentManager fm = getSupportFragmentManager();
            fragment.show(fm, fragment.getName());
        } else {
            pendingDialogs.add(fragment);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}

