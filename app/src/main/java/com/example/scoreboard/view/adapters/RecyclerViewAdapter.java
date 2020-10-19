package com.example.scoreboard.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scoreboard.R;
import com.example.scoreboard.model.SportEvent;
import com.example.scoreboard.view.TeamDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<SportEvent> events = new ArrayList<>();
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.event_item, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SportEvent currentEvent = events.get(position);
        Picasso.get().load(currentEvent.getHomeTeamBadgeUrl()).into(holder.homeTeamLogo);
        Picasso.get().load(currentEvent.getAwayTeamBadgeUrl()).into(holder.awayTeamLogo);
        holder.dateText.setText(currentEvent.getDateEvent());
        holder.homeTeamName.setText(currentEvent.getHomeTeam());
        holder.awayTeamName.setText(currentEvent.getAwayTeam());
        if (currentEvent.getHomeScore() != null && currentEvent.getAwayScore() != null) {
            holder.homeScore.setText(currentEvent.getHomeScore());
            holder.awayScore.setText(currentEvent.getAwayScore());
            holder.scoreSeparator.setVisibility(View.VISIBLE);
        } else {
            holder.versusImg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void setEvents(List<SportEvent> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerViewAdapter adapter;
        private Context context;
        private TextView dateText;
        private ImageView homeTeamLogo;
        private TextView homeTeamName;
        private TextView homeScore;
        private ImageView awayTeamLogo;
        private TextView awayTeamName;
        private TextView awayScore;
        private TextView scoreSeparator;
        private ImageView versusImg;

        public ViewHolder(@NonNull View itemView, RecyclerViewAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            dateText = itemView.findViewById(R.id.date);
            homeTeamLogo = itemView.findViewById(R.id.home_team_logo);
            homeTeamName = itemView.findViewById(R.id.home_team);
            homeScore = itemView.findViewById(R.id.home_score);
            awayTeamLogo = itemView.findViewById(R.id.away_team_logo);
            awayTeamName = itemView.findViewById(R.id.away_team);
            awayScore = itemView.findViewById(R.id.away_score);
            scoreSeparator = itemView.findViewById(R.id.score_separator);
            versusImg = itemView.findViewById(R.id.versus);

            homeTeamLogo.setOnClickListener(view -> {
                context = view.getContext();
                int position = getLayoutPosition();
                String teamName = adapter.events.get(position).getHomeTeam();
                Intent intent = new Intent(view.getContext(), TeamDetailActivity.class);
                intent.putExtra(context.getString(R.string.team_name_intent), teamName);
                context.startActivity(intent);
            });

            awayTeamLogo.setOnClickListener(view -> {
                context = view.getContext();
                int position = getLayoutPosition();
                String teamName = adapter.events.get(position).getAwayTeam();
                Intent intent = new Intent(view.getContext(), TeamDetailActivity.class);
                intent.putExtra(context.getString(R.string.team_name_intent), teamName);
                context.startActivity(intent);
            });
        }
    }
}
