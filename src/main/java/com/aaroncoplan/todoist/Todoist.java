package com.aaroncoplan.todoist;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.time.Duration;

public class Todoist {

    private final String URL_BASE = "https://beta.todoist.com/API/v8";
    private final String token;

    public Todoist(String token) {
        this.token = token;
    }

    public void doTest() {
        var client = new OkHttpClient.Builder()
                .addInterceptor(chain -> chain.proceed(chain.request()
                        .newBuilder()
                        .addHeader("Authorization", String.format("Bearer %s", token))
                        .addHeader("Accept", "application/json")
                        .build()))
                .followRedirects(false)
                .callTimeout(Duration.ofSeconds(20))
                .build();

        var request = new Request.Builder()
                .get()
                .url(URL_BASE + "/projects")
                .build();

        try {
            var response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
