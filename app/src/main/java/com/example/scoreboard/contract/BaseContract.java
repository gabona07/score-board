package com.example.scoreboard.contract;

public interface BaseContract {

    interface BaseView {

        void hideLoading();

        void showLoading();

        void displayError();

    }

    interface BasePresenter {

        void onAttach(BaseView view);

        void onDetach();

    }
}
