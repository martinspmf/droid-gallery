package com.mobinteg.driodgallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
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

//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
 //        getActionBar().hide();

        setContentView(R.layout.activity_sample);

        context = this;

        final ArrayList<String> imgArray = new ArrayList<>();

        Random r = new Random();

        for (int i = 0; i < 50; i++) {
            Random rand = new Random();
            int random = rand.nextInt(856) + 1;

            int width = (r.nextInt(500) + 300);
            int height = (r.nextInt(500) + 300);

            imgArray.add("https://unsplash.it/" + width + "/" + height + "/?image=" + random);
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
