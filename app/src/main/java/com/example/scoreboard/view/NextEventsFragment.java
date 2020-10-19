package com.example.scoreboard.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.scoreboard.contract.NextEventsContract;
import com.example.scoreboard.R;
import com.example.scoreboard.model.SportEvent;
import com.example.scoreboard.presenter.NextEventsPresenter;
import com.example.scoreboard.view.adapters.RecyclerViewAdapter;

import java.util.List;

public class NextEventsFragment extends Fragment implements NextEventsContract.NextEventsView {

    private ProgressBar loadingBar;
    private ImageView errorImage;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private NextEventsContract.NextEventsPresenter presenter;
    private List<SportEvent> sportEvents;

    public NextEventsFragment() {
        // Required empty public constructor
    }

    public static NextEventsFragment newInstance() {
        return new NextEventsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new NextEventsPresenter();
        presenter.onAttach(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.events_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        loadingBar = view.findViewById(R.id.last_events_loading);
        errorImage = view.findViewById(R.id.last_events_error);
        refreshLayout = view.findViewById(R.id.refresh_layout);
        adapter = new RecyclerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout.setOnRefreshListener(() -> presenter.getNextEvents());
        if (sportEvents == null) {
            presenter.getNextEvents();
        } else {
            displayNextEvents(sportEvents);
        }
    }

    @Override
    public void getNextEventsCallback(List<SportEvent> events) {
        presenter.getTeamBadges(events);
    }

    @Override
    public void getTeamBadgesCallBack(List<SportEvent> events) {
        displayNextEvents(events);
    }

    @Override
    public void hideRefreshBar() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void hideRecyclerView() {
        recyclerView.setVisibility(View.GONE);
    }

    private void displayNextEvents(List<SportEvent> events) {
        sportEvents = events;
        adapter.setEvents(events);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayError() {
        errorImage.setVisibility(View.VISIBLE);
    }
}
