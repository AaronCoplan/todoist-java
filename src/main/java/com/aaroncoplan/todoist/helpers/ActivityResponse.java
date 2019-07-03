package com.aaroncoplan.todoist.helpers;

import com.aaroncoplan.todoist.model.Activity;

import java.util.List;

public class ActivityResponse {

    public final int count;
    public final List<Activity> events;

    public ActivityResponse(int count, List<Activity> events) {
        this.count = count;
        this.events = events;
    }
}
