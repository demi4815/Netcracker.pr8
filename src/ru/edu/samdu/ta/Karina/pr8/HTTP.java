package ru.edu.samdu.ta.Karina.pr8;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.LinkedList;

import org.apache.commons.lang.*;



import ua.edu.sumdu.ta.Karina.pr8.*;

public class HTTP
{
    static public String xml ="http://www.mocky.io/v2/5bebe91f3300008500fbc0e3";
    static public String json = "http://www.mocky.io/v2/5bed52fd3300004c00a2959d";

    /*public static void HttpGet(String request) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL(request).openConnection();

        connection.setRequestMethod("GET");

        connection.connect();

        LinkedList<Food> food = new LinkedList<>();

        if(HttpURLConnection.HTTP_OK == connection.getResponseCode())
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

             while ((line = in.readLine()) != null)
             {
                 if(StringUtils.substringBetween(line, "<name>", "</name>") != null)
                 {
                     System.out.println(StringUtils.substringBetween(line, "<name>", "</name>"));
                 }
                 else if(StringUtils.substringBetween(line, "<price>$", "</price>") != null)
                 {
                     System.out.println(StringUtils.substringBetween(line, "<price>$", "</price>"));
                 }
                 else if(StringUtils.substringBetween(line, "<description>", "</description>") != null)
                 {
                     System.out.println(StringUtils.substringBetween(line, "<description>", "</description>"));
                 }
                 else if(StringUtils.substringBetween(line, "<calories>", "</calories>") != null)
                 {
                     System.out.println(StringUtils.substringBetween(line, "<calories>", "</calories>"));
                 }
             }
        }
        else
        {
            System.out.println("Fail: " + connection.getResponseCode());
        }

        System.out.println("Header: " + connection.getHeaderField("X-Ta-Course-Example-Header"));

        connection.disconnect();
    }*/

    public static void HttpGet(String request) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL(request).openConnection();

        connection.setRequestMethod("GET");

        connection.connect();


        if(HttpURLConnection.HTTP_OK == connection.getResponseCode())
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null)
            {
                sb.append(line);
                sb.append("\n");
            }

            File file = new File("FoodXml.xml");
            BufferedWriter writer = null;

            try {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(sb.toString());
            } finally {
                if (writer != null) writer.close();
            }
        }
        else
        {
            System.out.println("Fail: " + connection.getResponseCode());
        }

        System.out.println("Header: " + connection.getHeaderField("X-Ta-Course-Example-Header"));

        connection.disconnect();
    }

}
