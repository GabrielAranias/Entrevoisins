package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    private final static Fragment[] mFragments = { NeighbourFragment.newInstance(false), NeighbourFragment.newInstance(true) };

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return mFragments.length;
    }
}