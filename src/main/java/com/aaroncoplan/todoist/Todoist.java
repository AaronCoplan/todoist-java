package com.aaroncoplan.todoist;

import com.aaroncoplan.todoist.model.Project;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class Todoist {

    private final String URL_BASE = "https://beta.todoist.com/API/v8";
    private final String token;
    private final RequestExecutor requestExecutor;

    public Todoist(String token) {
        this.token = token;
        this.requestExecutor = new RequestExecutor(new OkHttpClient.Builder()
                .addInterceptor(chain -> chain.proceed(chain.request()
                        .newBuilder()
                        .addHeader("Authorization", String.format("Bearer %s", token))
                        .addHeader("Accept", "application/json")
                        .build()))
                .followRedirects(false)
                .callTimeout(Duration.ofSeconds(20))
                .build());
    }

    public Todoist(String token, OkHttpClient httpClient) {
        this.token = token;
        this.requestExecutor = new RequestExecutor(httpClient);
    }

    public List<Project> getAllProjects() {
        Request request = new Request.Builder()
                .get()
                .url(URL_BASE + "/projects")
                .build();

        try {
            String responseBody = requestExecutor.execute(request);
            return JsonAdapters.extractProjectList(responseBody);
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

        try {
            String responseBody = requestExecutor.execute(request);
            return JsonAdapters.extractProject(responseBody);
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
