package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentPagerBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.ui.adapters.PageAdapter;

public class PagerFragment extends FragmentView {

//    CategoryRepository categoryRepository = CategoryRepository.getCateRepoInstance();
    private FragmentPagerBinding pagerBinding;

    public static int NUM_PAGES;
    public ViewPager mPagerHandler;
    private PageAdapter pageAdapter;
    public static CategoryFragment[] viewFragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int numberCategories = 6;
        NUM_PAGES = numberCategories;
        viewFragments = new CategoryFragment[numberCategories];
    }

    @Override
    public String getName() {
        return PagerFragment.class.getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        pagerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pager, container, false);
        init();
        buildView();
        return pagerBinding.getRoot();
    }

    private void init() {
        pageAdapter = new PageAdapter(getChildFragmentManager(), getActivity());
    }

    private void buildView(){
        pagerBinding.pagerHeader.setDrawFullUnderline(true);
        pagerBinding.pagerHeader.setTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.blue_primary));
        pagerBinding.pagerHeader.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_secondary));
        pagerBinding.pagerHeader.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

        for (int i = 0; i < NUM_PAGES; i++) {
            viewFragments[i] = new CategoryFragment();
//            viewFragments[i].setFragmentCategory(String.valueOf(i));
        }
        pagerBinding.pager.setAdapter(pageAdapter);
        pagerBinding.pager.setCurrentItem(0);
    }


}
