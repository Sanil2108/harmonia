package com.sanilk.harmonia;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sanilk.harmonia.PlaylistViewFragment;
import com.sanilk.harmonia.R;


public class TrendingFragment extends Fragment {

    public TrendingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_trending, container, false);
//        FrameLayout container1=(FrameLayout)view.findViewById(R.id.fragment_trending_container1);
        PlaylistViewFragment playlistViewFragment=new PlaylistViewFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_trending_container1, playlistViewFragment).commit();



        return view;
    }

}
