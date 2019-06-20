package com.aaroncoplan.todoist.helpers;

import com.squareup.moshi.Json;

import java.util.List;

public class TaskRequest {

    public final String content;
    @Json(name = "project_id") public final Long projectId;
    public final Integer order;
    @Json(name = "label_ids") public final List<Long> labelIds;
    public final Integer priority;

    @Json(name = "due_string") public final String dueString;
    @Json(name = "due_date") public final String dueDate;
    @Json(name = "due_datetime") public final String dueDateTime;
    @Json(name = "due_lang") public final String dueLang;

    public TaskRequest(String content, Long projectId, Integer order, List<Long> labelIds, Integer priority, String dueString, String dueDate, String dueDateTime, String dueLang) {
        this.content = content;
        this.projectId = projectId;
        this.order = order;
        this.labelIds = labelIds;
        this.priority = priority;
        this.dueString = dueString;
        this.dueDate = dueDate;
        this.dueDateTime = dueDateTime;
        this.dueLang = dueLang;
    }
}
