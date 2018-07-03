package com.thisobeystudio.customviewpagerdemos.scrollabledemo;

/*
 * Created by Endika Aguilera on 31/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.thisobeystudio.customviewpager.models.CustomIndexHelper;
import com.thisobeystudio.customviewpager.viewpager.CustomPagerAdapter;

class SectionsPagerAdapter extends CustomPagerAdapter {

    private final int[] mDemoColors;

    SectionsPagerAdapter(FragmentManager fm, int[] demoColors) {
        super(fm);
        this.mDemoColors = demoColors;
    }

    @Override
    protected Fragment getItem(CustomIndexHelper customIndexHelper) {
        return PlaceholderFragment.
                newInstance(customIndexHelper, mDemoColors[customIndexHelper.getDataPosition()]);
    }

    @Override
    public int getRealCount() {
        if (mDemoColors == null) return 0;
        return mDemoColors.length;
    }
}