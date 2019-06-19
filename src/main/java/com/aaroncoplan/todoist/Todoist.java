package com.aaroncoplan.todoist;

import jdk.incubator.http.HttpClient;

public class Todoist {

    private final String URL_BASE = "https://beta.todoist.com/API/v8/";
    private final String token;

    public Todoist(String token) {
        this.token = token;
    }

    public boolean someLibraryMethod() {
        return true;
    }
}
