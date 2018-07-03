package com.thisobeystudio.customviewpagerdemos.demodata;

/*
 * Created by Endika Aguilera on 31/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.graphics.Color;

public class DemoDataManager {

    public static int[] demoColors() {
        return new int[]{
                Color.parseColor("#F44336"),
                Color.parseColor("#9C27B0"),
                Color.parseColor("#3F51B5"),
                Color.parseColor("#03A9F4"),
                Color.parseColor("#009688"),
                Color.parseColor("#8BC34A"),
                Color.parseColor("#FFEB3B"),
                Color.parseColor("#FF5722")};
    }

    public static int getDarkerColor(int baseColor) {
        float[] hsv = new float[3];
        int color = baseColor;
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.6f;
        color = Color.HSVToColor(hsv);
        return color;
    }
}
