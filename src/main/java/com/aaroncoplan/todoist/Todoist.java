package com.aaroncoplan.todoist;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.time.Duration;

public class Todoist {

    private final String URL_BASE = "https://beta.todoist.com/API/v8";
    private final String token;
    private final OkHttpClient httpClient;

    public Todoist(String token) {
        this.token = token;
        this.httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> chain.proceed(chain.request()
                        .newBuilder()
                        .addHeader("Authorization", String.format("Bearer %s", token))
                        .addHeader("Accept", "application/json")
                        .build()))
                .followRedirects(false)
                .callTimeout(Duration.ofSeconds(20))
                .build();
    }

    public Todoist(String token, OkHttpClient httpClient) {
        this.token = token;
        this.httpClient = httpClient;
    }

    public void getAllProjects() {
        Request request = new Request.Builder()
                .get()
                .url(URL_BASE + "/projects")
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
