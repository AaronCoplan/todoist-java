package com.aaroncoplan.todoist.model;

import com.squareup.moshi.Json;

public class ExtraData {

    public final String content;
    @Json(name = "due_date") public final String dueDate;
    @Json(name = "last_due_date") public final String lastDueDate;

    public ExtraData(String content, String dueDate, String lastDueDate) {
        this.content = content;
        this.dueDate = dueDate;
        this.lastDueDate = lastDueDate;
    }

    @Override
    public String toString() {
        return "ExtraData{" +
                "content='" + content + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", lastDueDate='" + lastDueDate + '\'' +
                '}';
    }
}
