package com.mobinteg.library;

import android.content.Context;
import android.content.Intent;
import android.opengl.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

public class FullScreenViewPagerFragment extends Fragment {
    String urlStr;
    public int position;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();

        try {
            urlStr = getArguments().getString("url", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        View view = inflater.inflate(R.layout.fragment_fullscreen_viewpager, container, false);

        if(SimpleGallery.zoom) {
            final ImageViewTouch image = (ImageViewTouch) view.findViewById(R.id.image);
            image.setVisibility(View.VISIBLE);
            Glide.with(context).load(urlStr).into(image);
            image.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);

            image.setOnTouchListener(
                    new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {

                            if (image.getScale() > 1f) {
                                FullScreenViewPager.hideRecView();
                                return false;
                            } else if (image.getScale() < 1f) {
                                FullScreenViewPager.showRecView();
                                return false;
                            }

                            return false;
                        }
                    });

        }else{
            ImageView noZoomImage = (ImageView) view.findViewById(R.id.no_zoom_img);
            noZoomImage.setVisibility(View.VISIBLE);
            Glide.with(context).load(urlStr).into(noZoomImage);

            noZoomImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FullScreenViewPager.toogleRecView();
                }
            });
        }

        return view;
    }

    public static FullScreenViewPagerFragment newInstance (String url) {
        FullScreenViewPagerFragment frag = new FullScreenViewPagerFragment();
        Bundle b = new Bundle();
        b.putString("url", url);
        frag.setArguments(b);
        return frag;
    }

}