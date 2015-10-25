package com.novaapps.botler;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class StatsActivity extends Fragment {
    PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle bundle) {
        super.onCreateView(inflater, null, bundle);
        setHasOptionsMenu(true);

        //Inflate the layout
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pieChart = (PieChart) getActivity().findViewById(R.id.pieChart);

        setPieChart();
    }

    public void setPieChart(){
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
        colors.add(ContextCompat.getColor(getActivity(), R.color.green));
        colors.add(ContextCompat.getColor(getActivity(), R.color.red));
        colors.add(ContextCompat.getColor(getActivity(), R.color.colorPrimary));

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


