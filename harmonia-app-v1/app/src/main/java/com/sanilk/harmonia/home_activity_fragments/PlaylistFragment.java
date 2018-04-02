package com.sanilk.harmonia.home_activity_fragments;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.sanilk.harmonia.R;
import com.sanilk.harmonia.entities.Song;

import java.io.File;

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
        View v=inflater.inflate(R.layout.fragment_playlist, container, false);

        ((ImageView) getActivity().findViewById(R.id.home_bar_profile)).setColorFilter(null);
        ((ImageView) getActivity().findViewById(R.id.home_bar_activity)).setColorFilter(null);
        ((ImageView) getActivity().findViewById(R.id.home_bar_leaderboards)).setColorFilter(null);

        ImageView imageView=(ImageView)getActivity().findViewById(R.id.home_bar_saved);
        imageView.setColorFilter(getResources().getColor(R.color.lemonade_cherry_red));

        ListView listView=(ListView)v.findViewById(R.id.fragment_playlist_list);

        File tempFile;
        

        Song[] songs=new Song[]{
//                new Song("Hawayein", "cYOB941gyXI", 100, 100, );
        };
        MyListAdapter myListAdapter=new MyListAdapter(this.getContext(), songs);
        listView.setAdapter(myListAdapter);
        listView.setOnItemClickListener(myListAdapter);

        return v;
    }

    private static class MyListAdapter extends ArrayAdapter<Song> implements AdapterView.OnItemClickListener{
        Song[] songs;
        Context context;

        public MyListAdapter(@NonNull Context context, @NonNull Song[] objects) {
            super(context, R.layout.fragment_playlist_row, objects);
            this.context=context;
            this.songs=songs;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            view.setBackgroundColor(Color.parseColor("#000000"));
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater  layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.fragment_playlist_row, parent, false);
            return view;
        }
    }

}
