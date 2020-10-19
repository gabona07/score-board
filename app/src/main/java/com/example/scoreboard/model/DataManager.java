package com.example.scoreboard.model;

import android.util.Log;

import com.example.scoreboard.api.ApiService;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {

    private static final String TAG = "DataManager";
    private static Retrofit retrofit = null;
        private static final String BASE_URL = "https://www.thesportsdb.com/";

    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(new OkHttpClient())
                    .build();
        }
        return retrofit;
    }

    public Single<ResponseEvents> getLatestSportEventsResponse() {
        Retrofit client = getClient();
        return client.create(ApiService.class).getLatestEvents();
    }

    public Single<ResponseEvents> getNextSportEventsResponse() {
        Retrofit client = getClient();
        return client.create(ApiService.class).getNextEvents();
    }

    public Observable<ResponseTeamDetails> getTeamDetailsObservable(String teamName) {
        Retrofit client = getClient();
        return client.create(ApiService.class).getTeamDetailsObservable(teamName);
    }

    // TODO: Use this method to fetch teamDetails in the other Activity
    //  and don't forget to compare team id's because this shitty api will return a bunch of teams
    //  so you have to find the correct team with the same id.
    //  check this for example: https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=Liverpool -> this shit returns 2 Liverpool teams!
    public Single<ResponseTeamDetails> getTeamDetailsSingle(String teamName) {
        Retrofit client = getClient();
        return client.create(ApiService.class).getTeamDetailsSingle(teamName);
    }
}
