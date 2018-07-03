package com.thisobeystudio.customviewpagerdemos.basicdemo;

/*
 * Created by Endika Aguilera on 31/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.thisobeystudio.customviewpager.viewpager.CustomViewPager;
import com.thisobeystudio.customviewpagerdemos.R;

import static com.thisobeystudio.customviewpagerdemos.demodata.DemoDataManager.demoColors;

public class IndicatorsActivity extends AppCompatActivity {

    private static final String TAG = IndicatorsActivity.class.getSimpleName();

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

        CustomViewPager customViewPager = findViewById(R.id.customViewPager);

        // Set up the CustomViewPager with the sections adapter.
        customViewPager.setAdapter(sectionsPagerAdapter);

        // Init pager indicators
        // default -> (POSITION_FLOAT_BOTTOM, MODE_CLAMPED_HEIGHT, 1)
        customViewPager.initIndicators();
//        custom_view_pager initIndicators(position: Int, adjustMode: Int)
//        custom_view_pager initIndicators(position: Int, adjustMode: Int, maxRows: Int)

        // Set the initial pager selection
        customViewPager.setCurrentItem(0);
    }

}
