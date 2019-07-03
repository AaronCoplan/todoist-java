package com.aaroncoplan.todoist.helpers;

import com.squareup.moshi.Json;

import java.util.List;

public class ActivityRequest {

    public final int limit;
    public final int offset;
    @Json(name = "object_event_types") public final List<String> activityTypes;
    @Json(name = "parent_item_id") public final Long parentItemId;
    @Json(name = "include_parent_object") public final Boolean includeParentObject;
    @Json(name = "annotate_notes") public final boolean annotateNotes;
    @Json(name = "parent_project_id") public final Long parentProjectId;
    @Json(name = "include_child_objects") public final Boolean includeChildObjects;

    public ActivityRequest(int limit, int offset, List<String> activityTypes, Long parentItemId, Boolean includeParentObject, boolean annotateNotes, Long parentProjectId, Boolean includeChildObjects) {
        this.limit = limit;
        this.offset = offset;
        this.activityTypes = activityTypes;
        this.parentItemId = parentItemId;
        this.includeParentObject = includeParentObject;
        this.annotateNotes = annotateNotes;
        this.parentProjectId = parentProjectId;
        this.includeChildObjects = includeChildObjects;
    }
}
