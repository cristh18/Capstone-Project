package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;

import com.udacitynanodegree.cristhian.capstoneproject.interfaces.DialogFragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentListener;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;

import java.util.ArrayList;


public class BaseFragmentActivity extends FragmentActivity implements FragmentListener, FragmentManager.OnBackStackChangedListener {


    public static final int ACTION_REQUEST_GALLERY = 101;

    public static final int CAPTURE_IMAGE_REQUEST_CODE = 102;

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

        setBackgroundActivity();

        fragmentManager = getSupportFragmentManager();

        progressDialog = new ProgressDialog(this);

        progressDialog.setIndeterminate(true);

        progressDialog.setCancelable(false);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    private void initFonts() {
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

        int countF = fragmentManager.getBackStackEntryCount();

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
}

