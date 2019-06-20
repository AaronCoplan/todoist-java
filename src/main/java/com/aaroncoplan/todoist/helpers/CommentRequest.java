package com.aaroncoplan.todoist.helpers;

import com.squareup.moshi.Json;

public class CommentRequest {

    @Json(name = "task_id") public final Long taskId;
    @Json(name = "project_id") public final Long projectId;
    public final String content;
    public final AttachmentRequest attachment;

    public CommentRequest(Long taskId, Long projectId, String content, AttachmentRequest attachment) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.content = content;
        this.attachment = attachment;
    }
}
