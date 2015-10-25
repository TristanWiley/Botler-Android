package com.novaapps.botler.Cards;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

/**
 * Created by Tristan on 10/24/2015.
 */
public class Game {

    String name;
    String total_wins;
    String total_losses;
    String total_tie;
    @Nullable Drawable game_image;

    public Game(String name, String total_wins, String total_losses, String total_tie, Drawable game_image){
        this.name = name;
        this.total_wins = total_wins;
        this.total_losses = total_losses;
        this.total_tie = total_tie;
    }

    public String getName(){
        return name;
    }

    public String getWins(){
        return total_wins;
    }

    public String getLosses(){
        return total_losses;
    }

    public String getTies(){
        return total_tie;
    }

    public Drawable getImage(){
        return game_image;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setWins(String wins){
        this.total_wins = wins;
    }

    public void setLosses(String losses){
        this.total_losses = losses;
    }

    public void setTies(String ties){
        this.total_tie = ties;
    }

    public void setDrawable(Drawable image){
        this.game_image = image;
    }
}
