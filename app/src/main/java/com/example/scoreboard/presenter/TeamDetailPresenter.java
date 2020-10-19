package com.example.scoreboard.presenter;

import android.util.Log;

import com.example.scoreboard.contract.BaseContract;
import com.example.scoreboard.contract.TeamDetailContract;
import com.example.scoreboard.model.DataManager;
import com.example.scoreboard.model.ResponseTeamDetails;
import com.example.scoreboard.model.TeamDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TeamDetailPresenter implements TeamDetailContract.TeamDetailPresenter {

    private static final String TAG = "TeamDetailPresenter";
    private DataManager dataManager;
    private TeamDetailContract.TeamDetailView view;

    public TeamDetailPresenter() {
        this.dataManager = new DataManager();
    }


    @Override
    public void getTeamDetails(String teamName) {
        view.showLoading();
        Single<ResponseTeamDetails> response = dataManager.getTeamDetailsSingle(teamName);
        List<TeamDetail> teamDetails = new ArrayList<>();
        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseTeamDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseTeamDetails value) {
                        List<HashMap<String, String>> details = value.getDetails();
                        for (int i = 0; i < 1; i++) {
                            HashMap<String, String> currentDetail = details.get(i);
                            TeamDetail teamDetail = new TeamDetail(currentDetail.get("strTeam"),
                                    currentDetail.get("strAlternate"),
                                    currentDetail.get("intFormedYear"),
                                    currentDetail.get("strCountry"),
                                    currentDetail.get("strTeamBadge"),
                                    currentDetail.get("strFacebook"),
                                    currentDetail.get("strTwitter"),
                                    currentDetail.get("strInstagram"),
                                    currentDetail.get("strDescriptionEN"),
                                    currentDetail.get("strYoutube"));
                            teamDetails.add(teamDetail);
                        }
                        view.hideLoading();
                        view.getTeamDetailsCallback(teamDetails);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideBottomMenu();
                        view.hideLoading();
                        view.displayError();
                        Log.d(TAG, "onError: " + e);
                    }
                });
    }

    @Override
    public void onAttach(BaseContract.BaseView view) {
        this.view = (TeamDetailContract.TeamDetailView) view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }
}
