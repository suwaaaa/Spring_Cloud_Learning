package com.Utils;


public enum ResultCode {
        SUCCESS_CALL(200,"Call Successful"),
        FAIL_RESULT(400,"Bad Request..."),
        FILE_REQUEST_NOT_SUPPORT_TYPE(400,"No Support For Your File Type !"),
        FILE_REQUEST_SUPPORT_TYPE_ALLOW(200,"Good to Support For Your File Type !"),
        FILE_REQUEST_SIZE_TOO_LARGE(400,"No Support For Too Large File !"),
        FILE_REQUEST_SIZE_ALLOW(200,"File Size Allow Upload"),
        FILE_UPLOAD_COMPLETED(200, "Your File Upload Completed !"),
        FIAL_UNKNOWN_RESPONSE(500, "Unknown Response, Try Again !");


    private String messageString;
    private Integer errorCodeInteger;

    ResultCode(Integer errorCode, String msg) {
        this.errorCodeInteger = errorCode;
        this.messageString = msg;
    }

    public String getMessageValue() {
        return messageString;
    }

    public Integer getErrorCodeKey() {
        return errorCodeInteger;
    }
}
