package ru.edu.samdu.ta.Karina.pr8;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.LinkedList;

import org.apache.commons.lang.*;



import ua.edu.sumdu.ta.Karina.pr8.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class HTTP
{
    static public String xml ="http://www.mocky.io/v2/5bebe91f3300008500fbc0e3";
    static public String json = "http://www.mocky.io/v2/5bed52fd3300004c00a2959d";

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

    public static void XmlWork() throws FileNotFoundException, XMLStreamException
    {
        LinkedList<Food> list = new LinkedList<>();

        final String fileName = "FoodXml.xml";
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(fileName, new FileInputStream(fileName));

        int index = 0;
        int k = 1;
        list.add(new Food());

        while (true)
        {
            if (reader.getEventType() == XMLStreamConstants.CHARACTERS)
            {
                if(!reader.isWhiteSpace())
                {
                    switch (k)
                    {
                        case 1:
                            list.get(index).name = reader.getText();
                            break;
                        case 2:
                            list.get(index).price = reader.getText();
                            break;
                        case 3:
                            list.get(index).description = reader.getText();
                            break;
                        case 4:
                            list.get(index).calories = reader.getText();
                            break;
                    }
                    k++;
                    if (k >= 5)
                    {
                        k = 1;
                        index++;
                        list.add(new Food());
                    }
                    //System.out.println( reader.getText());
                }
            }

            if (!reader.hasNext())
            {
                break;
            }

            reader.next();
        }

        System.out.println(list.get(4).name);
        System.out.println(list.get(4).price);
        System.out.println(list.get(4).description);
        System.out.println(list.get(4).calories);
    }

}
