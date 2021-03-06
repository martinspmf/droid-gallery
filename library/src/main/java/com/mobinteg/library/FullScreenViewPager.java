package com.mobinteg.library;


import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.xgc1986.parallaxPagerTransformer.ParallaxPagerTransformer;

import java.util.ArrayList;

public class FullScreenViewPager extends AppCompatActivity {

    static Context context;
    public static ViewPager viewPager;
    SlowerLinearLayoutManager llm;
    ArrayList<String> dataObjs = new ArrayList<String>();
    ArrayList<String> descriptionObjs = new ArrayList<String>();
    TextView closeBtn;
    private int position;
    private int count;
    public static RecyclerView myList;
    public static Animation slideUpIn, slideDownOut, slideDownIn, slideUpOut;
    RecyclerViewAdapter recAdapter;
    int width;
    public static Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     //   getWindow().requestFeature(Window.FEATURE_NO_TITLE);
     //   if(getActionBar()!=null)
    //        getActionBar().hide();

        setContentView(R.layout.activity_full_screen_view_pager);

        context = this;

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.black)); // i don't tested this method. Write if it's not working
        toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white));
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        slideUpIn = AnimationUtils.loadAnimation(this, R.anim.slide_up_in);
        slideDownOut = AnimationUtils.loadAnimation(this, R.anim.slide_down_out);
        slideUpOut = AnimationUtils.loadAnimation(this, R.anim.slide_up_out);
        slideDownIn = AnimationUtils.loadAnimation(this, R.anim.slide_down_in);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dataObjs = extras.getStringArrayList("array");
            if(extras.getStringArrayList("descriptionArray")!=null) {
                descriptionObjs = extras.getStringArrayList("descriptionArray");
            }
            position = extras.getInt("position", 0);
        }


        viewPager = (ViewPager) findViewById(R.id.infinite_pager);
        if (SimpleGallery.parallax) {
            viewPager.setPageTransformer(true, new ParallaxPageTransformer(R.id.image));
        }

        FullScreenAdapter adapter = new FullScreenAdapter(getSupportFragmentManager(), dataObjs);

        viewPager.setAdapter(adapter);
        count = 1;
        viewPager.setCurrentItem(position);


        viewPager.addOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int position) {

            //    myList.smoothScrollToPosition(position);
           //     System.out.println("onPageScrollStateChanged: " + position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

                FullScreenViewPager.showRecView();

            }

            @Override
            public void onPageSelected(int position) {

                System.out.println("onPageSetected: " + width);
                myList.smoothScrollToPosition(position);

            }
        });


        llm = new SlowerLinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        myList = (RecyclerView) findViewById(R.id.recycler_view);
        myList.setLayoutManager(llm);

        if(SimpleGallery.showList){
            myList.setVisibility(View.VISIBLE);
        }else{
            ViewGroup.LayoutParams params = myList.getLayoutParams();
            params.height = 0;
            myList.setLayoutParams(params);
            myList.requestLayout();
            myList.setVisibility(View.GONE);
        }

        recAdapter = new RecyclerViewAdapter(context, dataObjs);
        myList.setAdapter(recAdapter);
        myList.smoothScrollToPosition(position);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }




    public class FullScreenAdapter extends FragmentPagerAdapter {

        ArrayList<String> dataObjs = new ArrayList<String>();
        private int mCount;
        FragmentManager fm;

        public FullScreenAdapter(FragmentManager fm, ArrayList<String> dataObjs) {
            super(fm);
            this.dataObjs = dataObjs;
            mCount = dataObjs.size();
            System.out.println("COUNT 1: " + mCount);
            this.fm = fm;
        }

        @Override
        public Fragment getItem(int position) {
            if(descriptionObjs.size()>0){
                return FullScreenViewPagerFragment.newInstanceWithDescription(dataObjs.get(position), descriptionObjs.get(position));
            } else {
                return FullScreenViewPagerFragment.newInstance(dataObjs.get(position));
            }
        }

        @Override
        public int getCount() {
            return mCount;
        }

        @Override
        public float getPageWidth(int position) {
            return 1.0f;
        }
    }

    public static void toogleRecView() {

        if (myList.getVisibility() == View.VISIBLE) {
            myList.startAnimation(slideDownOut);
            toolbar.startAnimation(slideUpOut);
            toolbar.setVisibility(View.GONE);
            myList.setVisibility(View.GONE);
        } else {
            myList.startAnimation(slideUpIn);
            myList.setVisibility(View.VISIBLE);
            toolbar.startAnimation(slideDownIn);
            toolbar.setVisibility(View.VISIBLE);
        }

    }

    public static void hideRecView() {
        if (myList.getVisibility() == View.VISIBLE) {
            myList.startAnimation(slideDownOut);
            toolbar.startAnimation(slideUpOut);
            toolbar.setVisibility(View.GONE);
            myList.setVisibility(View.GONE);
        }
    }

    public static void showRecView() {
        if (myList.getVisibility() == View.GONE) {
            myList.startAnimation(slideUpIn);
            myList.setVisibility(View.VISIBLE);
            toolbar.startAnimation(slideDownIn);
            toolbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return super.onOptionsItemSelected(item);
            default:
                finish();
                return super.onOptionsItemSelected(item);
        }
    }

    public class ParallaxPageTransformer implements ViewPager.PageTransformer {
        private int id;
        private int border = 0;
        private float speed = 0.2f;

        public ParallaxPageTransformer(int id) {
            this.id = id;
        }

        @Override
        public void transformPage(View view, float position) {
            View parallaxView = view.findViewById(id);
            if (parallaxView != null) {
                if (position > -1 && position < 1) {
                    float width = parallaxView.getWidth();
                    parallaxView.setTranslationX(-(position * width * speed));
                    float sc = ((float) view.getWidth() - border) / view.getWidth();
                    if (position == 0) {
                        view.setScaleX(1);
                        view.setScaleY(1);
                    } else {
                        view.setScaleX(sc);
                        view.setScaleY(sc);
                    }
                }
            }
        }

        public void setBorder(int px) {
            border = px;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }
    }
}