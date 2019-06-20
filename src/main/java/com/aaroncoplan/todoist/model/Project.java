package com.aaroncoplan.todoist.model;

import com.squareup.moshi.Json;

public class Project {

    private final long id;
    private final String name;
    private final int order;
    private final int indent;
    @Json(name = "comment_count") private final int commentCount;

    public Project(int id, String name, int order, int indent, int commentCount) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.indent = indent;
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", indent=" + indent +
                ", commentCount=" + commentCount +
                '}';
    }
}
