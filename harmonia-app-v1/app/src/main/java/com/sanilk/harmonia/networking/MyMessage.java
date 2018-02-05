package com.sanilk.harmonia.networking;

/**
 * Created by sanil on 3/2/18.
 */

public class MyMessage {
    public MyMessageType type;

    public enum MyMessageType{
        SIGN_UP,
        AUTHENTICATE
    }
}
