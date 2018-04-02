package com.sanilk.harmonia;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sanilk.harmonia.networking.NetworkHandler;
import com.sanilk.harmonia.response_interfaces.GetYoutubeSearchResultsInterface;
import com.sanilk.harmonia.responses.GetYoutubeSearchResultsResponse;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AddSongDialog extends Dialog implements View.OnClickListener {
    Activity a;

    Song[] songs;
    ListView results;
    ArrayList<Song> selectedSongs;

    Handler uiHandler;

    private static final int MESSAGE_UPDATE_LIST=1;

    public AddSongDialog(Activity a){
        super(a);
        this.a=a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_song_layout);

        selectedSongs=new ArrayList<>();

        uiHandler=new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                GetYoutubeSearchResultsResponse response=
                        (GetYoutubeSearchResultsResponse)msg.obj;

                GetYoutubeSearchResultsResponse.Item[] items=response.getItems();
                int i=0;
                songs=new Song[items.length];
                for(GetYoutubeSearchResultsResponse.Item item:items){
                    Song song=new Song(
                            item.getSnippet().getTitle(),
                            item.getSnippet().getChannelTitle(),
                            item.getSnippet().bitmap
                    );
                    songs[i]=song;
                    i++;
                }


                results=(ListView)findViewById(R.id.new_song_layout_search_result_list);
                AddSongListAdaptor addSongListAdaptor=new AddSongListAdaptor(a, songs);
                results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        onSongClick(position);
                    }
                });
                results.setAdapter(addSongListAdaptor);
            }
        };

        EditText searchBar=(EditText)findViewById(R.id.new_song_layout_search);
        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if(event.getAction()==KeyEvent.ACTION_DOWN){
                    switch (actionId){
                        case 6:
                        case KeyEvent.KEYCODE_ENTER:
                            NetworkHandler networkHandler=NetworkHandler.getNetworkHandler();
                            networkHandler.startGetYoutubeSearchDataThread(
                                    "GET_YOUTUBE_SEARCH_DATA",
                                    new GetYoutubeSearchResultsInterface() {
                                        @Override
                                        public void responseReceived(GetYoutubeSearchResultsResponse response) {
                                            for(GetYoutubeSearchResultsResponse.Item i:response.getItems()) {
                                                i.getSnippet().setBitmap();
                                            }

                                            Message message=new Message();
                                            message.obj=response;
                                            uiHandler.sendMessage(
                                                    message
                                            );
                                        }

                                        @Override
                                        public void onFailure() {

                                        }
                                    },
                                    v.getText().toString()
                            );
                            return true;
                        default:
                            return false;
                    }
//                }
            }
        });

        Button doneButton=(Button)findViewById(R.id.new_song_layout_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NewPlaylistActivity)a).onDialogDone(selectedSongs);
            }
        });

        Button cancelButton=(Button)findViewById(R.id.new_song_layout_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NewPlaylistActivity)a).onDialogCancel();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public class Song{
        String name;
        String artist;
        Bitmap image;

        public Song(String name, String artist, Bitmap bitmap) {
            this.name = name;
            this.artist = artist;
            this.image = bitmap;
        }
    }

    private void onSongClick(int pos){
        Song s=songs[pos];
        if(!selectedSongs.contains(s)) {
            selectedSongs.add(s);

            LinearLayout selectedSongsLayout=
                    (LinearLayout)findViewById(R.id.new_song_layout_selected_songs);
            ImageView imageView=new ImageView(a);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT
            ));
            selectedSongsLayout.addView(imageView);
        }
    }

    private static class AddSongListAdaptor extends ArrayAdapter<Song>{
        Context context;
        private Song[] songs;

        public AddSongListAdaptor(@NonNull Context context, @NonNull Song[] songs) {
            super(context, R.layout.fragment_playlist_row, songs);
            this.context=context;
            this.songs=songs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            try {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = layoutInflater.inflate(R.layout.new_song_layout_row, parent, false);

                ImageView thumbnailImageView = (ImageView) v.findViewById(R.id.new_song_layout_row_thumbnail);
                thumbnailImageView.setImageBitmap(songs[position].image);


                TextView titleTextView = (TextView) v.findViewById(R.id.new_song_layout_row_title);
                titleTextView.setText(songs[position].name);

                TextView artistTextView = (TextView) v.findViewById(R.id.new_song_layout_row_artist);
                artistTextView.setText(songs[position].artist);

                return v;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }


}
