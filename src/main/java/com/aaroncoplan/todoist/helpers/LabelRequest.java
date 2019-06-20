package com.aaroncoplan.todoist.helpers;

public class LabelRequest {

    public final String name;
    public final Integer order;

    public LabelRequest(String name, Integer order) {
        this.name = name;
        this.order = order;
    }
}
