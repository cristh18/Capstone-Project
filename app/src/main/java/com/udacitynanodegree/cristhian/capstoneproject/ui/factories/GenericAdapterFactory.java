package com.udacitynanodegree.cristhian.capstoneproject.ui.factories;

import android.view.ViewGroup;

import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItemView;


public abstract class GenericAdapterFactory {

    public final static int CATEGORY = 100;

    public abstract GenericItemView onCreateViewHolder(ViewGroup parent, int viewType);

}