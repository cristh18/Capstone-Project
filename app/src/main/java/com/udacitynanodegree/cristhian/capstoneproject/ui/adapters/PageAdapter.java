package com.udacitynanodegree.cristhian.capstoneproject.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.PagerFragment;
import com.udacitynanodegree.cristhian.capstoneproject.utils.VehicleUtil;

public class PageAdapter extends FragmentStatePagerAdapter {

    Context context;

    public PageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
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
        return getNamePage(context, position);
    }

    private String getNamePage(Context context, int category) {

        for (int i = 0; i < PagerFragment.viewFragments.length; i++) {
            if (i == category) {
                return VehicleUtil.searchCategoryName(context, category);
            }
        }
        return null;
    }


}
