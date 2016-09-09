package com.mobinteg.library;


import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class SimpleGallery {
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
}
