package com.aaroncoplan.todoist;

import com.aaroncoplan.todoist.model.Project;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public List<Project> getAllProjects() {
        Request request = new Request.Builder()
                .get()
                .url(URL_BASE + "/projects")
                .build();

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Project[]> jsonAdapter = moshi.adapter(Project[].class);

        try {
            Response response = httpClient.newCall(request).execute();
            return Arrays.asList(jsonAdapter.fromJson(response.body().string()));
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
