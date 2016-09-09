package com.mobinteg.driodgallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mobinteg.library.FullScreenViewPager;
import com.mobinteg.library.SimpleGallery;

import java.util.ArrayList;
import java.util.Random;

public class SampleActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        context = this;

        final ArrayList<String> imgArray = new ArrayList<String>();

        for (int i = 0; i < 50; i++) {
            Random rand = new Random();
            int random = rand.nextInt(856) + 1;
            imgArray.add("https://unsplash.it/350/350/?image=" + random);
        }

        SimpleGallery.start(context, imgArray);

        TextView gallery = (TextView) findViewById(R.id.gallery);

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleGallery.start(context, imgArray);
            }
        });
    }
}
