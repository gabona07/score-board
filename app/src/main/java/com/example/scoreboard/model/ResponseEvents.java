package com.example.scoreboard.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class ResponseEvents {

    @SerializedName("events")
    private List<HashMap<String, String>> events;

    public List<HashMap<String, String>> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "EventsResponse{" +
                "events=" + events +
                '}';
    }
}
