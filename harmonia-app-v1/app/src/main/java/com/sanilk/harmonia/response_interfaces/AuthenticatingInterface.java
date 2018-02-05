package com.sanilk.harmonia.response_interfaces;

import com.sanilk.harmonia.networking.MyMessage;
import com.sanilk.harmonia.responses.AuthenticateResponse;
import com.sanilk.harmonia.responses.SignUpResponse;

/**
 * Created by sanil on 3/2/18.
 */

public interface AuthenticatingInterface {
    void responseReceived(AuthenticateResponse response);
    void onFailure();

    enum AuthenticatingMessageType{
        FAILED,
        SUCCESS
    }

    class AuthenticatingMessage extends MyMessage{
        public AuthenticatingMessageType authenticatingMessageType;
        public boolean isAuthentic;
        public AuthenticatingMessage(){
            type= MyMessageType.AUTHENTICATE;
        }
    }
}
