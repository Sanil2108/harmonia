package com.sanilk.harmonia.response_interfaces;

import com.sanilk.harmonia.networking.MyMessage;
import com.sanilk.harmonia.responses.SignUpResponse;

/**
 * Created by sanil on 3/2/18.
 */

public interface SignUpResponseInterface {
    void responseReceived(SignUpResponse response);
    void onFailure();

    enum SignUpMessageType{
        FAILED,
        SUCCESS
    }

    class SignUpMessage extends MyMessage{
        public SignUpMessageType signUpMessageType;
        public SignUpMessage(){
            type= MyMessageType.SIGN_UP;
        }
    }
}
