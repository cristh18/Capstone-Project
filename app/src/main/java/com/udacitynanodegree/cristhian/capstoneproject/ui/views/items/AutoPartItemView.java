package com.udacitynanodegree.cristhian.capstoneproject.ui.views.items;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.ItemAutoPartBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItemView;
import com.udacitynanodegree.cristhian.capstoneproject.model.AutoPart;
import com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.autopart.AutoPartItemViewModel;

public class AutoPartItemView extends LinearLayout implements GenericItemView, AutoPartItemViewModel.AutoPartListener {

    private ItemAutoPartBinding autoPartBinding;
    private Context context;

    public AutoPartItemView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public AutoPartItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public AutoPartItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        autoPartBinding = DataBindingUtil.inflate(inflater, R.layout.item_auto_part, this, true);
        setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void bind(GenericItem item) {
        AutoPart autoPart = (AutoPart) item;
        setViewModel(autoPart);
    }

    private void setViewModel(AutoPart autoPart) {
        AutoPartItemViewModel autoPartItemViewModel = new AutoPartItemViewModel(autoPart);
        autoPartItemViewModel.setAutoPartListener(this);
        autoPartBinding.setViewModel(autoPartItemViewModel);
    }

    @Override
    public void onAutoPartSelected(AutoPart autoPart) {

    }
}