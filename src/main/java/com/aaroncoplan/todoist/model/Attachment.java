package com.aaroncoplan.todoist.model;

import com.squareup.moshi.Json;

public class Attachment {

    @Json(name = "file_size") public final long fileSize;
    @Json(name = "file_type") public final String fileType;
    @Json(name = "file_name") public final String fileName;
    @Json(name = "upload_state") public final String uploadState;
    @Json(name = "resource_type") public final String resourceType;
    @Json(name = "file_url") public final String fileUrl;

    public Attachment(long fileSize, String fileType, String fileName, String uploadState, String resourceType, String fileUrl) {
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.fileName = fileName;
        this.uploadState = uploadState;
        this.resourceType = resourceType;
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "fileSize=" + fileSize +
                ", fileType='" + fileType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", uploadState='" + uploadState + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}