package com.example.scoreboard.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TeamDetail implements Parcelable {

    String strTeam;
    String strAlternate;
    String intFormedYear;
    String strCountry;
    String strTeamBadge;
    String strFacebook;
    String strTwitter;
    String strInstagram;
    String strDescriptionEN;
    String strYoutube;

    public TeamDetail(String strTeam, String strAlternate, String intFormedYear, String strCountry, String strTeamBadge, String strFacebook, String strTwitter, String strInstagram, String strDescriptionEN, String strYoutube) {
        this.strTeam = strTeam;
        this.strAlternate = strAlternate;
        this.intFormedYear = "(" + intFormedYear + ")";
        this.strCountry = strCountry;
        this.strTeamBadge = strTeamBadge;
        this.strFacebook = strFacebook;
        this.strTwitter = strTwitter;
        this.strInstagram = strInstagram;
        this.strDescriptionEN = strDescriptionEN;
        this.strYoutube = strYoutube;
    }

    public String getStrAlternate() {
        return strAlternate;
    }

    public String getIntFormedYear() {
        return intFormedYear;
    }

    public String getStrTeamBadge() {
        return strTeamBadge;
    }

    public String getStrFacebook() {
        return strFacebook;
    }

    public String getStrTwitter() {
        return strTwitter;
    }

    public String getStrInstagram() {
        return strInstagram;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    protected TeamDetail(Parcel in) {
        strTeam = in.readString();
        strAlternate = in.readString();
        intFormedYear = in.readString();
        strCountry = in.readString();
        strTeamBadge = in.readString();
        strFacebook = in.readString();
        strTwitter = in.readString();
        strInstagram = in.readString();
        strDescriptionEN = in.readString();
        strYoutube = in.readString();
    }

    public static final Creator<TeamDetail> CREATOR = new Creator<TeamDetail>() {
        @Override
        public TeamDetail createFromParcel(Parcel in) {
            return new TeamDetail(in);
        }

        @Override
        public TeamDetail[] newArray(int size) {
            return new TeamDetail[size];
        }
    };

    public String getStrTeam() {
        return strTeam;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(strTeam);
        dest.writeString(strAlternate);
        dest.writeString(intFormedYear);
        dest.writeString(strCountry);
        dest.writeString(strTeamBadge);
        dest.writeString(strFacebook);
        dest.writeString(strTwitter);
        dest.writeString(strInstagram);
        dest.writeString(strDescriptionEN);
        dest.writeString(strYoutube);
    }
}
