package com.sanilk.harmonia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpecificGenreFragment extends Fragment {
    String genre;

    public SpecificGenreFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_specific_genre, container, false);
        Bundle args=this.getArguments();
        genre=args.getString("GENRE_NAME");
        TextView title=view.findViewById(R.id.fragment_specific_genre_textview_title);
        if(!genre.equals("")) {
            title.setText("Hot in "+genre);
        }
        return view;
    }

}
