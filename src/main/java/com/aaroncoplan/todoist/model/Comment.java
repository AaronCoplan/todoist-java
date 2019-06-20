package com.aaroncoplan.todoist.model;

import com.squareup.moshi.Json;

public class Comment {

    public final Long id;
    @Json(name = "task_id") public final Long taskId;
    @Json(name = "project_id") public final Long projectId;
    public final String posted;
    public final String content;

    public Comment(Long id, Long taskId, Long projectId, String posted, String content) {
        this.id = id;
        this.taskId = taskId;
        this.projectId = projectId;
        this.posted = posted;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", projectId=" + projectId +
                ", posted='" + posted + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
