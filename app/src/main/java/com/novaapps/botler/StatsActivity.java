package com.novaapps.botler;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pieChart = (PieChart) findViewById(R.id.pieChart);

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
        yVals.add(new Entry(30, 0));
        yVals.add(new Entry(60, 0));
        yVals.add(new Entry(10, 0));

        ArrayList<String> xVals = new ArrayList<>();
        xVals.add("Wins");
        xVals.add("Losses");
        xVals.add("Ties");

        PieDataSet dataSet = new PieDataSet(yVals, "");

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(this, R.color.green));
        colors.add(ContextCompat.getColor(this, R.color.red));
        colors.add(ContextCompat.getColor(this, R.color.colorPrimary));

        dataSet.setColors(colors);



        PieData data = new PieData(xVals, dataSet);
        data.setValueTextSize(12f);
        Legend lg = new Legend();
        lg.setCustom(colors, xVals);
        lg.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);

        pieChart.setData(data);
        pieChart.invalidate();
    }

}
