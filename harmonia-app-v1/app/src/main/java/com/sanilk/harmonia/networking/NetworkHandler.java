package com.sanilk.harmonia.networking;

import com.sanilk.harmonia.entities.User;
import com.sanilk.harmonia.networking.threads.AuthenticatingThread;
import com.sanilk.harmonia.networking.threads.MyThread;
import com.sanilk.harmonia.networking.threads.SignUpThread;
import com.sanilk.harmonia.response_interfaces.AuthenticatingInterface;
import com.sanilk.harmonia.response_interfaces.SignUpResponseInterface;

import java.util.Hashtable;

/**
 * Created by sanil on 1/2/18.
 */

public class NetworkHandler {

    private static NetworkHandler networkHandler=new NetworkHandler();

    public final static String ADDRESS="http://192.168.1.4:8080/harmonia_backend_v1_war_exploded/MainServlet";

    private Hashtable<String, MyThread> allThreads;

    private NetworkHandler(){
        allThreads=new Hashtable<>();
    }

    public static NetworkHandler getNetworkHandler() {
        return networkHandler;
    }

    public void checkNetwork(){}

    public void startAuthenticationThread(String key, User user, AuthenticatingInterface authenticatingInterface){
        AuthenticatingThread authenticatingThread=new AuthenticatingThread(user, authenticatingInterface);
        allThreads.put(key, authenticatingThread);
        authenticatingThread.startThread();
    }

    public void startSignUpThread(String key, User user, SignUpResponseInterface responseInterface){
        SignUpThread signUpThread=new SignUpThread(user, responseInterface);
        allThreads.put(key, signUpThread);
        signUpThread.startThread();
    }

    public void stopThread(String key){
        if(allThreads.contains(key)){
            allThreads.get(key).stopThread();
        }
    }

}
