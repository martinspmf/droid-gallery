package com.mobinteg.library;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

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
        ImageView image = (ImageView) view.findViewById(R.id.image);
        Picasso.with(context).load(urlStr).into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FullScreenViewPager.toogleRecView();
            }
        });

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