package com.sanilk.harmonia.home_activity_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sanilk.harmonia.CreateBrowse;
import com.sanilk.harmonia.GenreFragment;
import com.sanilk.harmonia.NotificationsFragment;
import com.sanilk.harmonia.R;
import com.sanilk.harmonia.TrendingFragment;
import com.sanilk.harmonia.TrendingTagsFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((ImageView) getActivity().findViewById(R.id.home_bar_saved)).setColorFilter(null);
        ((ImageView) getActivity().findViewById(R.id.home_bar_profile)).setColorFilter(null);
        ((ImageView) getActivity().findViewById(R.id.home_bar_activity)).setColorFilter(null);
        ((ImageView) getActivity().findViewById(R.id.home_bar_leaderboards)).setColorFilter(null);

        FragmentTransaction ft=getChildFragmentManager().beginTransaction();
        TrendingFragment trendingFragment=new TrendingFragment();
        CreateBrowse createBrowseFragment=new CreateBrowse();
        GenreFragment genreFragment=new GenreFragment();
        NotificationsFragment notificationsFragment=new NotificationsFragment();
        TrendingTagsFragment trendingTagsFragment=new TrendingTagsFragment();
        ft.replace(R.id.fragment_home_container1, trendingFragment);
        ft.replace(R.id.fragment_home_container2, createBrowseFragment);
        ft.replace(R.id.fragment_home_container3, genreFragment);
        ft.replace(R.id.fragment_home_container4, notificationsFragment);
        ft.replace(R.id.fragment_home_container5, trendingTagsFragment);
        ft.commit();

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
