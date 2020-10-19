package com.example.scoreboard.contract;

import com.example.scoreboard.model.TeamDetail;

import java.util.List;

public interface TeamDetailContract {

    interface TeamDetailView extends BaseContract.BaseView {

        void getTeamDetailsCallback(List<TeamDetail> details);

        void hideBottomMenu();

    }

    interface TeamDetailPresenter extends BaseContract.BasePresenter {

        void getTeamDetails(String teamName);

    }
}
