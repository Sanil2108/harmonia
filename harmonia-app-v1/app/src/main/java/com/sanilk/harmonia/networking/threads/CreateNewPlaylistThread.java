package com.sanilk.harmonia.networking.threads;

import com.sanilk.harmonia.entities.Playlist;
import com.sanilk.harmonia.entities.Song;
import com.sanilk.harmonia.networking.JSONParser;
import com.sanilk.harmonia.networking.NetworkHandler;
import com.sanilk.harmonia.response_interfaces.CreateNewPlaylistInterface;
import com.sanilk.harmonia.response_interfaces.GetRandomPlaylistInterface;
import com.sanilk.harmonia.responses.CreateNewPlaylistResponse;
import com.sanilk.harmonia.responses.GetRandomPlaylistResponse;
import com.sanilk.harmonia.responses.MyResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sanil on 18/3/18.
 */

public class CreateNewPlaylistThread implements MyThread {
    Thread t;
    boolean needToStop=false;

    Playlist p;

    CreateNewPlaylistInterface createNewPlaylistInterface;

    public CreateNewPlaylistThread(Playlist p, CreateNewPlaylistInterface createNewPlaylistInterface) {
        this.createNewPlaylistInterface = createNewPlaylistInterface;
        this.p=p;

        t=new Thread(this);
    }

    @Override
    public void startThread() {
        t.run();
    }

    @Override
    public void stopThread() {
        needToStop=true;
    }

    @Override
    public void run() {
        try{
            URL url=new URL(NetworkHandler.ADDRESS);

            JSONParser jsonParser=new JSONParser();
            HttpURLConnection conn5 = (HttpURLConnection) url.openConnection();
            conn5.setRequestMethod("POST");
            conn5.setDoInput(true);
            conn5.setDoOutput(true);
            DataOutputStream dos=new DataOutputStream(conn5.getOutputStream());


            JSONObject json=new JSONObject();
            json.put("request_type", "CREATE_PLAYLIST");
            json.put("playlist_name", p.getName());
            json.put("user_name", p.getUser().getName());
            json.put("songs_count", p.getSongs().size());

            for(Song s:p.getSongs()){

            }


            CreateNewPlaylistResponse createNewPlaylistResponse=(CreateNewPlaylistResponse)jsonParser.parse("");

            createNewPlaylistInterface.responseReceived(createNewPlaylistResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
