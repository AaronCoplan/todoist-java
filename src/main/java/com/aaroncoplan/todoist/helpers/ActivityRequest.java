package com.aaroncoplan.todoist.helpers;

import com.squareup.moshi.Json;

import java.util.List;

public class ActivityRequest {

    public final int limit;
    public final int offset;
    @Json(name = "object_event_types") public final List<String> activityTypes;
    @Json(name = "parent_item_id") public final long parentItemId;

    public ActivityRequest(int limit, int offset, List<String> activityTypes, long parentItemId) {
        this.limit = limit;
        this.offset = offset;
        this.activityTypes = activityTypes;
        this.parentItemId = parentItemId;
    }
}
