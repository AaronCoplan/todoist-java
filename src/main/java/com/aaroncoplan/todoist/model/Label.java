package com.aaroncoplan.todoist.model;

public class Label {

    public final long id;
    public final String name;
    public final int order;

    public Label(long id, String name, int order) {
        this.id = id;
        this.name = name;
        this.order = order;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                '}';
    }
}