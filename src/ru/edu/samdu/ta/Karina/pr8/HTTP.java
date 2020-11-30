package ru.edu.samdu.ta.Karina.pr8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

import org.apache.commons.lang.*;



import ua.edu.sumdu.ta.Karina.pr8.*;

public class HTTP
{
    static public String xml ="http://www.mocky.io/v2/5bebe91f3300008500fbc0e3";
    static public String json = "http://www.mocky.io/v2/5bed52fd3300004c00a2959d";

    public static void HttpGet(String request) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL(request).openConnection();

        connection.setRequestMethod("GET");

        connection.connect();

        StringBuilder sb = new StringBuilder();

        if(HttpURLConnection.HTTP_OK == connection.getResponseCode())
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
             while ((line = in.readLine()) != null)
             {
                 sb.append(line);
                 sb.append("\n");
             }

             System.out.println(sb.toString());
        }
        else
        {
            System.out.println("Fail: " + connection.getResponseCode());
        }

        System.out.println("Header: " + connection.getHeaderField("X-Ta-Course-Example-Header"));

        System.out.println(StringUtils.substringBetween(sb.toString(), "<name>", "</name>"));

        connection.disconnect();
    }

}
