package com.novaapps.botler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.novaapps.botler.Cards.CardAdapter;
import com.novaapps.botler.Cards.Game;

import java.util.ArrayList;

public class SavedActivity extends AppCompatActivity {
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        rv = (RecyclerView) findViewById(R.id.recyclerView);

        ArrayList<Game> games = new ArrayList<>();
        games.add(new Game("Rock Paper Scissors", "3 wins", "3 losses", "3 ties", null));
        games.add(new Game("Some other game", "6 wins", "3 losses", "1 ties", null));
        games.add(new Game("Some new game", "61 wins", "3000 losses", "12 ties", null));

        CardAdapter adapter = new CardAdapter(getApplicationContext(), games);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }
}
