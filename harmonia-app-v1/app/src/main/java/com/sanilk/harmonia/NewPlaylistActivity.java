package com.sanilk.harmonia;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sanilk.harmonia.entities.Song;

import java.util.ArrayList;

public class NewPlaylistActivity extends AppCompatActivity {
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_playlist);

        this.activity=this;

        ImageView imageView=(ImageView)findViewById(R.id.activity_new_playlist_add);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSongDialog dialog=new AddSongDialog(activity);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }

    protected void onDialogCancel(){
        finish();
    }

    protected void onDialogDone(ArrayList<
            com.sanilk.harmonia.AddSongDialog.Song> songs){

    }
}
