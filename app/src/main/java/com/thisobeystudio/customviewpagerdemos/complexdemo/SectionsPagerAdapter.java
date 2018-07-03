package com.thisobeystudio.customviewpagerdemos.complexdemo;

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

    private final ComplexDataHelper[] mComplexDemoData;

    SectionsPagerAdapter(FragmentManager fm, ComplexDataHelper[] complexDemoData) {
        super(fm);
        this.mComplexDemoData = complexDemoData;
    }

    @Override
    protected Fragment getItem(CustomIndexHelper customIndexHelper) {
        return PlaceholderFragment.
                newInstance(customIndexHelper, mComplexDemoData[customIndexHelper.getDataPosition()]);
    }

    @Override
    public int getRealCount() {
        if (mComplexDemoData == null) return 0;
        return mComplexDemoData.length;
    }
}