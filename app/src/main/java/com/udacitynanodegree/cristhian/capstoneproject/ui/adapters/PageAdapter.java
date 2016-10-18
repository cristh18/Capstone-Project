package com.udacitynanodegree.cristhian.capstoneproject.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.PagerFragment;

import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {

    private List<String> categoryNames;

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return PagerFragment.viewFragments[i];
    }

    @Override
    public int getCount() {
        return PagerFragment.NUM_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getNamePage(position);
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    private String getNamePage(int category) {

        for (int i = 0; i < PagerFragment.viewFragments.length; i++) {
            if (i == category) {
                return getCategoryNames().get(category);
            }
        }
        return null;
    }


}
