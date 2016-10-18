package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentAutopartBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.ui.adapters.GenericAdapter;

public class AutoPartFragment extends FragmentView {

    private FragmentAutopartBinding autopartBinding;
    private String[] fragmentAutoPart = new String[1];
    private GenericAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        autopartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_autopart, container, false);
        return autopartBinding.getRoot();
    }

    @Override
    public String getName() {
        return AutoPartFragment.class.getName();
    }


    public void setFragmentAutoPart(String category) {
        fragmentAutoPart[0] = category;
    }

}
