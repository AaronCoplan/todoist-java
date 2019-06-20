package com.aaroncoplan.todoist;

import com.aaroncoplan.todoist.helpers.ProjectRequest;
import com.aaroncoplan.todoist.model.Project;
import com.aaroncoplan.todoist.model.Task;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonAdapters {

    private static final Moshi moshi = new Moshi.Builder().build();

    private static final JsonAdapter<Project[]> projectArrayJsonAdapter = moshi.adapter(Project[].class);
    private static final JsonAdapter<Project> projectJsonAdapter = moshi.adapter(Project.class);
    private static final JsonAdapter<ProjectRequest> projectRequestJsonAdapter = moshi.adapter(ProjectRequest.class);

    private static final JsonAdapter<Task[]> taskArrayJsonAdapter = moshi.adapter(Task[].class);

    public static List<Project> extractProjectList(String json) throws IOException {
        return Arrays.asList(projectArrayJsonAdapter.fromJson(json));
    }

    public static Project extractProject(String json) throws IOException {
        return projectJsonAdapter.fromJson(json);
    }

    public static String writeProjectRequest(ProjectRequest projectRequest) {
        return projectRequestJsonAdapter.toJson(projectRequest);
    }

    public static List<Task> extractTaskList(String json) throws IOException {
        return Arrays.asList(taskArrayJsonAdapter.fromJson(json));
    }
 }
