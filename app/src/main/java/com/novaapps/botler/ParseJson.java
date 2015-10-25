package com.novaapps.botler;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by MLH-User on 10/25/2015.
 */
public class ParseJson {
    JSONObject jsonObject;

    ParseJson(String json) {
        try {
            jsonObject = new JSONObject(json);
        } catch (Exception e) {
            Log.e("Parsing Json Object", e.getMessage());
        }
    }

    public Integer getWins() {
        int s = 0;
        try {
            s = jsonObject.getInt("wins");
        } catch (Exception e) {
            Log.e("WinsParsing", e.getMessage());
        }
        return s;
    }

    public Integer getLosses() {
        int s = 0;
        try {
            s = jsonObject.getInt("losses");
        } catch (Exception e) {
            Log.e("WinsParsing", e.getMessage());
        }
        return s;
    }

    public Integer getTies() {
        int s = 0;
        try {
            s = jsonObject.getInt("ties");
        } catch (Exception e) {
            Log.e("WinsParsing", e.getMessage());
        }
        return s;
    }
}
