package com.example.scoreboard.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class ResponseTeamDetails {

    @SerializedName("teams")
    private List<HashMap<String, String>> details;

    public List<HashMap<String, String>> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "TeamDetails{" +
                "details=" + details +
                '}';
    }
}
