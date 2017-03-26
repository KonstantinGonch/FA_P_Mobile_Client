package com.raxsoft.univer_project_client_side;


import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by JohnCena on 02.01.2017.
 */

public class Processor {
    private static final String SITE_PREFIX = "http://192.168.1.179:8080/getparam/";

    public static String GetParameter (String fnum, String snum, String tnum, String parNo) throws Exception
    {
        String output = "";
        String fullSite = SITE_PREFIX+fnum+"/"+snum+"/"+tnum+"/"+parNo+"/";
        Log.d("TAGTAGTAG", fullSite);
        URL url = new URL(fullSite);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = null;
        while((line = reader.readLine())!=null)
        {
            output+=line;
        }
        return output;
    }
}
