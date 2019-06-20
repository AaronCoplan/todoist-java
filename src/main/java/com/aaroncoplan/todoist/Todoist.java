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

    private final int OK = 200;
    private final int OK_NO_DATA = 204;

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
            if(response.code() != OK && response.code() != OK_NO_DATA) throw new Exception("HTTP CODE " + response.code());
            String responseBody = response.body().string();
            return Arrays.asList(jsonAdapter.fromJson(responseBody));
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Project getProject(long id) {
        Request request = new Request.Builder()
                .get()
                .url(URL_BASE + "/projects/" + id)
                .build();

        Moshi moshi = new Moshi.Builder().build();

        JsonAdapter<Project> jsonAdapter = moshi.adapter(Project.class);

        try {
            Response response = httpClient.newCall(request).execute();
            if(response.code() != OK && response.code() != OK_NO_DATA) throw new Exception("HTTP CODE " + response.code());
            String responseBody = response.body().string();
            return jsonAdapter.fromJson(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Project createNewProject(String name) {
        throw new UnsupportedOperationException();
    }

    public Project updateProject(long id, String name) {
        throw new UnsupportedOperationException();
    }

    public void deleteProject(long id) {
        throw new UnsupportedOperationException();
    }
}
