package com.thisobeystudio.customviewpagerdemos.scrollabledemo;

/*
 * Created by Endika Aguilera on 31/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.thisobeystudio.customviewpager.viewpager.CustomViewPager;
import com.thisobeystudio.customviewpagerdemos.R;

import static com.thisobeystudio.customviewpager.indicator.CustomIndicator.MODE_CLAMPED_HEIGHT;
import static com.thisobeystudio.customviewpager.indicator.CustomIndicator.POSITION_FLOAT_BOTTOM;
import static com.thisobeystudio.customviewpagerdemos.demodata.DemoDataManager.demoColors;

public class ScrollableActivity extends AppCompatActivity {

    private final String TAG = ScrollableActivity.class.getSimpleName();

    private CustomViewPager customViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demos);

        setTitle(TAG);

        initCustomViewPager();
    }

    private void initCustomViewPager() {
        // Create the adapter that will return a fragment for each of the
        // primary sections of the activity.
        SectionsPagerAdapter sectionsPagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager(), demoColors());

        // Find and set customViewPager.
        customViewPager = findViewById(R.id.customViewPager);

        // Set up the CustomViewPager with the sections adapter.
        customViewPager.setAdapter(sectionsPagerAdapter);

        // Indicators must be initialized before set the initial CustomViewPager current item.
        customViewPager.initIndicators(POSITION_FLOAT_BOTTOM, MODE_CLAMPED_HEIGHT);

        // Set initial selection
        customViewPager.setCurrentItem(0);
    }

    // region helper methods to share scroll pos between real and helper pages

    void setHelperPageData(Boolean first, Boolean last, Object data) {
        customViewPager.setPageData(first, last, data);
    }

    Object getPageData(Boolean first, Boolean last) {
        return customViewPager.getPageData(first, last);
    }

    // endregion helper methods to share scroll pos between real and helper pages

}
