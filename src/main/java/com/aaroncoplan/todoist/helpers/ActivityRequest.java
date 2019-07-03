package com.aaroncoplan.todoist.helpers;

import com.squareup.moshi.Json;

import java.util.List;

public class ActivityRequest {

    public final int limit;
    public final int offset;
    @Json(name = "object_event_types") public final List<String> activityTypes;
    @Json(name = "parent_item_id") public final long parentItemId;
    @Json(name = "include_parent_object") public final boolean includeParentObject;
    @Json(name = "annotate_notes") public final boolean annotateNotes;

    public ActivityRequest(int limit, int offset, List<String> activityTypes, long parentItemId, boolean includeParentObject, boolean annotateNotes) {
        this.limit = limit;
        this.offset = offset;
        this.activityTypes = activityTypes;
        this.parentItemId = parentItemId;
        this.includeParentObject = includeParentObject;
        this.annotateNotes = annotateNotes;
    }
}
