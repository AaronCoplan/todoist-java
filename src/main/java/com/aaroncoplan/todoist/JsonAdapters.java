package com.aaroncoplan.todoist;

import com.aaroncoplan.todoist.model.Project;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonAdapters {

    private static final Moshi moshi = new Moshi.Builder().build();

    private static final JsonAdapter<Project[]> projectArrayJsonAdapter = moshi.adapter(Project[].class);
    private static final JsonAdapter<Project> projectJsonAdapter = moshi.adapter(Project.class);

    public static List<Project> extractProjectList(String json) throws IOException {
        return Arrays.asList(projectArrayJsonAdapter.fromJson(json));
    }

    public static Project extractProject(String json) throws IOException {
        return projectJsonAdapter.fromJson(json);
    }
}
