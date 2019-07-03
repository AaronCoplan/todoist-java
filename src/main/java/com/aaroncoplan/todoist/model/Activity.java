package com.aaroncoplan.todoist.model;

import com.squareup.moshi.Json;

public class Activity {

    public final long id;
    @Json(name = "object_type") public final String objectType;
    @Json(name = "object_id") public final long objectId;
    @Json(name = "event_type") public final String eventType;
    @Json(name = "event_date") public final String eventDateTime;
    @Json(name = "parent_project_id") public final Long parentProjectId;
    @Json(name = "parent_item_id") public final Long parentItemId;
    @Json(name = "initiator_id") public final Long initiatorId;
    @Json(name = "extra_data") public final ExtraData extraData;

    public Activity(long id, String objectType, long objectId, String eventType, String eventDateTime, Long parentProjectId, Long parentItemId, Long initiatorId, ExtraData extraData) {
        this.id = id;
        this.objectType = objectType;
        this.objectId = objectId;
        this.eventType = eventType;
        this.eventDateTime = eventDateTime;
        this.parentProjectId = parentProjectId;
        this.parentItemId = parentItemId;
        this.initiatorId = initiatorId;
        this.extraData = extraData;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", objectType='" + objectType + '\'' +
                ", objectId=" + objectId +
                ", eventType='" + eventType + '\'' +
                ", eventDateTime='" + eventDateTime + '\'' +
                ", parentProjectId=" + parentProjectId +
                ", parentItemId=" + parentItemId +
                ", initiatorId=" + initiatorId +
                ", extraData=" + extraData +
                '}';
    }
}
