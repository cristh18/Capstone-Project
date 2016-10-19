package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentAutopartBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItemView;
import com.udacitynanodegree.cristhian.capstoneproject.model.AutoPart;
import com.udacitynanodegree.cristhian.capstoneproject.ui.adapters.GenericAdapter;
import com.udacitynanodegree.cristhian.capstoneproject.ui.factories.GenericAdapterFactory;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.SimpleDividerItemDecoration;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.items.AutoPartItemView;

import java.util.List;

public class AutoPartFragment extends FragmentView {

    private FragmentAutopartBinding autopartBinding;
    private String[] fragmentAutoPart = new String[1];
    private GenericAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        autopartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_autopart, container, false);
        init();
        buildView();
        return autopartBinding.getRoot();
    }

    private void init() {
        initRecyclerViewAdapter();
    }

    private void initRecyclerViewAdapter() {
        adapter = new GenericAdapter(new GenericAdapterFactory() {
            @Override
            public GenericItemView onCreateViewHolder(ViewGroup parent, int viewType) {
                return new AutoPartItemView(parent.getContext());
            }
        });
    }

    private void buildView() {
        autopartBinding.recyclerViewAutoParts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        autopartBinding.recyclerViewAutoParts.setVerticalScrollBarEnabled(true);
        autopartBinding.recyclerViewAutoParts.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        autopartBinding.recyclerViewAutoParts.setHasFixedSize(true);
        autopartBinding.recyclerViewAutoParts.setVerticalScrollBarEnabled(true);
        autopartBinding.recyclerViewAutoParts.setAdapter(adapter);
//        adapter.setItems(getAutoPartItems(stockVehicles));
    }

    @Override
    public String getName() {
        return AutoPartFragment.class.getName();
    }


    public void setFragmentAutoPart(String category) {
        fragmentAutoPart[0] = category;
    }

    private List<? extends GenericItem> getAutoPartItems(List<AutoPart> items) {
        List<? extends GenericItem> vehicleItems = items;
        return vehicleItems;
    }

}
