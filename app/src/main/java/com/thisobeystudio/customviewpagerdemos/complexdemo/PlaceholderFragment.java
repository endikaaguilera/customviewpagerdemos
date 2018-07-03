package com.thisobeystudio.customviewpagerdemos.complexdemo;

/*
 * Created by Endika Aguilera on 31/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.thisobeystudio.customviewpager.models.CustomFragment;
import com.thisobeystudio.customviewpager.models.CustomIndexHelper;
import com.thisobeystudio.customviewpagerdemos.R;

import static com.thisobeystudio.customviewpagerdemos.demodata.DemoDataManager.getDarkerColor;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends CustomFragment
        implements NestedScrollView.OnScrollChangeListener,
        RatingBar.OnRatingBarChangeListener {

    private ComplexActivity mActivity = null;

    private NestedScrollView mComplexDemoScrollView;
    private RatingBar mRatingBar;

    private ComplexDataHelper mComplexDataHelper;

    /**
     * The fragment argument representing the section for this fragment.
     */
    private static final String ARG_COMPLEX_DEMO_DATA = "demo_color";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     */
    public static PlaceholderFragment newInstance(CustomIndexHelper customIndexHelper,
                                                  ComplexDataHelper complexDataHelper) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        // fixme this is important!! ARG_CUSTOM_INDEX_HELPER
        args.putParcelable(ARG_CUSTOM_INDEX_HELPER, customIndexHelper);
        args.putParcelable(ARG_COMPLEX_DEMO_DATA, complexDataHelper);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_demo_complex, container, false);

        // make sure we are targeting the right host activity
        if (!(getActivity() instanceof ComplexActivity)) return rootView;
        mActivity = ((ComplexActivity) getActivity());
        if (mActivity == null) return rootView;

        Bundle arguments = getArguments();

        // check for valid arguments
        if (arguments == null ||
                !arguments.containsKey(ARG_CUSTOM_INDEX_HELPER) ||
                !arguments.containsKey(ARG_COMPLEX_DEMO_DATA))
            return rootView;

        // set scrollableDemoCardView background color
        mComplexDataHelper = arguments.getParcelable(ARG_COMPLEX_DEMO_DATA);
        if (mComplexDataHelper == null) return rootView;

        CardView scrollableDemoCardView = rootView.findViewById(R.id.complexDemoCardView);
        scrollableDemoCardView.setCardBackgroundColor(mComplexDataHelper.getColor());

        TextView sectionPageIndexLabel = rootView.findViewById(R.id.sectionPageIndexLabel);
        TextView sectionDataIndexLabel = rootView.findViewById(R.id.sectionDataIndexLabel);

        // set root view background color
        int darkerColor = getDarkerColor(mComplexDataHelper.getColor());
        rootView.setBackgroundColor(darkerColor);

        // set sectionPageIndexLabel text
        sectionPageIndexLabel.setText(String.valueOf(getPageIndex()));

        // set sectionDataIndexLabel text and color
        sectionDataIndexLabel.setText(String.valueOf(getDataIndex()));
        sectionDataIndexLabel.setTextColor(darkerColor);

        mComplexDemoScrollView = rootView.findViewById(R.id.complexDemoScrollView);
        mRatingBar = rootView.findViewById(R.id.ratingBar);

        // handle data if we are on first or last real pages
        if (isRealFirst() || isRealLast()) {
            mComplexDemoScrollView.setOnScrollChangeListener(this);
            mRatingBar.setOnRatingBarChangeListener(this);
        }

        // handle data if we are on first or last helper pages
        if (isHelperFirst() || isHelperLast()) updateHelpers(rootView);

        return rootView;
    }

    // handle rating change to share data between real and helper pages
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if (mActivity == null) return;
        mComplexDataHelper.setRating(rating);
        mActivity.setHelperPageData(isRealFirst(), isRealLast(), mComplexDataHelper); // update page data
    }

    // handle scroll change to share data between real and helper pages
    @Override
    public void onScrollChange(NestedScrollView v,
                               int scrollX, int scrollY,
                               int oldScrollX, int oldScrollY) {
        if (mActivity == null) return;
        mComplexDataHelper.setPosY(scrollY);
        mActivity.setHelperPageData(isRealFirst(), isRealLast(), mComplexDataHelper); // update page data
    }

    // this will be called if the helper fragment is available to update its data
    @Override
    public void setHelperPageData(@NonNull Object data) {
        mComplexDataHelper = (ComplexDataHelper) data;
        mComplexDemoScrollView.setScrollY(mComplexDataHelper.getPosY());
        mRatingBar.setRating(mComplexDataHelper.getRating());
    }

    // use to update ui when needed
    private void updateHelpers(View rootView) {

        if (mActivity == null) return;

        final Object data = mActivity.getPageData(isHelperFirst(), isHelperLast());

        if (!(data instanceof ComplexDataHelper)) return;

        mComplexDataHelper = (ComplexDataHelper) data;

        rootView.post(new Runnable() {
            @Override
            public void run() {
                mComplexDemoScrollView.setScrollY(mComplexDataHelper.getPosY());
                mRatingBar.setRating(mComplexDataHelper.getRating());
            }
        });
    }
}