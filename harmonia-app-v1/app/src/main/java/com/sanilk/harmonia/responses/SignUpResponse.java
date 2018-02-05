package com.sanilk.harmonia.responses;

/**
 * Created by sanil on 3/2/18.
 */

public class SignUpResponse extends MyResponse {
    public static final String RESPONSE_TYPE="CREATE_USER_RESPONSE";
    public static final String SUCCESSFUL_KEY="successful";
    public static final String ERROR_CODE_KEY="error_code";
    public static final String ERROR_DETAILS="error_details";

    private boolean isSuccessful;
    private long errorCode;
    private String errorDetails;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public SignUpResponse(boolean isSuccessful, long errorCode, String errorDetails) {
        this.isSuccessful = isSuccessful;
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
        this.responseType=RESPONSE_TYPE;
    }
}
