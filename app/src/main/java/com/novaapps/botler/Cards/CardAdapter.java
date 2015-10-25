package com.novaapps.botler.Cards;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.novaapps.botler.R;
import com.novaapps.botler.StatsActivity;

import java.util.List;

/**
 * Created by Tristan on 10/24/2015.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private Context mContext;
    private List<Game> mGames;

    public CardAdapter(Context context, List<Game> games){
        this.mContext = context;
        this.mGames = games;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.game_card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindGame(mGames.get(position));
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView gameTitleTextView;
        public final TextView gameWonTextView;
        public final TextView gameLostTextView;
        public final TextView gameTiedTextView;

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, StatsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        };

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(clickListener);
            gameTitleTextView = (TextView) view.findViewById(R.id.game_title);
            gameWonTextView = (TextView) view.findViewById(R.id.game_won);
            gameLostTextView = (TextView) view.findViewById(R.id.game_loss);
            gameTiedTextView = (TextView) view.findViewById(R.id.game_tied);
        }

        public void bindGame(Game game) {
            this.gameTitleTextView.setText(game.getName());
            this.gameWonTextView.setText(game.getWins());
            this.gameLostTextView.setText(game.getLosses());
            this.gameTiedTextView.setText(game.getTies());
        }

    }
}