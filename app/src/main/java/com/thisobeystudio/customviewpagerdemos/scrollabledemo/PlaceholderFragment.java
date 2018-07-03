package com.thisobeystudio.customviewpagerdemos.scrollabledemo;

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
import android.widget.TextView;

import com.thisobeystudio.customviewpager.models.CustomFragment;
import com.thisobeystudio.customviewpager.models.CustomIndexHelper;
import com.thisobeystudio.customviewpagerdemos.R;

import static com.thisobeystudio.customviewpagerdemos.demodata.DemoDataManager.getDarkerColor;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends CustomFragment
        implements NestedScrollView.OnScrollChangeListener {

    private ScrollableActivity mActivity = null;

    private NestedScrollView scrollableDemoScrollView;

    /**
     * The fragment argument representing the section for this fragment.
     */
    private static final String ARG_DEMO_COLOR = "demo_color";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     */
    public static PlaceholderFragment newInstance(CustomIndexHelper customIndexHelper,
                                                  int demoColor) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        // fixme this is important!! ARG_CUSTOM_INDEX_HELPER
        args.putParcelable(ARG_CUSTOM_INDEX_HELPER, customIndexHelper);
        args.putInt(ARG_DEMO_COLOR, demoColor);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_demo_scrollable, container, false);

        // make sure we are targeting the right host activity
        if (!(getActivity() instanceof ScrollableActivity)) return rootView;
        mActivity = ((ScrollableActivity) getActivity());
        if (mActivity == null) return rootView;

        Bundle arguments = getArguments();

        // check for valid arguments
        if (arguments == null ||
                !arguments.containsKey(ARG_CUSTOM_INDEX_HELPER) ||
                !arguments.containsKey(ARG_DEMO_COLOR))
            return rootView;

        // set scrollableDemoCardView background color
        int color = arguments.getInt(ARG_DEMO_COLOR);
        CardView scrollableDemoCardView = rootView.findViewById(R.id.scrollableDemoCardView);
        scrollableDemoCardView.setCardBackgroundColor(color);

        scrollableDemoScrollView = rootView.findViewById(R.id.scrollableDemoScrollView);
        TextView sectionPageIndexLabel = rootView.findViewById(R.id.sectionPageIndexLabel);
        TextView sectionDataIndexLabel = rootView.findViewById(R.id.sectionDataIndexLabel);

        // set root view background color
        int darkerColor = getDarkerColor(color);
        rootView.setBackgroundColor(darkerColor);

        // set sectionPageIndexLabel text
        sectionPageIndexLabel.setText(String.valueOf(getPageIndex()));

        // set sectionDataIndexLabel text and color
        sectionDataIndexLabel.setText(String.valueOf(getDataIndex()));
        sectionDataIndexLabel.setTextColor(darkerColor);

        // handle data if we are on first or last real pages
        if (isRealFirst() || isRealLast())
            scrollableDemoScrollView.setOnScrollChangeListener(this);

        // handle data if we are on first or last helper pages
        if (isHelperFirst() || isHelperLast()) updateHelpers(rootView);

        return rootView;
    }

    // handle scroll change to share data between real and helper pages
    @Override
    public void onScrollChange(NestedScrollView v,
                               int scrollX, int scrollY,
                               int oldScrollX, int oldScrollY) {
        if (mActivity == null) return;
        mActivity.setHelperPageData(isRealFirst(), isRealLast(), scrollY); // update page data
    }

    // this will be called if the helper fragment is available to update its data
    @Override
    public void setHelperPageData(@NonNull Object data) {
        int posY = (int) data;
        scrollableDemoScrollView.setScrollY(posY);
    }

    // use to update ui when needed
    private void updateHelpers(View rootView) {
        if (mActivity == null) return;

        final Object data = mActivity.getPageData(isHelperFirst(), isHelperLast());

        if (!(data instanceof Integer)) return;

        final int posY = (int) data;

        rootView.post(new Runnable() {
            @Override
            public void run() {
                scrollableDemoScrollView.setScrollY(posY);
            }
        });
    }
}