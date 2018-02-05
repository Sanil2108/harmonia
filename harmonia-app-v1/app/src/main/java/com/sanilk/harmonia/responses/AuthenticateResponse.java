package com.sanilk.harmonia.responses;

public class AuthenticateResponse extends MyResponse{
    public static final String RESPONSE_TYPE="AUTHENTICATE_RESPONSE";
    public static final String SUCCESSFUL_KEY="successful";
    public static final String ERROR_CODE_KEY="error_code";
    public static final String ERROR_DETAILS="error_details";
    public static final String AUTHENTIC_KEY="authentic";

    private boolean isSuccessful;
    private long errorCode;
    private String errorDetails;
    private boolean authentic;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public boolean isAuthentic() {
        return authentic;
    }

    public AuthenticateResponse(boolean isSuccessful, long errorCode, String errorDetails, boolean isAuthentic) {
        this.isSuccessful = isSuccessful;
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
        this.authentic=isAuthentic;
        this.responseType=RESPONSE_TYPE;
    }
}
