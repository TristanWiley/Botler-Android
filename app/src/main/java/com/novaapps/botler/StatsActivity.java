package com.novaapps.botler;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.novaapps.botler.Cards.DetailAdapter;
import com.novaapps.botler.Cards.Game;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class StatsActivity extends Fragment {
    PieChart pieChart;
    String jsonName;
    RecyclerView rv;

    double win;
    double loss;
    double ties;
    double total;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        jsonName = b.getString("json");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pieChart = (PieChart) getActivity().findViewById(R.id.pieChart);
        rv = (RecyclerView) getActivity().findViewById(R.id.detail_recycler);

        ParseJson p = new ParseJson(jsonName);
        win = p.getWins();
        loss = p.getLosses();
        ties = p.getTies();

         total = win + loss + ties;

        String ratio;
        if (loss != 0) {
            BigDecimal a = new BigDecimal(win / loss);
            BigDecimal b = a.setScale(2, RoundingMode.HALF_EVEN);
            ratio = String.valueOf(b);
        } else {
            ratio = "100";
        }

        ArrayList<Game> details = new ArrayList<>();
        details.add(new Game("Total Games", String.valueOf(total)));
        details.add(new Game("Win Loss Ratio", ratio));

        DetailAdapter adapter = new DetailAdapter(getActivity().getApplicationContext(), details);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        rv.setAdapter(adapter);
        setPieChart();
    }

    public void setPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColorTransparent(true);
        pieChart.setUsePercentValues(true);

        pieChart.setDrawSliceText(false);
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(48f);
        pieChart.setTransparentCircleRadius(48f);

        pieChart.setDescription("");
        pieChart.setDrawCenterText(false);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);

        pieChart.setUsePercentValues(false);

        pieChart.setDrawHoleEnabled(true);

        ArrayList<Entry> yVals = new ArrayList<>();
        yVals.add(new Entry((float) win, 0));
        yVals.add(new Entry((float) loss, 0));
        yVals.add(new Entry( (float) ties, 0));

        ArrayList<String> xVals = new ArrayList<>();
        xVals.add("Wins");
        xVals.add("Losses");
        xVals.add("Ties");

        PieDataSet dataSet = new PieDataSet(yVals, "");

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(getActivity(), R.color.green));
        colors.add(ContextCompat.getColor(getActivity(), R.color.red));
        colors.add(ContextCompat.getColor(getActivity(), R.color.colorAccent));

        dataSet.setColors(colors);


        PieData data = new PieData(xVals, dataSet);
        data.setValueTextSize(12f);
//        Legend lg = new Legend();
//        lg.setCustom(colors, xVals);
//        lg.setPosition(Legend.LegendPosition.PIECHART_CENTER);

        pieChart.setData(data);
        pieChart.invalidate();


    }
}


