package com.aaroncoplan.todoist.model;

import com.squareup.moshi.Json;

import java.util.List;

public class Task {

    public final long id;
    @Json(name = "project_id") public final long projectId;
    public final String content;
    @Json(name = "completed") public final boolean isCompleted;
    @Json(name = "label_ids") public final List<Long> labelIds;
    public final int order;
    public final int indent;
    public final int priority;
    public final Due due;
    public final String url;
    @Json(name = "comment_count") public final int commentCount;

    public Task(long id, long projectId, String content, boolean isCompleted, List<Long> labelIds, int order, int indent, int priority, Due due, String url, int commentCount) {
        this.id = id;
        this.projectId = projectId;
        this.content = content;
        this.isCompleted = isCompleted;
        this.labelIds = labelIds;
        this.order = order;
        this.indent = indent;
        this.priority = priority;
        this.due = due;
        this.url = url;
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", content='" + content + '\'' +
                ", isCompleted=" + isCompleted +
                ", labelIds=" + labelIds +
                ", order=" + order +
                ", indent=" + indent +
                ", priority=" + priority +
                ", due='" + due + '\'' +
                ", url='" + url + '\'' +
                ", commentCount=" + commentCount +
                '}';
    }
}

