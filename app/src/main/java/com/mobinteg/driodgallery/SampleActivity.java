package com.mobinteg.driodgallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.mobinteg.library.FullScreenViewPager;
import com.mobinteg.library.SimpleGallery;

import java.util.ArrayList;
import java.util.Random;

public class SampleActivity extends AppCompatActivity {

    Context context;
    private FadingCircle doubleBounce;
    private CountDownTimer timer;
    private int max = -1;
    private int min = 50200;
    private boolean descending = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //        getActionBar().hide();

        setContentView(R.layout.activity_sample);


        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        doubleBounce = new FadingCircle();
        doubleBounce.setColor(Color.parseColor("#0099ff"));
        progressBar.setIndeterminateDrawable(doubleBounce);

        context = this;

        final ArrayList<String> imgArray = new ArrayList<>();
        final ArrayList<String> descArray = new ArrayList<>();

        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int random = rand.nextInt(856) + 1;

            int width = (r.nextInt(500) + 300);
            int height = (r.nextInt(500) + 300);

            imgArray.add("http://lorempixel.com/" + width + "/" + height + "/?random=" + random);
            descArray.add("wsdjfhsa");
            //  imgArray.add("https://unsplash.it/" + width + "/" + height + "/?image=" + random);
        }

        SimpleGallery gallery = new SimpleGallery();
        gallery.enableZoom(true);
        gallery.enableParallax(true);
        gallery.showBottomList(true);
        gallery.startWithDescription(context, imgArray, descArray);

        final TextView galleryText = (TextView) findViewById(R.id.gallery);
        final TextView galleryText1 = (TextView) findViewById(R.id.gallery1);

        galleryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleGallery.start(context, imgArray);
            }
        });


        //galleryText.setText("" + MyUtils.dpToPx(context, 300));
        //galleryText1.setText("" + MyUtils.convertDate("2017/05/02", "yyyy/MM/dd", "dd de MMMM de yyyy", true));


        timer = new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {


            }

            @Override
            public void onFinish() {
                try{
                    timer.start();
                }catch(Exception e){
                    Log.e("Error", "Error: " + e.toString());
                }
            }
        }.start();


        timer.start();



    }


}



