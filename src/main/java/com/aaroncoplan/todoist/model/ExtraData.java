package com.aaroncoplan.todoist.model;

public class ExtraData {

    public final String content;

    public ExtraData(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ExtraData{" +
                "content='" + content + '\'' +
                '}';
    }
}
