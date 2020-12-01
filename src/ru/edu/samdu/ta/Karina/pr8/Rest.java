package ru.edu.samdu.ta.Karina.pr8;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Rest
{
    static public String xml ="http://www.mocky.io/v2/5bebe91f3300008500fbc0e3";
    static public String json = "http://www.mocky.io/v2/5bed52fd3300004c00a2959d";

    public static StringBuilder HttpConnectionAndSb(String request) throws IOException {

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
        }
        else
        {
            System.out.println("Fail: " + connection.getResponseCode());
        }

        System.out.println("Header: " + connection.getHeaderField("X-Ta-Course-Example-Header"));
        System.out.println();

        connection.disconnect();

        return sb;
    }

    public static void getXmlFile(StringBuilder sb) throws IOException {
        File fileXml = new File("FoodXml.xml");
        BufferedWriter writerXML = null;

        try {
            writerXML = new BufferedWriter(new FileWriter(fileXml));
            writerXML.write(sb.toString());
        } finally {
            if (writerXML != null) writerXML.close();
        }
    }
    public static void getJsonFile(StringBuilder sb) throws IOException
    {
        File fileJson = new File("FoodJson.json");
        BufferedWriter writerJson = null;

        try {
            writerJson = new BufferedWriter(new FileWriter(fileJson));
            writerJson.write(sb.toString());
        } finally {
            if (writerJson != null) writerJson.close();
        }
    }

    public static List<Food> XmlToFood() throws FileNotFoundException, XMLStreamException
    {
        final String fileName = "FoodXml.xml";
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(fileName, new FileInputStream(fileName));

        LinkedList<Food> list = new LinkedList<>();

        int index = 0;
        int k = 1;
        list.add(new Food());

        while (true)
        {
            if (reader.getEventType() == XMLStreamConstants.CHARACTERS)
            {
                if(!reader.isWhiteSpace())
                {
                    switch (k) {
                        case 1 -> list.get(index).name = reader.getText();
                        case 2 -> list.get(index).price = reader.getText();
                        case 3 -> list.get(index).description = reader.getText();
                        case 4 -> list.get(index).calories = reader.getText();
                    }

                    k++;

                    if (k >= 5)
                    {
                        k = 1;
                        index++;
                        list.add(new Food());
                    }
                }
            }

            if (!reader.hasNext())
            {
                list.remove(index);
                break;
            }

            reader.next();
        }

        return  list;
    }

    public static void /*List<Food>*/JsonToFood() throws IOException, ParseException {
        final String fileName = "FoodJson.json";
        Object obj = new JSONParser().parse(new FileReader(fileName));
        JSONObject jsonObject1 = (JSONObject) obj;
        JSONObject jsonObject2 = (JSONObject) jsonObject1.get("breakfast_menu");
        JSONArray food = ( JSONArray) jsonObject2.get("food");

        //LinkedList<Food> list = new LinkedList<>();
        //System.out.println(food.toJSONString());

        Iterator nameItr = food.iterator();
        System.out.println("Names:");
        while (nameItr.hasNext()) {
            JSONObject test = (JSONObject) nameItr.next();
            System.out.println(test.get("name"));
        }

        //return  list;

    }

    public static void XmlWork(List<Food> list)
    {
        System.out.println("List of dishes: ");
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i).name);
        }
        System.out.println();

        int maxСalorie = 0;
        int index = 0;
        for (int i = 0; i < list.size(); i++)
        {
            if(Integer.parseInt(list.get(i).calories) > maxСalorie)
            {
                maxСalorie = Integer.parseInt(list.get(i).calories);
                index = i;
            }
        }
        System.out.println("The name of the dish with the most calories: " + list.get(index).name);
        System.out.println();


        System.out.println("Price for dishes with calorie content less than 700: ");
        for (int i = 0; i < list.size(); i++)
        {
            if(Integer.parseInt(list.get(i).calories) < 700)
            {
                System.out.println(list.get(i).price);
            }
        }
        System.out.println();
    }



}
