package com.sanilk.harmonia.networking;

import com.sanilk.harmonia.responses.AuthenticateResponse;
import com.sanilk.harmonia.responses.MyResponse;
import com.sanilk.harmonia.responses.SignUpResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sanil on 3/2/18.
 */

public class JSONParser {
    private static final String RESPONSE_TYPE_KEY="response_type";

    public MyResponse parse(String response) throws JSONException{
        JSONObject jsonObject=new JSONObject(response);
        String responseType=jsonObject.getString(RESPONSE_TYPE_KEY);
        switch (responseType){
            case SignUpResponse.RESPONSE_TYPE:
                return parseSignupResponse(jsonObject);
            case AuthenticateResponse.RESPONSE_TYPE:
                return parseAuthenticateResponse(jsonObject);
            default:
                return null;
        }
    }

    private AuthenticateResponse parseAuthenticateResponse(JSONObject jsonObject) throws JSONException{
        boolean successful=jsonObject.getBoolean(AuthenticateResponse.SUCCESSFUL_KEY);
        long errorCode=jsonObject.getLong(AuthenticateResponse.ERROR_CODE_KEY);
        String errorDetails=jsonObject.getString(AuthenticateResponse.ERROR_DETAILS);
        boolean authentic=jsonObject.getBoolean(AuthenticateResponse.AUTHENTIC_KEY);

        return new AuthenticateResponse(successful, errorCode, errorDetails, authentic);
    }

    private SignUpResponse parseSignupResponse(JSONObject jsonObject) throws JSONException{
        boolean successful=jsonObject.getBoolean(SignUpResponse.SUCCESSFUL_KEY);
        long errorCode=jsonObject.getLong(SignUpResponse.ERROR_CODE_KEY);
        String errorDetails=jsonObject.getString(SignUpResponse.ERROR_DETAILS);

        return new SignUpResponse(successful, errorCode, errorDetails);
    }

}
