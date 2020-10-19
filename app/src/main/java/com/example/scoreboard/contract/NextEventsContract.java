package com.example.scoreboard.contract;

import com.example.scoreboard.model.SportEvent;

import java.util.List;

public interface NextEventsContract {

    interface NextEventsView extends BaseContract.BaseView {

        void getNextEventsCallback(List<SportEvent> events);

        void getTeamBadgesCallBack(List<SportEvent> events);

        void hideRefreshBar();

        void hideRecyclerView();

    }

    interface NextEventsPresenter extends BaseContract.BasePresenter {

        void getNextEvents();

        void getTeamBadges(List<SportEvent> events);

    }
}
