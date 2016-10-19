package com.udacitynanodegree.cristhian.capstoneproject.ui.views.items;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItemView;

public class AutoPartItemView extends LinearLayout implements GenericItemView {

    public AutoPartItemView(Context context) {
        super(context);
    }

    public AutoPartItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoPartItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void bind(GenericItem item) {

    }
}