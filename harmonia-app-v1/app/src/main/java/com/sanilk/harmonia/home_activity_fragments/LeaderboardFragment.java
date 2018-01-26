package com.sanilk.harmonia.home_activity_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sanilk.harmonia.R;
import com.sanilk.harmonia.SpecificGenreFragment;
import com.sanilk.harmonia.TrendingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderboardFragment extends Fragment {


    public LeaderboardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_leaderboard, container, false);

        ((ImageView) getActivity().findViewById(R.id.home_bar_profile)).setColorFilter(null);
        ((ImageView) getActivity().findViewById(R.id.home_bar_saved)).setColorFilter(null);
        ((ImageView) getActivity().findViewById(R.id.home_bar_activity)).setColorFilter(null);

        ImageView imageView=(ImageView)getActivity().findViewById(R.id.home_bar_leaderboards);
        imageView.setColorFilter(getResources().getColor(R.color.lemonade_cherry_red));

        TrendingFragment trendingFragment=new TrendingFragment();

//        SpecificGenreFragment specificGenreFragment=new SpecificGenreFragment();

//        args.putString("GENRE_NAME", "");
//        specificGenreFragment.setArguments(args);

        FragmentTransaction ft=getChildFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_leaderboard_container1, trendingFragment);

        String[] genres=this.getResources().getStringArray(R.array.genres);
        int i=0;
        for(String genre:genres){
            i++;
            SpecificGenreFragment temp=new SpecificGenreFragment();
            Bundle args=new Bundle();
            args.putString("GENRE_NAME", genre);
            temp.setArguments(args);

            FrameLayout frameLayout=new FrameLayout(getActivity());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            frameLayout.setId(1234567+i);
            LinearLayout linearLayout=v.findViewById(R.id.fragment_leaderboard_main_container);
            linearLayout.addView(frameLayout);

            ft.replace(frameLayout.getId(), temp);
        }

        ft.commit();

        return v;
    }

}
