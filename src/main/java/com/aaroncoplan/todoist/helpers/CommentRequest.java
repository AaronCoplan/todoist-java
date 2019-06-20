package com.aaroncoplan.todoist.helpers;

import com.squareup.moshi.Json;

public class CommentRequest {

    @Json(name = "task_id") public final Long taskId;
    @Json(name = "project_id") public final Long projectId;
    public final String content;
    public final AttachmentRequest attachmentRequest;

    public CommentRequest(Long taskId, Long projectId, String content, AttachmentRequest attachmentRequest) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.content = content;
        this.attachmentRequest = attachmentRequest;
    }
}
