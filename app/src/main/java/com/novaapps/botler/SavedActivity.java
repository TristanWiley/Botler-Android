package com.novaapps.botler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.novaapps.botler.Cards.DetailAdapter;
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
        games.add(new Game("Wins", "3 wins"));
        games.add(new Game("Ties", "3 ties"));
        games.add(new Game("Loss", "3 loss"));

        DetailAdapter adapter = new DetailAdapter(getApplicationContext(), games);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }
}
