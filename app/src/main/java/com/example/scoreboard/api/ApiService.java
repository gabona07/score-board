package com.example.scoreboard.api;

import com.example.scoreboard.model.ResponseEvents;
import com.example.scoreboard.model.ResponseTeamDetails;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/v1/json/1/eventspastleague.php?id=4328")
    Single<ResponseEvents> getLatestEvents();

    @GET("api/v1/json/1/searchteams.php")
    Observable<ResponseTeamDetails> getTeamDetailsObservable(@Query("t") String teamName);

    @GET("api/v1/json/1/searchteams.php")
    Single<ResponseTeamDetails> getTeamDetailsSingle(@Query("t") String teamName);

    @GET("api/v1/json/1/eventsnextleague.php?id=4328")
    Single<ResponseEvents> getNextEvents();
}
