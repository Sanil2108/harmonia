package com.sanilk.harmonia.home_activity_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sanilk.harmonia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment {


    public PlaylistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_leaderboard, container, false);

        ((ImageView) getActivity().findViewById(R.id.home_bar_profile)).setColorFilter(null);
        ((ImageView) getActivity().findViewById(R.id.home_bar_activity)).setColorFilter(null);
        ((ImageView) getActivity().findViewById(R.id.home_bar_leaderboards)).setColorFilter(null);

        ImageView imageView=(ImageView)getActivity().findViewById(R.id.home_bar_saved);
        imageView.setColorFilter(getResources().getColor(R.color.lemonade_cherry_red));
        return v;
    }

}
