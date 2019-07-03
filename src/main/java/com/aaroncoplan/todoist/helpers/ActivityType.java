package com.aaroncoplan.todoist.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum ActivityType {
    ALL("item:", "note:added"),
    ADDED("item:added"),
    UPDATED("item:updated"),
    COMPLETED("item:completed"),
    UNCOMPLETED("item:uncompleted"),
    DELETED("item:completed"),
    ADDED_COMMENT("note:added");


    private final List<String> vals;

    ActivityType(String... vals) {
        this.vals = Arrays.asList(vals);
    }

    public Stream<String> getStream() {
        return vals.stream();
    }
}
