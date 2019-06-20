package com.aaroncoplan.todoist.helpers;

import com.squareup.moshi.Json;

public class AttachmentRequest {

    @Json(name = "resource_type") public final String resourceType;
    @Json(name = "file_url") public final String fileUrl;
    @Json(name = "file_type") public final String fileType;
    @Json(name = "file_name") public final String fileName;

    public AttachmentRequest(String resourceType, String fileUrl, String fileType, String fileName) {
        this.resourceType = resourceType;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.fileName = fileName;
    }
}
