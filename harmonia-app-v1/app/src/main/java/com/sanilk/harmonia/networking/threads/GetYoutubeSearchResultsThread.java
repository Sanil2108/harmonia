package com.sanilk.harmonia.networking.threads;

import com.sanilk.harmonia.networking.JSONParser;
import com.sanilk.harmonia.networking.NetworkHandler;
import com.sanilk.harmonia.response_interfaces.GetYoutubeSearchResultsInterface;
import com.sanilk.harmonia.responses.GetRandomPlaylistResponse;
import com.sanilk.harmonia.responses.GetYoutubeSearchResultsResponse;
import com.sanilk.harmonia.responses.MyResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sanil on 14/3/18.
 */

public class GetYoutubeSearchResultsThread implements MyThread{
    private static final String ADDRESS="https://www.googleapis.com/youtube/v3/search";

    Thread t;
    boolean isRunning;

    public String part;
    public int maxResults;
    public String q;
    public String type;
    public String key;

    GetYoutubeSearchResultsInterface getYoutubeSearchResultsInterface;

    //DEFAULT VALUES
    public static final String KEY="AIzaSyC5dMCKVv3QPbX7LEk_zNGgxMKonA8P6Z8";
    public static final String PART="snippet";
    public static final int MAX_RESULTS=20;
    public static final String TYPE="";

    public GetYoutubeSearchResultsThread(String part, int maxResults, String q, String type, String key,
                                         GetYoutubeSearchResultsInterface getYoutubeSearchResultsInterface) {
        this.part = part;
        this.maxResults = maxResults;
        this.q = q.replaceAll(" ", "%20");
        this.type = type;
        this.key = key;
        this.getYoutubeSearchResultsInterface=getYoutubeSearchResultsInterface;

        t=new Thread(this);
        isRunning=true;
    }

    public GetYoutubeSearchResultsThread(String q, GetYoutubeSearchResultsInterface getYoutubeSearchResultsInterface){
        this(PART, MAX_RESULTS, q, TYPE, KEY, getYoutubeSearchResultsInterface);
    }

    @Override
    public void startThread() {
        t.start();
    }

    @Override
    public void stopThread() {
        isRunning=false;
    }

    @Override
    public void run() {
        try{
            URL url=new URL("https://www.googleapis.com/youtube/v3/search?maxResults="+maxResults+"&key="+key+"&part="+part+"&q="+q+"&type=video");

            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream=connection.getInputStream();
            int c;
            String resp="";
            while((c= inputStream.read()) != -1){
                resp+=(char)c;
            }
            JSONParser jsonParser=new JSONParser();
            GetYoutubeSearchResultsResponse getYoutubeSearchResultsResponse=jsonParser.parseYoutubeSearchResultsResponse(
                    new JSONObject(resp)
            );


//            HttpURLConnection conn5 = (HttpURLConnection) url.openConnection();
//            conn5.setRequestMethod("GET");
//            conn5.setDoInput(true);
//            conn5.setDoOutput(true);
//            DataOutputStream dos=new DataOutputStream(conn5.getOutputStream());

//            OutputStreamWriter writer = new OutputStreamWriter(
//                    conn5.getOutputStream());
//            writer.write(json.toString());
//            writer.close();

//            DataInputStream dis=new DataInputStream(conn5.getInputStream());
//            String responseString=dis.readUTF();
//            JSONParser jsonParser=new JSONParser();
//            GetYoutubeSearchResultsResponse getYoutubeSearchResultsResponse=
//                    jsonParser.parseYoutubeSearchResultsResponse(
//                            new JSONObject(responseString)
//                    );
//
            getYoutubeSearchResultsInterface.responseReceived(getYoutubeSearchResultsResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
