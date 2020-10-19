package com.example.scoreboard.contract;

import com.example.scoreboard.model.SportEvent;

import java.util.List;

public interface LastEventsContract {

    interface LastEventsView extends BaseContract.BaseView {

        void getLastEventsCallBack(List<SportEvent> events);

        void getTeamBadgesCallBack(List<SportEvent> events);

        void hideRefreshBar();

        void hideRecyclerView();
    }

    interface LastEventsPresenter extends BaseContract.BasePresenter {

        void getLastEvents();

        void getTeamBadges(List<SportEvent> events);
    }
}
