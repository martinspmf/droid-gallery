package com.mobinteg.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

public class FullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        Bundle b = getIntent().getExtras();
        String url = b.getString("url", "");

        ImageViewTouch img = (ImageViewTouch) findViewById(R.id.fullscreen_image);
        Glide.with(this).load(url).into(img);
        img.setDisplayType(ImageViewTouchBase.DisplayType.FIT_IF_BIGGER);
    }
}
