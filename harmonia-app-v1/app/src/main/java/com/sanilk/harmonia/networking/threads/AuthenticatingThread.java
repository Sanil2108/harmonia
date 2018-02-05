package com.sanilk.harmonia.networking.threads;

import com.sanilk.harmonia.entities.User;
import com.sanilk.harmonia.networking.JSONParser;
import com.sanilk.harmonia.networking.NetworkHandler;
import com.sanilk.harmonia.response_interfaces.AuthenticatingInterface;
import com.sanilk.harmonia.response_interfaces.SignUpResponseInterface;
import com.sanilk.harmonia.responses.AuthenticateResponse;
import com.sanilk.harmonia.responses.SignUpResponse;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sanil on 3/2/18.
 */

public class AuthenticatingThread implements MyThread {
    Thread t;
    boolean needToStop=false;
    private User user;

    private AuthenticatingInterface authenticatingInterface;

    private final static long MILLISECONDS=1000;

    public AuthenticatingThread(User user, AuthenticatingInterface authenticatingInterface) {
        this.user = user;
        this.authenticatingInterface=authenticatingInterface;
        t=new Thread(this);
    }

    @Override
    public void startThread() {
        t.start();
    }

    @Override
    public void stopThread() {
        needToStop=true;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if(needToStop){
                    break;
                }
                Thread.sleep(MILLISECONDS);
                URL url = new URL(NetworkHandler.ADDRESS);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                DataOutputStream dos = new DataOutputStream(connection.getOutputStream());

                JSONObject object=new JSONObject();
                object.put("request_type", "AUTHENTICATE");
                object.put("user_name", user.getName());
                object.put("password", user.getPassword());

                String jsonObject=object.toString();

                dos.writeUTF(jsonObject);
                dos.flush();
                dos.close();

                DataInputStream din=new DataInputStream(connection.getInputStream());
                String response=din.readUTF();
                din.close();

                connection.disconnect();

                JSONParser jsonParser=new JSONParser();
                AuthenticateResponse authenticateResponse=(AuthenticateResponse)jsonParser.parse(response);

                authenticatingInterface.responseReceived(authenticateResponse);

                //check for response
                //break;

            } catch (Exception e) {
                e.printStackTrace();
                authenticatingInterface.onFailure();
            }
            break;
        }
    }
}
