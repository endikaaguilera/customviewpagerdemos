package com.thisobeystudio.customviewpagerdemos.complexdemo;

/*
 * Created by Endika Aguilera on 31/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.thisobeystudio.customviewpager.viewpager.CustomViewPager;
import com.thisobeystudio.customviewpagerdemos.R;
import com.thisobeystudio.customviewpagerdemos.ZoomOutPageTransformer;

import static com.thisobeystudio.customviewpager.indicator.CustomIndicator.MODE_CLAMPED_HEIGHT;
import static com.thisobeystudio.customviewpager.indicator.CustomIndicator.POSITION_FLOAT_BOTTOM;

public class ComplexActivity extends AppCompatActivity {

    private final String TAG = ComplexActivity.class.getSimpleName();

    private CustomViewPager customViewPager;

    private final ComplexDataHelper[] complexDemoData = new ComplexDataHelper[]{
            new ComplexDataHelper(Color.parseColor("#F44336")),
            new ComplexDataHelper(Color.parseColor("#9C27B0")),
            new ComplexDataHelper(Color.parseColor("#3F51B5")),
            new ComplexDataHelper(Color.parseColor("#03A9F4")),
            new ComplexDataHelper(Color.parseColor("#009688")),
            new ComplexDataHelper(Color.parseColor("#8BC34A")),
            new ComplexDataHelper(Color.parseColor("#FFEB3B")),
            new ComplexDataHelper(Color.parseColor("#FF5722"))
    };

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
                new SectionsPagerAdapter(getSupportFragmentManager(), complexDemoData);

        // Find and set customViewPager.
        customViewPager = findViewById(R.id.customViewPager);

        // Set up the CustomViewPager with the sections adapter.
        customViewPager.setAdapter(sectionsPagerAdapter);

        // Indicators must be initialized before set the initial CustomViewPager current item.
        customViewPager.initIndicators(POSITION_FLOAT_BOTTOM, MODE_CLAMPED_HEIGHT);

        // Set initial selection
        customViewPager.setCurrentItem(0);

        customViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    // region helper methods to share scroll pos between real and helper pages

    void setHelperPageData(Boolean first, Boolean last, Object data) {
        customViewPager.setPageData(first, last, data);
    }

    Object getPageData(Boolean first, Boolean last) {
        return customViewPager.getPageData(first, last);
    }

}
