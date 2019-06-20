package com.aaroncoplan.todoist;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestExecutor {

    private final int OK = 200;
    private final int OK_NO_DATA = 204;

    private final OkHttpClient httpClient;

    public RequestExecutor(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String execute(Request request) throws Exception {
        Response response = httpClient.newCall(request).execute();
        if(response.code() != OK && response.code() != OK_NO_DATA){
            throw new Exception("HTTP CODE " + response.code());
        }
        return response.body() != null ? response.body().string() : null;
    }
}
