package com.aaroncoplan.todoist.model;

import com.squareup.moshi.Json;

public class Project {

    public final long id;
    public final String name;
    public final int order;
    public final int indent;
    @Json(name = "comment_count") public final int commentCount;

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
