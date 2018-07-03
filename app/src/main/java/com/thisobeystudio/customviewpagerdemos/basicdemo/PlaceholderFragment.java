package com.thisobeystudio.customviewpagerdemos.basicdemo;

/*
 * Created by Endika Aguilera on 31/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thisobeystudio.customviewpager.models.CustomIndexHelper;
import com.thisobeystudio.customviewpagerdemos.R;

import static com.thisobeystudio.customviewpager.models.CustomFragment.ARG_CUSTOM_INDEX_HELPER;
import static com.thisobeystudio.customviewpagerdemos.demodata.DemoDataManager.getDarkerColor;

public class PlaceholderFragment extends Fragment {


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

        View rootView = inflater.inflate(R.layout.fragment_demo_basic, container, false);

        Bundle arguments = getArguments();

        // check for valid arguments
        if (arguments == null ||
                !arguments.containsKey(ARG_CUSTOM_INDEX_HELPER) ||
                !arguments.containsKey(ARG_DEMO_COLOR))
            return rootView;


        // set scrollableDemoCardView background color
        int color = arguments.getInt(ARG_DEMO_COLOR);
        CardView scrollableDemoCardView = rootView.findViewById(R.id.basicDemoCardView);
        scrollableDemoCardView.setCardBackgroundColor(color);

        TextView sectionPageIndexLabel = rootView.findViewById(R.id.sectionPageIndexLabel);
        TextView sectionDataIndexLabel = rootView.findViewById(R.id.sectionDataIndexLabel);

        // set root view background color
        int darkerColor = getDarkerColor(color);
        rootView.setBackgroundColor(darkerColor);

        CustomIndexHelper customIndexHelper = arguments.getParcelable(ARG_CUSTOM_INDEX_HELPER);
        if (customIndexHelper == null) return rootView;

        // set sectionPageIndexLabel text
        sectionPageIndexLabel.setText(String.valueOf(customIndexHelper.getPagerPosition()));

        // set sectionDataIndexLabel text and color
        sectionDataIndexLabel.setText(String.valueOf(customIndexHelper.getDataPosition()));
        sectionDataIndexLabel.setTextColor(darkerColor);

        return rootView;
    }

}
