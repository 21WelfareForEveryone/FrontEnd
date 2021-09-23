package com.example.tave0915;

import android.util.Log;

public class UserLoginInfo {
    private String mToken;
    private Boolean isSuccess;
    private int statusCode;

    public UserLoginInfo(){

    }
//    public UserLoginInfo(String mToken, Boolean isSuccess, int statusCode){
//        this.mToken = mToken;
//        this.isSuccess = isSuccess;
//        this.statusCode = statusCode;
//    }

    public void setToken(String mToken) {
        this.mToken = mToken;
    };
    public String getToken() {
        return mToken;
    };
    public void setIsSuccess(Boolean isSuccess){
        this.isSuccess = isSuccess;
    };
    public Boolean getIsSuccess(){
        return isSuccess;
    };
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    };
    public int getStatusCode() {
        return statusCode;
    };
}
