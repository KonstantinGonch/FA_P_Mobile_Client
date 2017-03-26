package com.raxsoft.univer_project_client_side;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class DataContainer {
    public static List<String> nums;
    public static String cFnum;
    public static String cSnum;
    public static String cTnum, cFullNum, cPos, cDateReg, cCurState, cQuery;
    private static final String TAG=  "NUMBERS";

    public static void SaveNums(SharedPreferences prefs)
    {
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        editor.putString(TAG,gson.toJson(nums));
        editor.apply();
    }

    public static void LoadNums(SharedPreferences prefs)
    {

    }
}
