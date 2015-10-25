package com.novaapps.botler.Cards;

/**
 * Created by Tristan on 10/24/2015.
 */
public class Game {

    String name;
    String total_detail;

    public Game(String name, String total_detail) {
        this.name = name;
        this.total_detail = total_detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWins() {
        return total_detail;
    }

    public void setWins(String wins) {
        this.total_detail = wins;
    }

}
