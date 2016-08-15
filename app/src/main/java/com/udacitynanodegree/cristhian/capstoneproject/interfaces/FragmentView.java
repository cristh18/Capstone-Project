package com.udacitynanodegree.cristhian.capstoneproject.interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.udacitynanodegree.cristhian.capstoneproject.R;


public abstract class FragmentView extends Fragment {

    private int enter = R.anim.slide_in_right;

    private int exit = R.anim.slide_out_right;

    private int popEnter = R.anim.slide_in_right;

    private int popExit = R.anim.slide_out_right;

    private boolean addOnStack = false;

    private boolean animate = false;

    private int container = R.id.container;

    protected FragmentListener fragmentListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setListener(FragmentListener fragmentListener) {
        this.fragmentListener = fragmentListener;
    }

    public abstract String getName();

    public boolean canBack() {
        return true;
    }

    public void backPressed() {
    }

    public boolean hideGridButton() {
        return false;
    }

    public boolean hideCheckoutButton() {
        return false;
    }

    public boolean hideBasket() {
        return false;
    }

    public boolean isShowWeightBasket() {
        return false;
    }

    public boolean closeMenu() {
        return true;
    }

    public void close() {
        fragmentListener.onClose(this);
    }

    public int getEnter() {
        return enter;
    }

    public int getExit() {
        return exit;
    }

    public int getPopEnter() {
        return popEnter;
    }

    public int getPopExit() {
        return popExit;
    }

    public boolean isAddOnStack() {
        return addOnStack;
    }

    public boolean isAnimate() {
        return animate;
    }

    public int getContainer() {
        return container;
    }

    public FragmentView setEnter(int enter) {
        this.enter = enter;
        return this;
    }

    public FragmentView setExit(int exit) {
        this.exit = exit;
        return this;
    }

    public FragmentView setPopEnter(int popEnter) {
        this.popEnter = popEnter;
        return this;
    }

    public FragmentView setPopExit(int popExit) {
        this.popExit = popExit;
        return this;
    }

    public FragmentView addOnStack() {
        this.addOnStack = true;
        return this;
    }

    public FragmentView animate() {
        this.animate = true;
        return this;
    }

    public FragmentView id(int container) {
        this.container = container;
        return this;
    }


}