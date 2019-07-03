package com.aaroncoplan.todoist.model;

import com.squareup.moshi.Json;

public class Due {

    public final String date;
    @Json(name = "recurring") public final boolean isRecurring;
    @Json(name = "datetime") public final String dateTime;
    public final String string;
    public final String timezone;

    public Due(String date, boolean isRecurring, String dateTime, String string, String timezone) {
        this.date = date;
        this.isRecurring = isRecurring;
        this.dateTime = dateTime;
        this.string = string;
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "Due{" +
                "date='" + date + '\'' +
                ", isRecurring=" + isRecurring +
                ", dateTime='" + dateTime + '\'' +
                ", string='" + string + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
