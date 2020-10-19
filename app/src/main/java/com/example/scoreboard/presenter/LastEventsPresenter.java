package com.example.scoreboard.presenter;

import android.util.Log;

import com.example.scoreboard.contract.BaseContract;
import com.example.scoreboard.contract.LastEventsContract;
import com.example.scoreboard.model.DataManager;
import com.example.scoreboard.model.ResponseEvents;
import com.example.scoreboard.model.SportEvent;
import com.example.scoreboard.model.ResponseTeamDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LastEventsPresenter implements LastEventsContract.LastEventsPresenter {

    private LastEventsContract.LastEventsView view;
    private DataManager dataManager;
    private static final String TAG = "BasePresenter";

    public LastEventsPresenter() {
        this.dataManager = new DataManager();
    }

    @Override
    public void onAttach(BaseContract.BaseView view) {
        this.view = (LastEventsContract.LastEventsView) view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    @Override
    public void getLastEvents() {
        view.showLoading();
        Single<ResponseEvents> response = dataManager.getLatestSportEventsResponse();
        List<SportEvent> sportEvents = new ArrayList<>();
        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseEvents>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(ResponseEvents value) {
                        List<HashMap<String, String>> events = value.getEvents();
                        for (int i = 0; i < events.size(); i++) {
                            HashMap<String, String> currentEvent = events.get(i);
                            SportEvent sportEvent = new SportEvent(currentEvent.get("idHomeTeam"),
                                    currentEvent.get("idAwayTeam"),
                                    currentEvent.get("strHomeTeam"),
                                    currentEvent.get("strAwayTeam"),
                                    currentEvent.get("intHomeScore"),
                                    currentEvent.get("intAwayScore"),
                                    currentEvent.get("dateEvent"));
                            sportEvents.add(sportEvent);
                        }
                        view.getLastEventsCallBack(sportEvents);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideRecyclerView();
                        view.hideRefreshBar();
                        view.hideLoading();
                        view.displayError();
                        Log.d(TAG, "onError: " + e);
                    }
                });
    }

    @Override
    public void getTeamBadges(List<SportEvent> events) {

        int[] iterationNum = new int[1];
        int lastIterationNum = events.size() * 2;
        for (SportEvent sportEvent : events) {
            Observable<ResponseTeamDetails> homeTeamResponse = dataManager.getTeamDetailsObservable(sportEvent.getHomeTeam());
            Observable<ResponseTeamDetails> awayTeamResponse = dataManager.getTeamDetailsObservable(sportEvent.getAwayTeam());
            Observable.merge(homeTeamResponse, awayTeamResponse)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseTeamDetails>() {

                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(ResponseTeamDetails value) {
                            List<HashMap<String, String>> teamDetails = value.getDetails();
                            HashMap<String, String> details = teamDetails.get(0);
                            if (sportEvent.getIdHomeTeam().equals(details.get("idTeam"))) {
                                sportEvent.setHomeTeamBadgeUrl(details.get("strTeamBadge"));
                            } else if (sportEvent.getIdAwayTeam().equals(details.get("idTeam"))) {
                                sportEvent.setAwayTeamBadgeUrl(details.get("strTeamBadge"));
                            }
                            iterationNum[0]++;
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError: " + e);
                        }

                        @Override
                        public void onComplete() {
                            if (lastIterationNum == iterationNum[0]) {
                                view.hideRefreshBar();
                                view.hideLoading();
                                view.getTeamBadgesCallBack(events);
                            }
                        }
                    });
        }
    }
}