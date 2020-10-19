package com.example.scoreboard.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.scoreboard.R;
import com.example.scoreboard.contract.TeamDetailContract;
import com.example.scoreboard.model.TeamDetail;
import com.example.scoreboard.presenter.TeamDetailPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamDetailActivity extends AppCompatActivity implements TeamDetailContract.TeamDetailView {

    private static final String DETAIL_PARCELABLE_KEY = "DETAIL_PARCELABLE_KEY";
    private TeamDetailContract.TeamDetailPresenter presenter;
    private TeamDetail teamDetail;
    private ProgressBar loadingBar;
    private ImageView errorImage;
    private ImageView teamLogo;
    private TextView teamName;
    private TextView teamYear;
    private TextView teamDescription;
    private ConstraintLayout bottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        loadingBar = findViewById(R.id.detail_loading);
        errorImage = findViewById(R.id.details_error);
        teamLogo = findViewById(R.id.team_logo);
        teamName = findViewById(R.id.team_name);
        teamYear = findViewById(R.id.formed_year);
        teamDescription = findViewById(R.id.description);
        bottomMenu = findViewById(R.id.bottom_menu);

        Intent intent = getIntent();
        String teamName = intent.getStringExtra(getString(R.string.team_name_intent));

        presenter = new TeamDetailPresenter();
        presenter.onAttach(this);
        if (savedInstanceState != null) {
            teamDetail = savedInstanceState.getParcelable(DETAIL_PARCELABLE_KEY);
        }
        if (teamDetail == null) {
            presenter.getTeamDetails(teamName);
        } else {
            displayDetails(teamDetail);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(DETAIL_PARCELABLE_KEY, teamDetail);
    }

    @Override
    public void getTeamDetailsCallback(List<TeamDetail> details) {
        teamDetail = details.get(0);
        displayDetails(teamDetail);
    }

    @Override
    public void hideBottomMenu() {
        bottomMenu.setVisibility(View.GONE);
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

    private void displayDetails(TeamDetail teamDetail) {
        Picasso.get().load(teamDetail.getStrTeamBadge()).into(teamLogo);
        if (teamDetail.getStrAlternate().isEmpty()) {
            teamName.setText(teamDetail.getStrTeam());
        } else {
            teamName.setText(teamDetail.getStrAlternate());
        }
        teamYear.setText(teamDetail.getIntFormedYear());
        teamDescription.setText(teamDetail.getStrDescriptionEN());
    }

    public void openFacebook(View view) {
        String url = teamDetail.getStrFacebook();
        Uri webpage = Uri.parse("http://" + url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }

    public void openInstagram(View view) {
        String url = teamDetail.getStrInstagram();
        Uri webPage = Uri.parse("http://" + url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
        startActivity(webIntent);
    }

    public void openYoutube(View view) {
        String url = teamDetail.getStrYoutube();
        Uri webPage = Uri.parse("http://" + url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
        startActivity(webIntent);
    }

    public void openTwitter(View view) {
        String url = teamDetail.getStrTwitter();
        Uri webPage = Uri.parse("http://" + url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
        startActivity(webIntent);
    }
}