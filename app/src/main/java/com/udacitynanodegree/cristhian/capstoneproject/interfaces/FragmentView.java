package com.udacitynanodegree.cristhian.capstoneproject.interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.udacitynanodegree.cristhian.capstoneproject.R;


public abstract class FragmentView extends Fragment {

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

    public boolean isAddOnStack() {
        return addOnStack;
    }

    public boolean isAnimate() {
        return animate;
    }

    public int getContainer() {
        return container;
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