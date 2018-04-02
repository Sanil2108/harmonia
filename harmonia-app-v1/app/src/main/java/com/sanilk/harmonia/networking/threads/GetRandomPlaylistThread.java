package com.sanilk.harmonia.networking.threads;

import android.util.Log;

import com.sanilk.harmonia.networking.JSONParser;
import com.sanilk.harmonia.networking.NetworkHandler;
import com.sanilk.harmonia.response_interfaces.GetRandomPlaylistInterface;
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
 * Created by sanil on 17/2/18.
 */

public class GetRandomPlaylistThread implements MyThread {
    Thread t;
    boolean needToStop=false;

    GetRandomPlaylistInterface getRandomPlaylistInterface;

    public GetRandomPlaylistThread(GetRandomPlaylistInterface getRandomPlaylistInterface) {
        this.getRandomPlaylistInterface = getRandomPlaylistInterface;

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
            HttpURLConnection conn5 = (HttpURLConnection) url.openConnection();
            conn5.setRequestMethod("POST");
            conn5.setDoInput(true);
            conn5.setDoOutput(true);
            DataOutputStream dos=new DataOutputStream(conn5.getOutputStream());


            JSONObject json=new JSONObject();
            json.put("request_type", "GET_RANDOM_PLAYLIST_REQUEST");
            json.put("genres_count", 3);
            JSONArray jsonArray=new JSONArray();
            jsonArray.put("genre1");
            jsonArray.put("genre2");
            jsonArray.put("genre3");
            json.put("genres", jsonArray);
            // System.out.println(json.toString());

            dos.writeUTF(json.toString());
            OutputStreamWriter writer = new OutputStreamWriter(
                    conn5.getOutputStream());
            writer.write(json.toString());
            writer.close();

            DataInputStream dis=new DataInputStream(conn5.getInputStream());
            String responseString=dis.readUTF();
            JSONParser jsonParser=new JSONParser();
            MyResponse myResponse=jsonParser.parse(responseString);
            GetRandomPlaylistResponse getRandomPlaylistResponse=
                    (GetRandomPlaylistResponse)myResponse;

            getRandomPlaylistInterface.responseReceived(getRandomPlaylistResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
