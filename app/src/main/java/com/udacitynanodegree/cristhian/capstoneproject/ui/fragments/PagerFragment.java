package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentPagerBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.model.AutoPart;
import com.udacitynanodegree.cristhian.capstoneproject.ui.adapters.PageAdapter;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.HeaderMainView;
import com.udacitynanodegree.cristhian.capstoneproject.utils.PartCategoryUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PagerFragment extends FragmentView implements HeaderMainView.HeaderMainListener {

    //    CategoryRepository categoryRepository = CategoryRepository.getCateRepoInstance();
    private FragmentPagerBinding pagerBinding;

    public static int NUM_PAGES;
    public ViewPager mPagerHandler;
    private PageAdapter pageAdapter;
    public static AutoPartFragment[] viewFragments;
    private static final String ARG_AUTOPARTS = "ARG_AUTOPARTS";
    private List<AutoPart> autoParts;
    private HashSet<String> categoryNames;

    public static PagerFragment newInstance(List<AutoPart> autoParts) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARG_AUTOPARTS, (ArrayList<? extends Parcelable>) autoParts);
        PagerFragment pagerFragment = new PagerFragment();
        pagerFragment.setArguments(bundle);
        return pagerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        autoParts = getArguments().getParcelableArrayList(ARG_AUTOPARTS);
        categoryNames = PartCategoryUtil.getCategories(autoParts);
        NUM_PAGES = categoryNames.size();
        viewFragments = new AutoPartFragment[NUM_PAGES];
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
        pageAdapter = new PageAdapter(getChildFragmentManager());
        initListeners();
    }

    private void initListeners() {
        pagerBinding.headerMainViewAutoPartCategories.setHeaderMainListener(this);
    }

    private void buildView() {
        pagerBinding.pagerHeader.setDrawFullUnderline(true);
        pagerBinding.pagerHeader.setTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.blue_primary));
        pagerBinding.pagerHeader.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_secondary));
        pagerBinding.pagerHeader.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

        for (int i = 0; i < NUM_PAGES; i++) {
            viewFragments[i] = new AutoPartFragment();
            viewFragments[i].setFragmentAutoPart(String.valueOf(i));
            viewFragments[i].setAutoParts(PartCategoryUtil.getAutoPartsByCategory(new ArrayList<>(categoryNames).get(i), autoParts));
        }

        pageAdapter.setCategoryNames(new ArrayList<>(categoryNames));

        pagerBinding.pager.setAdapter(pageAdapter);
        pagerBinding.pager.setCurrentItem(0);
    }


    @Override
    public void onClickBackHeader() {
        close();
    }

    @Override
    public void onClickRightHeader() {

    }
}
