package com.example.scoreboard.model;


import com.squareup.picasso.Picasso;

public class SportEvent {

    private String idHomeTeam;
    private String idAwayTeam;
    private String homeTeam;
    private String awayTeam;
    private String homeScore;
    private String awayScore;
    private String dateEvent;
    private String homeTeamBadgeUrl;
    private String awayTeamBadgeUrl;

    public SportEvent(String idHomeTeam, String idAwayTeam, String homeTeam, String awayTeam, String homeScore, String awayScore, String dateEvent) {
        this.idHomeTeam = idHomeTeam;
        this.idAwayTeam = idAwayTeam;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.dateEvent = dateEvent;
    }

    public SportEvent(String idHomeTeam, String idAwayTeam, String homeTeam, String awayTeam, String dateEvent) {
        this.idHomeTeam = idHomeTeam;
        this.idAwayTeam = idAwayTeam;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.dateEvent = dateEvent;
    }

    public String getIdHomeTeam() {
        return idHomeTeam;
    }

    public String getIdAwayTeam() {
        return idAwayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public String getHomeTeamBadgeUrl() {
        return homeTeamBadgeUrl;
    }

    public void setHomeTeamBadgeUrl(String homeTeamBadgeUrl) {
        Picasso.get().load(homeTeamBadgeUrl);
        this.homeTeamBadgeUrl = homeTeamBadgeUrl;
    }

    public String getAwayTeamBadgeUrl() {
        return awayTeamBadgeUrl;
    }

    public void setAwayTeamBadgeUrl(String awayTeamBadgeUrl) {
        Picasso.get().load(awayTeamBadgeUrl);
        this.awayTeamBadgeUrl = awayTeamBadgeUrl;
    }
}
