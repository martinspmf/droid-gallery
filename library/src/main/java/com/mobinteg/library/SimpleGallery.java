package com.mobinteg.library;


import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class SimpleGallery {

    public static boolean zoom = true;
    public static boolean parallax = true;

    /**
     *
     * @param context (context - Context you are in)
     * @param imgArray (imgArray - ArrayList<String> of strings URLs)
     * @param pos      (pos - List position, default will be 0)
     */
    public static void startWithPosition(Context context, ArrayList<String> imgArray, int pos){

        Intent intent = new Intent(context, FullScreenViewPager.class);
        intent.putStringArrayListExtra("array", imgArray);
        intent.putExtra("position", pos);
        context.startActivity(intent);
    }
    /**
     *
     * @param context (context - Context you are in)
     * @param imgArray (imgArray - ArrayList<String> of strings URLs)
     */
    public static void start(Context context, ArrayList<String> imgArray){

        Intent intent = new Intent(context, FullScreenViewPager.class);
        intent.putStringArrayListExtra("array", imgArray);
        intent.putExtra("position", 0);
        context.startActivity(intent);
    }

    /**
     *
     * @param zoom (if true, zoom will be enable on the view - default true)
     */
    public void enableZoom(boolean zoom) {
        this.zoom = zoom;
    }

    /**
     *
     * @param parallax (if true, parallax animation will be enable on the view - default true)
     */
    public void enableParallax(boolean parallax) {
        this.parallax = parallax;
    }
}
