package com.novaapps.botler.Cards;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.novaapps.botler.R;

import java.util.List;

/**
 * Created by Tristan on 10/24/2015.
 */
public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {
    private Context mContext;
    private List<Game> mDetails;

    public DetailAdapter(Context context, List<Game> games) {
        this.mContext = context;
        this.mDetails = games;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_detail_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindGame(mDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return mDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView gameTitleTextView;
        public final TextView gameDetailTextView;

        public ViewHolder(View view) {
            super(view);
            gameTitleTextView = (TextView) view.findViewById(R.id.game_title);
            gameDetailTextView = (TextView) view.findViewById(R.id.game_detail);

        }

        public void bindGame(Game game) {
            this.gameTitleTextView.setText(game.getName());
            this.gameDetailTextView.setText(game.getWins());
        }

    }
}