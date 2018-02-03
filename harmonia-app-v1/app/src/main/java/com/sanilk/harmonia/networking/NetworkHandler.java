package com.sanilk.harmonia.networking;

import com.sanilk.harmonia.entities.User;
import com.sanilk.harmonia.networking.threads.SignUpThread;

import java.util.Hashtable;

/**
 * Created by sanil on 1/2/18.
 */

public class NetworkHandler {

    private static NetworkHandler networkHandler=new NetworkHandler();

    public final static String ADDRESS="http://192.168.1.6:8080/harmonia_backend_v1_war_exploded/MainServlet";

    private Hashtable<String, MyThread> allThreads;

    private NetworkHandler(){
        allThreads=new Hashtable<>();
    }

    public static NetworkHandler getNetworkHandler() {
        return networkHandler;
    }

    public void checkNetwork(){}

    public void startSignUpThread(String key, User user){
        SignUpThread signUpThread=new SignUpThread(user);
        allThreads.put(key, signUpThread);
        signUpThread.startThread();
    }

    public void stopThread(String key){
        if(allThreads.contains(key)){
            allThreads.get(key).stopThread();
        }
    }

}
