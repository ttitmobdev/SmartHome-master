package com.example.user.smarthome;

public class ApiUtils {
    private ApiUtils(){}
    public static final String BASE_URL = "http://wsr.ru/";
    public static API getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(API.class);
    }
}
